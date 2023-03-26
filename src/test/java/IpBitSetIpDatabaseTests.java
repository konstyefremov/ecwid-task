import konstyefremov.IpAddrCounter.domain.Ip;
import konstyefremov.IpAddrCounter.implementations.IpBitSetIpDatabase;
import konstyefremov.IpAddrCounter.interfaces.IpBitSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IpBitSetIpDatabaseTests {
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void ipsAreNotCountedTwice() {
        var ips = new Ip[] {
                Ip.fromString("127.255.255.255").get(),
                Ip.fromString("127.255.255.255").get(),
                Ip.fromString("255.255.255.255").get(),
                Ip.fromString("255.255.255.255").get(),
        };

        var ipBooleanStorage = mock(IpBitSet.class);
        when(ipBooleanStorage.get(any()))
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true);

        var counter = new IpBitSetIpDatabase(ipBooleanStorage);

        for (var ip : ips) {
            counter.tryRegister(ip);
        }

        assertEquals(2, counter.getTotalRegistered());
    }
}
