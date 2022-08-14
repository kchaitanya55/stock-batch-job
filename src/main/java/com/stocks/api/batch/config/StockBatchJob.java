package com.stocks.api.batch.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.api.model.StockDaily;
import com.stocks.api.repositories.StockRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
public class StockBatchJob extends DefaultBatchConfigurer {
    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Value("${stock.api.key}")
    private String apiKey;

    @Value("${stock.api.url}")
    private String stockURL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockRepository stockRepository;

    @Bean
    public Job getStocksJob() throws JsonProcessingException {
        return jobs
                .get("stocksJob")
                .start(dataCleanUp())
                .next(processStocksData())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader jobScopedItemReader() throws JsonProcessingException {
        String url=stockURL+apiKey;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String data=restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        Map<String, Map<String,Object>> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(data, HashMap.class);
        Map<String, Object> dailyData=map.get("Time Series (Daily)");
        List<StockDaily> list=dailyData.entrySet().stream().map(entry->{
            StockDaily daily=null;
            try {
                daily=mapper.readValue(mapper.writeValueAsString(entry.getValue()),StockDaily.class);
                daily.setStockDate(Date.valueOf(LocalDate.parse(entry.getKey(), DateTimeFormatter.ofPattern("uuuu-MM-dd"))));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return daily;
        }).filter(stockDaily -> stockDaily.getStockDate().after(Date.valueOf(LocalDate.now().minusDays(30)))).collect(Collectors.toList());

        return new ListItemReader<StockDaily>(list);
    }
    @Bean
    protected Step dataCleanUp(){
        return steps.get("dataCleanUp").tasklet((StepContribution contribution,
                                                 ChunkContext chunkContext)->{
            stockRepository.deleteAll();
            return RepeatStatus.FINISHED;

        }).build();
    }

    @Bean
    protected Step processStocksData() throws JsonProcessingException {
        return steps.get("stockProcessingStep").chunk(1)
                .reader(jobScopedItemReader())
                .writer( stocks->{
                    stockRepository.saveAll((List<StockDaily>) stocks);
                })
                .build();

    }

    @Override
    public void setDataSource(DataSource dataSource){
        //No Datasource for internal batch job processing

    }

}
