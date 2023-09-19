package com.malik.CelioShop.CelioShop.service;

import com.malik.CelioShop.CelioShop.playload.product.ProductSold;

import java.util.List;

public interface StatisticsService {

    public List<ProductSold> getListOfSales();

    public List<ProductSold>  getTotalOfSalesAndReviewsOfEachProduct();
}
