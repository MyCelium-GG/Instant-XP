package my.celium.org.instantxp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import my.celium.org.Mycel;
import my.celium.org.internal.PlatformHolder;

/** The mod boots on Mycel with a tiny, well-defaulted config. */
class InstantXpModTest {
    @BeforeAll
    static void initOnce(@TempDir Path tempDir) {
        PlatformHolder.setForTesting(new InstantXpTestPlatform(tempDir));
        InstantXpMod.init();
    }

    @Test
    void registersWithMycel() {
        assertTrue(Mycel.getRegisteredMods().contains(InstantXpMod.META));
        assertEquals("instantxp", InstantXpMod.META.id());
    }

    @Test
    void configDefaults() {
        assertTrue(InstantXpMod.enabled.get());
        assertEquals(8, InstantXpMod.radius.get());
        assertTrue(InstantXpMod.enabled.isDefault());
        assertEquals(8, InstantXpMod.effectiveRadius());
    }

    @Test
    void enableToggleFlowsThrough() {
        assertTrue(InstantXpMod.isModEnabled());
        Mycel.setEnabled(InstantXpMod.ID, false);
        try {
            assertTrue(!InstantXpMod.isModEnabled());
        } finally {
            Mycel.setEnabled(InstantXpMod.ID, true);
        }
        assertTrue(InstantXpMod.isModEnabled());
    }

    @Test
    void tickServerToleratesNullServer() {
        // Loader hooks must never crash the tick.
        InstantXpMod.tickServer(null);
    }
}
