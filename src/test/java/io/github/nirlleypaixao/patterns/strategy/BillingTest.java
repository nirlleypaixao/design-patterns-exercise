package io.github.nirlleypaixao.patterns.strategy;

import com.eriksencosta.money.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingTest {
    private final Money zero = Money.Factory.of(0, "USD");
    private final Billing billing = new Billing();

    @Test
    public void testFreePlanInvoicing() {
        // Arrange
        Contract contract = new Contract("John Doe", new FreePlanPricing());
        Usage usage = new Usage(1, 0);
        Invoice expected = new Invoice("FreePlanPricing", 1, 0, zero, zero);

        // Act
        Invoice invoice = billing.invoice(contract, usage);

        // Assert
        assertEquals(expected, invoice);
    }

    @Test
    public void testBasicPlanInvoicing() {
        // Arrange
        Contract contract = new Contract("John's Small Firm", new BasicPlanPricing());
        Usage usage = new Usage(4, 0);
        Invoice expected = new Invoice(
                "BasicPlanPricing",
                4,
                0,
                Money.Factory.of(120, "USD"),
                zero);

        // Act
        Invoice invoice = billing.invoice(contract, usage);

        // Assert
        assertEquals(expected, invoice);
    }

    @Test
    public void testBusinessPlanInvoicingWithRegularStorageUsage() {
        // Arrange
        Contract contract = new Contract("John's Company", new BusinessPlanPricing());
        Usage usage = new Usage(50, 100);
        Invoice expected = new Invoice(
                "BusinessPlanPricing",
                50,
                100,
                Money.Factory.of(3500, "USD"),
                zero);

        // Act
        Invoice invoice = billing.invoice(contract, usage);

        // Assert
        assertEquals(expected, invoice);
    }

    @Test
    public void testBusinessPlanInvoicingWithOverStorageUsage() {
        // Arrange
        Contract contract = new Contract("John's Company", new BusinessPlanPricing());
        Usage usage = new Usage(50, 150);
        Invoice expected = new Invoice(
                "BusinessPlanPricing",
                50,
                150,
                Money.Factory.of(3500, "USD"),
                Money.Factory.of(75, "USD")); // USD 1.50 per GB used above 100 GB

        // Act
        Invoice invoice = billing.invoice(contract, usage);

        // Assert
        assertEquals(expected, invoice);
    }

    @Test
    public void testEnterprisePlanWithRegularStorageUsage() {
        // Arrange
        Contract contract = new Contract("John's Enterprise", new EnterprisePlanPricing());
        Usage usage = new Usage(12500, 8000);
        Invoice expected = new Invoice(
                "EnterprisePlanPricing",
                12500,
                8000,
                Money.Factory.of(1687500, "USD"), // USD 145.90 per user on this specific contract
                zero);                                              // Using the free quota, so no charge for storage

        // Act
        Invoice invoice = billing.invoice(contract, usage);

        // Assert
        assertEquals(expected, invoice);
    }

    @Test
    public void testEnterprisePlanWithOverStorageUsage() {
        // Arrange
        Contract contract = new Contract("John's Enterprise", new EnterprisePlanPricing());
        Usage usage = new Usage(12500, 70000);
        Invoice expected = new Invoice(
                "EnterprisePlanPricing",
                12500,
                70000,
                Money.Factory.of(1823750, "USD"), // USD 145.90 per user on this specific contract
                Money.Factory.of(6750, "USD"));   // USD 0.90 per GB used above the free quota

        // Act
        Invoice invoice = billing.invoice(contract, usage);

        // Assert
        assertEquals(expected, invoice);
    }

    @Test
    void testBusinessPlanWithStorage() {
        Contract contract = new Contract("Nico Corp", new BusinessPlanPricing());
        Usage usage = new Usage(50, 150);

        Billing billing = new Billing();
        Invoice invoice = billing.invoice(contract, usage);

        Money expectedUserCost = Money.Factory.of(70, "USD").times(50);
        Money expectedStorageCost = Money.Factory.of(1.50, "USD").times(50); // 50GB over free storage

        assertEquals(expectedUserCost, invoice.usersCost);
        assertEquals(expectedStorageCost, invoice.storageCost);
    }
}
