package io.github.nirlleypaixao.patterns.strategy;

public class Contract {
    public final String client;
    public PlanPricingStrategy pricingStrategy;

    public Contract(String client, PlanPricingStrategy pricingStrategy) {
        this.client = client;
        this.pricingStrategy = pricingStrategy;
    }
}

