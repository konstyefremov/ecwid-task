package konstyefremov.IpAddrCounter.implementations;

import konstyefremov.IpAddrCounter.domain.Ip;
import konstyefremov.IpAddrCounter.interfaces.IpBitSet;
import konstyefremov.IpAddrCounter.interfaces.IpDatabase;


/**
 * This class registers IP address inside its storage implementation.
 * The same address cannot be registered twice. It is also possible
 * to get total registered addresses.
 */
public class IpBitSetIpDatabase implements IpDatabase {
    /**
     * Stores registered IP addresses
     */
    private final IpBitSet ipBitSet;

    /**
     * Stores total registered unique IP addresses
     */
    private long totalRegistered;

    /**
     * Creates a new database for storing registered IP addresses. Takes an implementation of IpBitSet to store as a dependency.
     *
     * @param ipBitSet IpBitSet to store IP addresses internally
     */
    public IpBitSetIpDatabase(IpBitSet ipBitSet) {
        this.ipBitSet = ipBitSet;
        this.totalRegistered = 0;
    }

    /**
     * Registers IP address in the database. The same address will not be registered twice.
     *
     * @param ip IP address
     */
    @Override
    public void tryRegister(Ip ip) {
        if (ipBitSet.get(ip))
            return;

        ipBitSet.set(ip, true);
        totalRegistered++;
    }

    /**
     * Total registered addresses in the database
     *
     * @return number of registered addresses
     */
    @Override
    public long getTotalRegistered() {
        return totalRegistered;
    }
}
