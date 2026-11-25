package my.celium.org.instantxp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class XpCounterTest {
    @Test
    void quickGainsAddUpAndPausesStartOver() {
        UUID id = UUID.randomUUID();
        assertEquals(7, XpCounter.add(id, 7, 100));
        assertEquals(10, XpCounter.add(id, 3, 120));
        assertEquals(15, XpCounter.add(id, 5, 160));
        assertEquals(2, XpCounter.add(id, 2, 201));
    }

    @Test
    void deathBottleKeepsItsShare() {
        assertEquals(697, DeathBottle.keep(1395, 50));
        assertEquals(1395, DeathBottle.keep(1395, 100));
        assertEquals(0, DeathBottle.keep(-5, 50));
        assertEquals(1000000000, DeathBottle.keep(2000000000, 50));
    }
}
