//package com.stocks.api.contoller;
//
//import com.stocks.api.model.City;
//import com.stocks.api.model.WeatherResponse;
//import com.stocks.api.repositories.StockRepository;
//import com.stocks.api.services.StockService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class StocksControllerTest {
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private StocksController stocksController;
//
//    @Mock
//    private StockService weatherService;
//
//    @Mock
//    private StockRepository stockRepository;
//    private String city;
//
//    @BeforeEach // For Junit5
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(stocksController).build();
//        this.city="dummy";
//
//    }
//
//    @Test
//    void getWeatherResponsePositive() throws Exception {
//        WeatherResponse weatherResponse=new WeatherResponse();
//        City cityObj=mock(City.class);
//        when(weatherService.getAverageWeatherData(city)).thenReturn(weatherResponse);
//        when(stockRepository.findByName(city)).thenReturn(cityObj);
//        mockMvc.perform( MockMvcRequestBuilders
//                        .get("/data?city="+city)
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andDo(print())
//                        .andExpect(status().isOk());
//    }
//
//    @Test
//    void getWeatherResponseCityRequiredException() throws Exception {
//        mockMvc.perform( MockMvcRequestBuilders
//                        .get("/data")
//                        .accept(MediaType.APPLICATION_JSON))
//                        .andDo(print())
//                        .andExpect(status().isBadRequest());//Bad Request
//    }
//
//}
