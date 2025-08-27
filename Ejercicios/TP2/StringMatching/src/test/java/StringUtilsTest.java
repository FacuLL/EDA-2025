import ar.edu.itba.eda.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testIndexOf() {
        assertEquals(2, StringUtils.indexOf("ra".toCharArray(), "abracadabra".toCharArray()));
        assertEquals(0, StringUtils.indexOf("abra".toCharArray(), "abracadabra".toCharArray()));
        assertEquals(-1, StringUtils.indexOf("aba".toCharArray(), "abracadabra".toCharArray()));
        assertEquals(-1, StringUtils.indexOf("aba".toCharArray(), "ab".toCharArray()));
        assertEquals(-1, StringUtils.indexOf("aba".toCharArray(), "xa".toCharArray()));
        assertEquals(7, StringUtils.indexOf("abras".toCharArray(), "abracadabras".toCharArray()));
    }
}
