//package com.stocks.api.services;
//
//import com.stocks.api.model.Weather;
//import com.stocks.api.services.impl.StockServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class StockServiceTest {
//    @InjectMocks
//    private StockServiceImpl weatherService;
//
//    @Mock
//    private WeatherRepository weatherRepository;
//
//
//    @Test
//    void getAverageWeatherDataPositive(){
//        String city="dummy";
//        double temp=13.6;
//        Weather weather=mock(Weather.class);
//        when(weather.getDayTemp()).thenReturn(temp);
//        when(weather.getNightTemp()).thenReturn(temp);
//        when(weather.getPressure()).thenReturn(temp);
//        ArrayList<Weather> weatherList=new ArrayList<>();
//        weatherList.add(weather);
//        when(weatherRepository.getLast2days(city)).thenReturn(weatherList);
//        assertDoesNotThrow(()->weatherService.getAverageWeatherData(city));
//        assertEquals(weatherService.getAverageWeatherData(city).getAverageDayTemperature(),temp);
//    }
//
//    @Test
//    void getAverageWeatherDataNegative(){
//        String city="dummy";
//        when(weatherRepository.getLast2days(city)).thenThrow(NullPointerException.class);
//        assertThrows(Exception.class,()->weatherService.getAverageWeatherData(city));
//    }
//
//}
