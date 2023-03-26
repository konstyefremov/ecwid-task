package konstyefremov.IpAddrCounter.implementations;

import konstyefremov.IpAddrCounter.domain.Ip;
import konstyefremov.IpAddrCounter.interfaces.IpBitSet;

import java.util.BitSet;

/**
 * This class implements IpBitSet on two separate BitSets for storing all possible values of IPv4. The first BitSet
 * stores values for the first 1/2 of the possible IP range. The second BitSet stores values the second 1/2 of the
 * possible range.
 */
public class InMemoryIpBitSet implements IpBitSet {
    /**
     * Stores boolean values for IP addresses with decimal values 0 - 2,147,483,646
     */
    private final BitSet bitSet1;

    /**
     * Stores boolean values for IP addresses with decimal values 2,147,483,647 - 4,294,967,295
     */
    private final BitSet bitSet2;

    /**
     * The offset used to calculate the index in the second BitSet for IP addresses with decimal values greater than 2,147,483,646
     */
    private final long BITSET2_OFFSET = (long) Integer.MAX_VALUE + 1;

    /**
     * Creates an IP bit set for storing all possible values of IPv4 range
     */
    public InMemoryIpBitSet() {
        bitSet1 = new BitSet(Integer.MAX_VALUE);
        bitSet2 = new BitSet(Integer.MAX_VALUE);
    }

    /**
     * Returns the boolean value stored for the given IP address.
     *
     * @param ip the IP address to get the boolean value for
     * @return the boolean value stored for the given IP address
     */
    public boolean get(Ip ip) {
        var ipDecimal = ip.getDecimalValue();

        if (ipDecimal <= Integer.MAX_VALUE) {
            return bitSet1.get((int) ipDecimal);
        }

        return bitSet2.get((int) (ipDecimal - BITSET2_OFFSET));
    }

    /**
     * Sets the boolean value for the given IP address.
     *
     * @param ip the IP address to set the boolean value for
     * @param value the boolean value to set for the given IP address
     */
    public void set(Ip ip, boolean value) {
        var ipDecimal = ip.getDecimalValue();

        if (ipDecimal <= Integer.MAX_VALUE) {
            bitSet1.set((int) ipDecimal, value);
        } else {
            bitSet2.set((int) (ipDecimal - BITSET2_OFFSET), value);
        }
    }
}