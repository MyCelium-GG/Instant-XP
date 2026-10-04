package my.celium.org.instantxp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.minecraft.world.phys.Vec3;

/** Headless tests for the radius geometry. Pure vectors, no registries needed. */
class InstantXpBrainTest {
    @Test
    void orbOnPlayerIsInside() {
        assertTrue(InstantXpBrain.withinRadius(new Vec3(0, 64, 0), new Vec3(0, 64, 0), 8));
    }

    @Test
    void orbInsideRadiusCollects() {
        assertTrue(InstantXpBrain.withinRadius(new Vec3(0, 64, 0), new Vec3(3, 64, 4), 8));
    }

    @Test
    void boundaryIsInclusive() {
        // 3-4-5 triangle: exactly 5 blocks away.
        assertTrue(InstantXpBrain.withinRadius(new Vec3(0, 0, 0), new Vec3(3, 4, 0), 5));
        assertFalse(InstantXpBrain.withinRadius(new Vec3(0, 0, 0), new Vec3(3, 4, 0), 4.99));
    }

    @Test
    void orbOutsideRadiusIsIgnored() {
        assertFalse(InstantXpBrain.withinRadius(new Vec3(0, 64, 0), new Vec3(30, 64, 0), 8));
    }

    @Test
    void verticalDistanceCounts() {
        assertFalse(InstantXpBrain.withinRadius(new Vec3(0, 64, 0), new Vec3(0, 73, 0), 8));
        assertTrue(InstantXpBrain.withinRadius(new Vec3(0, 64, 0), new Vec3(0, 72, 0), 8));
    }

    @Test
    void rejectsBadInput() {
        Vec3 origin = new Vec3(0, 0, 0);
        assertFalse(InstantXpBrain.withinRadius(null, origin, 8));
        assertFalse(InstantXpBrain.withinRadius(origin, null, 8));
        assertFalse(InstantXpBrain.withinRadius(origin, origin, -1));
    }

    @Test
    void tickServerToleratesNulls() {
        // Must never throw: the tick hook runs every server tick.
        InstantXpBrain.tickServer(null, 8);
        InstantXpBrain.collectForPlayer(null, 8);
    }
}
