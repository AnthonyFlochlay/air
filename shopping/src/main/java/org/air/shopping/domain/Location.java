package org.air.shopping.domain;

public record Location(String name) {

    private static Location of(String name) {
        return new Location(name);
    }
}
