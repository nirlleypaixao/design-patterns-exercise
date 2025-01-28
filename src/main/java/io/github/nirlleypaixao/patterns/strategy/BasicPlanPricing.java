package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class BasicPlanPricing implements PlanPricingStrategy {
    private static final Money PRICE_PER_USER = Money.Factory.of(30, "USD");

    @Override
    public Money calculateUserCost(int users) {
        return PRICE_PER_USER.times(users);
    }

    @Override
    public Money calculateStorageCost(int storageInGigabytes, int users) {
        return Money.Factory.of(0, "USD");
    }
}
