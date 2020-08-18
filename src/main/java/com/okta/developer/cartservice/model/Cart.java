package com.okta.developer.cartservice.model;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Integer id;

    private String customerId;

    private MonetaryAmount total;

    private List<LineItem> lineItems = new ArrayList<>();


}
