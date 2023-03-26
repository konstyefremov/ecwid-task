package konstyefremov.IpAddrCounter.interfaces;

import konstyefremov.IpAddrCounter.domain.Ip;

/**
 * This interface registers IP address inside its implementation. The same address should not be registered twice.
 */
public interface IpDatabase {
    /**
     * Registers IP address in the database. The same address will not be registered twice.
     *
     * @param ip IP address
     */
    void tryRegister(Ip ip);

    /**
     * Total registered addresses in the database
     *
     * @return number of registered addresses
     */
    long getTotalRegistered();
}