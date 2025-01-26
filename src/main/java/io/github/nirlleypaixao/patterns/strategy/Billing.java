package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

public class Billing {
    public Invoice invoice(Contract contract, Usage usage) {
        Money userCost = Money.Factory.of(0, "USD");
        Money storageCost = Money.Factory.of(0, "USD");

        return new Invoice(contract.plan, usage.users, usage.storageInGigabytes, userCost, storageCost);
    }
}
