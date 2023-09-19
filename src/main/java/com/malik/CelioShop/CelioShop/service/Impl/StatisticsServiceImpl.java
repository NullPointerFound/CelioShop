package com.malik.CelioShop.CelioShop.service.Impl;

import com.malik.CelioShop.CelioShop.playload.product.ProductSold;
import com.malik.CelioShop.CelioShop.repository.StatisticsRepository;
import com.malik.CelioShop.CelioShop.service.StatisticsService;
import lombok.AllArgsConstructor;
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
    public List<ProductSold> getTotalOfSalesAndReviewsOfEachProduct() {
        return null;
    }
}
