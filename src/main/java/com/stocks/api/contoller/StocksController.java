package com.stocks.api.contoller;

import com.stocks.api.model.StockDaily;
import com.stocks.api.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StocksController {

    @Autowired
    private StockRepository stockRepository;
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getStockData() {
        List<StockDaily> list=stockRepository.findAll();
        Map<String,StockDaily> stocks=new LinkedHashMap<>();
        list.forEach(stockDaily -> {
            stocks.put(stockDaily.getStockDate().toString(),stockDaily);
        });
        return new ResponseEntity(stocks,HttpStatus.OK);
    }
}
