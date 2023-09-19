package com.malik.CelioShop.CelioShop.controller;

import com.malik.CelioShop.CelioShop.playload.product.ProductSold;
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
    @GetMapping
    public ResponseEntity<List<ProductSold>> getListOfSales(){

        return new ResponseEntity<>(statisticsService.getListOfSales(), HttpStatus.OK);
    }

    // products statistics
//    @GetMapping
//    public ResponseEntity<List<ProductSold>> getTotalOfSalesAndReviewsOfEachProduct(){
//
//        return  new ResponseEntity<>(List.of(),HttpStatus.OK);
//        //return new ResponseEntity<>(statisticsService.getTotalOfSalesAndReviewsOfEachProduct(),HttpStatus.OK);
//    }

    // reviews statics


    // users statics
//    @GetMapping
//    public ResponseEntity<List<ProductSold>> getListOfUsersWithSales(){
//
//        return  new ResponseEntity<>(List.of(),HttpStatus.OK);
//    }




}
