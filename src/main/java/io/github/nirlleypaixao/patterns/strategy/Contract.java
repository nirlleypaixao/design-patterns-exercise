package io.github.nirlleypaixao.patterns.strategy;

public class Contract {
    public final String client;
    public final String plan;

    public Contract(String client, String plan) {
        this.client = client;
        this.plan = plan;
    }
}
