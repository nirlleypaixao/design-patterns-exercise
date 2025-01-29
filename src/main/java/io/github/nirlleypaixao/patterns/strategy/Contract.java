package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class Contract {
    public final String client;
    public PlanPricingStrategy pricingStrategy;

    public Contract(String client, PlanPricingStrategy pricingStrategy) {
        this.client = client;
        this.pricingStrategy = pricingStrategy;
    }

    public void setPricingStrategy(Money dynamicPricePerUser, Money dynamicPricePerGB) {
        this.pricingStrategy = new EnterprisePlanPricing(dynamicPricePerUser, dynamicPricePerGB);
    }

    public PlanPricingStrategy getPricingStrategy() {
        return this.pricingStrategy;
    }
}


