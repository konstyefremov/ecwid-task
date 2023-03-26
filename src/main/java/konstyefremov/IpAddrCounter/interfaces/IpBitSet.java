package konstyefremov.IpAddrCounter.interfaces;

import konstyefremov.IpAddrCounter.domain.Ip;


/**
 * This interface uses semantics of BitSet class to store and get values of specific IP addresses which acts as indexes
 */
public interface IpBitSet {
    /**
     * Returns the boolean value stored for the given IP address.
     *
     * @param ip the IP address to get the boolean value for
     * @return the boolean value stored for the given IP address
     */
    boolean get(Ip ip);


    /**
     * Sets the boolean value for the given IP address.
     *
     * @param ip the IP address to set the boolean value for
     * @param value the boolean value to set for the given IP address
     */
    void set(Ip ip, boolean value);
}
