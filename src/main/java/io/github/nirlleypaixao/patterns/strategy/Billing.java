package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class Billing {
    public Invoice invoice(Contract contract, Usage usage) {
        PlanPricingStrategy pricingStrategy = contract.pricingStrategy;

        Money userCost = pricingStrategy.calculateUserCost(usage.users);
        Money storageCost = pricingStrategy.calculateStorageCost(usage.storageInGigabytes, usage.users);

        return new Invoice(
                contract.pricingStrategy.getClass().getSimpleName(), //convertendo pricingStrategy para tipo String.
                usage.users,
                usage.storageInGigabytes,
                userCost,
                storageCost);
    }
}
