package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class EnterprisePlanPricing implements PlanPricingStrategy {

    // Constantes para os preços por usuário
    private static final Money PRICE_PER_USER_UNDER_100 = Money.Factory.of(185, "USD");   // Para empresas com mais de 100 usuários
    private static final Money PRICE_PER_USER_UNDER_10000 = Money.Factory.of(135, "USD");  // Para empresas com mais de 10.000 usuários

    // Preço negociado por GB excedente
    private static final Money PRICE_PER_GB_OVERCAP = Money.Factory.of(0.91, "USD");

    // Espaço de armazenamento gratuito por usuário (em GB)
    private static final int STORAGE_FREE_PER_USER = 5;  // em GB

    @Override
    public Money calculateUserCost(int users) {
        // Verifica o preço por usuário com base no número de usuários
        Money pricePerUser;
        if (users > 10000) {
            pricePerUser = PRICE_PER_USER_UNDER_10000; // Empresas com mais de 10.000 usuários
        } else if (users > 100) {
            pricePerUser = PRICE_PER_USER_UNDER_100; // Empresas com mais de 100 usuários
        } else {
            pricePerUser = PRICE_PER_USER_UNDER_100; // Para empresas com menos de 100 usuários
        }

        // Calcula o custo total dos usuários
        Money totalUserCost = pricePerUser.times(users);

        // Retorna o custo total como um objeto Money
        return totalUserCost;
    }

    @Override
    public Money calculateStorageCost(int storageInGigabytes, int users) {
        // Calcula o espaço de armazenamento gratuito disponível
        int freeStorageAvailable = users * STORAGE_FREE_PER_USER;

        // Calcula o excedente de armazenamento
        int extraStorage = storageInGigabytes - freeStorageAvailable;

        // Se o excedente for positivo, calcula o custo
        Money extraStorageCost = Money.Factory.of(0, "USD");
        if (extraStorage > 0) {
            extraStorageCost = PRICE_PER_GB_OVERCAP.times(extraStorage); // Custo do armazenamento excedente
        }

        // Retorna o custo do armazenamento excedente como um objeto Money
        return extraStorageCost;
    }
}