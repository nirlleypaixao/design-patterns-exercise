package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class FreePlanPricing  implements PlanPricingStrategy {

    @Override
    public Money calculateUserCost(int users) {
        return Money.Factory.of(0, "USD");
    }

    @Override
    public Money calculateStorageCost(int storageInGigabytes, int users) {
        return Money.Factory.of(0, "USD");
    }
}
