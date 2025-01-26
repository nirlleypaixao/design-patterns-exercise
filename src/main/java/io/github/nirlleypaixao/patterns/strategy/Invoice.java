package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;

import java.util.Objects;

public class Invoice {
    public final String plan;
    public final int users;
    public final int storageInGigabytes;
    public final Money usersCost;
    public final Money storageCost;
    public final Money total;

    public Invoice(String plan, int users, int storageInGigabytes, Money usersCost, Money storageCost) {
        this.plan = plan;
        this.users = users;
        this.storageInGigabytes = storageInGigabytes;
        this.usersCost = usersCost;
        this.storageCost = storageCost;
        this.total = usersCost.plus(storageCost);
    }

    // Auto-generated methods using the wizard.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return users == invoice.users && storageInGigabytes == invoice.storageInGigabytes && Objects.equals(plan, invoice.plan) && Objects.equals(usersCost, invoice.usersCost) && Objects.equals(storageCost, invoice.storageCost) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plan, users, storageInGigabytes, usersCost, storageCost, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "plan='" + plan + '\'' +
                ", users=" + users +
                ", storageInGigabytes=" + storageInGigabytes +
                ", usersCost=" + usersCost +
                ", storageCost=" + storageCost +
                ", total=" + total +
                '}';
    }
}
