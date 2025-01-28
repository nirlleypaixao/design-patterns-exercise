package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class BusinessPlanPricing implements PlanPricingStrategy {
    private static final Money PRICE_PER_USER = Money.Factory.of(70, "USD");
    private static final Money STORAGE_COST_PER_GB = Money.Factory.of(1.50, "USD");
    private static final int FREE_STORAGE_GB = 100;

    @Override
    public Money calculateUserCost(int users) {
        return PRICE_PER_USER.times(users);
    }

    @Override
    public Money calculateStorageCost(int storageInGigabytes, int users) {
        // Math.max garante que o valor do excesso de armazenamento nunca seja negativo.
        //Se o valor calculado for negativo i.e storageInGigabytes for < ou == FREE_STORAGE_GB, o excesso de armazenamento será zero (sem cobranças adicionais)
        int excessStorageGB = Math.max(0, storageInGigabytes - FREE_STORAGE_GB);
        return STORAGE_COST_PER_GB.times(excessStorageGB);
    }
}
