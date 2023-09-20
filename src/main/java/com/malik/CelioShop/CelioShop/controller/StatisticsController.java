package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.statistics.ProductSold;
import com.malik.CelioShop.CelioShop.playload.statistics.UserStats;
import com.malik.CelioShop.CelioShop.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    // sales statics
    @GetMapping("/sales")
    public ResponseEntity<List<ProductSold>> getListOfSales(){

        return new ResponseEntity<>(statisticsService.getListOfSales(), HttpStatus.OK);
    }

    // products statistics
    @GetMapping("/users")
    public ResponseEntity<List<UserStats>> getListOfSalesOfEachUser(){

        return  new ResponseEntity<>(statisticsService.getListOfSalesOfEachUser(),HttpStatus.OK);
    }

}
