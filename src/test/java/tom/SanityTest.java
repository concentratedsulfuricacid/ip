package tom;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
