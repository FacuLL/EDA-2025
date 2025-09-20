import ar.edu.itba.eda.ProximityIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProximityIndexTest {

    ProximityIndex index;

    @BeforeEach
    void initialize() {
        index = new ProximityIndex();
        index.initialize(new String[] {"Ana", "Carlos", "Juan", "Yolanda"});
    }

    @Test()
    @DisplayName("No encontrado")
    void notFoundTest() {
        assertNull(index.search("Alfredo", 2));
        assertNull(index.search("holaquetalsoyfacundo", 3));
        assertNull(index.search("holaq", -5));
    }

    @Test
    @DisplayName("Encontrado")
    void foundTest() {
        assertEquals("Juan", index.search("Carlos", 1));
        assertEquals("Ana", index.search("Ana", 0));
        assertEquals("Ana", index.search("Carlos", -1));
        assertEquals("Yolanda", index.search("Juan", 1));
    }

    @Test
    @DisplayName("Circular Offset")
    void circularOffsetTest() {
        assertEquals("Carlos", index.search("Yolanda", 2));
        assertEquals("Yolanda", index.search("Ana", 3));
        assertEquals("Juan", index.search("Juan", 4));
        assertEquals("Yolanda", index.search("Ana", -1));
    }
}
