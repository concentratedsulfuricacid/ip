package tom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Verifies basic test infrastructure behavior.
 */
public class SanityTest {
    /**
     * Verifies the sanity assertion for arithmetic.
     */
    @Test
    public void sanityTest() {
        assertEquals(2, 1 + 1);
    }

}
