package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.entity.product.Product;
import com.malik.CelioShop.CelioShop.playload.statistics.ProductSold;
import com.malik.CelioShop.CelioShop.playload.statistics.UserStats;
import com.malik.CelioShop.CelioShop.repository.StatisticsRepository;
import com.malik.CelioShop.CelioShop.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private StatisticsRepository statisticsRepository;

    @Override
    public List<ProductSold> getListOfSales() {

        List<Object []> obj = statisticsRepository.findProductsSold();

        List<ProductSold> allProductSold =  obj.stream().map( (item) -> {

            ProductSold productSold = new ProductSold();
            productSold.setProductId((Long) item[0]);
            productSold.setProductName(String.valueOf(item[1]));
            productSold.setProductCost((BigDecimal) item[2]);
            productSold.setProductSku((String) item[3]);
            productSold.setQuantityInStock((Integer) item[4]);
            productSold.setProductRateCount((Integer) item[5]);
            productSold.setQuantitySold((Integer) item[6]);
            productSold.setSaleDate((LocalDateTime) item[7]);
            productSold.setSubtotal((BigDecimal) item[8]);
            productSold.setOrderId((Long) item[9]);
            return productSold;
        }).collect(Collectors.toList());

        return allProductSold;
    }

    @Override
    public List<UserStats> getListOfSalesOfEachUser() {

        List<Object []> salesOfEachUserObj = statisticsRepository.findListOfSalesOfEachUser();

        salesOfEachUserObj.forEach(System.out::println);
        List<UserStats> salesOfEachUserList = salesOfEachUserObj.stream().map( item -> {


            UserStats userStats = new UserStats();
            userStats.setUserId((Long) item[0]);
            userStats.setUsername((String) item[1]);
            userStats.setUserJoinedDate((LocalDateTime) item[2]);
            userStats.setOrderSubTotal((BigDecimal) item[3]);
            userStats.setTaxPaid((BigDecimal) item[4]);
            userStats.setShippingCost((BigDecimal) item[5]);
            userStats.setSaleDate((LocalDateTime) item[6]);
            userStats.setQuantityBought((Integer) item[7]);
            userStats.setProduct((Product) item[8]);

            return userStats;

        }).collect(Collectors.toList());

        return salesOfEachUserList;
    }
}
