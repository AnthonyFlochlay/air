package org.air.shopping.domain;

import java.time.ZonedDateTime;
import java.util.List;

import static org.air.shopping.domain.Randoms.oneOf;

public class DateFixtures {

    public static final ZonedDateTime NOW = ZonedDateTime.now();
    private static final List<ZonedDateTime> sampleDateTimes = List.of(
            NOW.minusMonths(1),
            NOW.minusDays(3),
            NOW.minusDays(2),
            NOW.minusDays(1),
            NOW.minusMinutes(10),
            NOW,
            NOW.plusMinutes(10),
            NOW.plusDays(1),
            NOW.plusDays(2),
            NOW.plusDays(3),
            NOW.plusMonths(1),
            NOW.plusMonths(2)
    );

    public static ZonedDateTime aZonedDateTime() {
        return oneOf(sampleDateTimes);
    }

}
