package com.bednarz.ports.model;


import lombok.Value;

@Value
public class CustomerInfo {
    String userName;
    Integer lastMonthRewardPoints;
    Integer last3MonthRewardPoints;
}
