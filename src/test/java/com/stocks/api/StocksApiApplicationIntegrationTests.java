package com.stocks.api;

import com.stocks.api.batch.scheduler.StockScheduler;
import com.stocks.api.contoller.StocksController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class StocksApiApplicationIntegrationTests {
	private MockMvc mockMvc;

	@Autowired
	StocksController stocksController;

	@Autowired
	private StockScheduler stockScheduler;

	@Test
	void runTheSchedulerAndFetchData() throws Exception {
		stockScheduler.processStockData();

		mockMvc= MockMvcBuilders.standaloneSetup(stocksController).build();
		mockMvc.perform( MockMvcRequestBuilders
						.get("/data")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());

	}


}
