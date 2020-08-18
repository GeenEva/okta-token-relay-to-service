package com.okta.developer.cartservice.model;

import javax.money.MonetaryAmount;

public class LineItem {

    private Integer id;

    private String productName;

    private Integer quantity;

    private MonetaryAmount price;
}
