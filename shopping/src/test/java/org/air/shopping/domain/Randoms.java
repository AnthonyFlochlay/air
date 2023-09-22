package org.air.shopping.domain;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

public class Randoms {

    public static final Random RANDOM = new Random();

    public static <T> T oneOf(List<T> items) {
        return items.get(RANDOM.nextInt(items.size()));
    }

    public static ZonedDateTime aDateTimeBetween(ZonedDateTime start, ZonedDateTime end) {
        return anInstantBetween(start.toInstant(), end.toInstant())
                .atZone(
                        ZoneId.systemDefault()
                );
    }

    public static Instant anInstantBetween(Instant start, Instant end) {
        return Instant.ofEpochMilli(
                RANDOM.nextLong(
                        start.toEpochMilli(),
                        end.toEpochMilli()
                )
        );
    }
}
