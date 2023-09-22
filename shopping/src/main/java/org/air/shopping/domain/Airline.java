package org.air.shopping.domain;

public record Airline(String name) {

    public static Airline of(String name) {
        return new Airline(name);
    }
}
