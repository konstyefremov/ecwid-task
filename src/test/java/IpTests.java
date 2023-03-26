import konstyefremov.IpAddrCounter.domain.Ip;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IpTests {
    @ParameterizedTest
    @MethodSource("getValidIpStrings")
    public void validIpsAreParsed(String ip) {
        assertTrue(Ip.fromString(ip).isPresent());
    }

    @ParameterizedTest
    @MethodSource("getInvalidIpStrings")
    public void invalidIpsAreNotParsed(String ip) {
        assertTrue(Ip.fromString(ip).isEmpty());
    }

    private static String[] getValidIpStrings() {
      return new String[]{
                "0.0.0.0",
                "127.255.255.255",
                "128.0.0.0",
                "255.255.255.255"
        };
    }

    private static String[] getInvalidIpStrings() {
        return new String[]{
                "some string",
                "",
                "256.0.0.0",
                "a.1.2.3",
                "120.120.120.120.120"
        };
    }
}
