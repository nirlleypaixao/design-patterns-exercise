package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public interface PlanPricingStrategy {
    Money calculateUserCost(int users);

    Money calculateStorageCost(int storageInGigabytes, int users);
}

