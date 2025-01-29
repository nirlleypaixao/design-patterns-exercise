package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class EnterprisePlanPricing implements PlanPricingStrategy {

    // Constantes para os preços por usuário
    private Money pricePerUser;
    private Money pricePerGB;

    // Espaço de armazenamento gratuito por usuário (em GB)
    private static final int STORAGE_FREE_PER_USER = 5;

    public EnterprisePlanPricing(Money pricePerUser, Money pricePerGB) {
        this.pricePerUser = pricePerUser;
        this.pricePerGB = pricePerGB;
    }

    @Override
    public Money calculateUserCost(int users) {
        return pricePerUser.times(users);
    }

    @Override
    public Money calculateStorageCost(int storageInGigabytes, int users) {
      int freeStorage = users * STORAGE_FREE_PER_USER;
      int excessStorage = Math.max(storageInGigabytes - freeStorage, 0);
      return pricePerGB.times(excessStorage);
    }
}