package org.air.shopping.domain;

import java.util.List;
import java.util.Random;

public class Randoms {

    public static final Random RANDOM = new Random();

    public static <T> T oneOf(List<T> items) {
        return items.get(RANDOM.nextInt(items.size()));
    }
}
