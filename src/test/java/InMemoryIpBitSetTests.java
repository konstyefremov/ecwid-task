import konstyefremov.IpAddrCounter.domain.Ip;
import konstyefremov.IpAddrCounter.implementations.InMemoryIpBitSet;
import konstyefremov.IpAddrCounter.interfaces.IpBitSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryIpBitSetTests {
    static IpBitSet ipBitSet;

    @BeforeAll
    public static void setUp() {
        ipBitSet = new InMemoryIpBitSet();
    }

    @ParameterizedTest
    @MethodSource("getIps")
    public void IpsAreSavedAndRetrieved(Ip ip) {
        ipBitSet.set(ip, true);
        assertTrue(ipBitSet.get(ip));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static Ip[] getIps() {
        return new Ip[] {
                Ip.fromString("0.0.0.0").get(),
                Ip.fromString("127.255.255.255").get(),
                Ip.fromString("128.0.0.0").get(),
                Ip.fromString("255.255.255.255").get(),
        };
    }
}
