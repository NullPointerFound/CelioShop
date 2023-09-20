package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.statistics.ProductSold;
import com.malik.CelioShop.CelioShop.playload.statistics.UserStats;

import java.util.List;

public interface StatisticsService {

    public List<ProductSold> getListOfSales();

//    public List<ProductSold>  getTotalOfSalesAndReviewsOfEachProduct();

    List<UserStats> getListOfSalesOfEachUser();
}
