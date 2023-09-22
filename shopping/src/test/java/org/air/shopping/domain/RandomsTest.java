package org.air.shopping.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RandomsTest {

    @Test
    void should_generate_instant_in_range() {
        var start = Instant.now();
        var end = start.plusSeconds(100);
        assertThat(
                Randoms.anInstantBetween(start, end)
        ).isBetween(start, end);
    }

    @Test
    void should_generate_date_time_in_range() {
        var start = ZonedDateTime.now();
        var end = start.plusMinutes(1);
        assertThat(
                Randoms.aDateTimeBetween(start, end)
        ).isBetween(start, end);
    }
}