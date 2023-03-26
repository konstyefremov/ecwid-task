package konstyefremov.IpAddrCounter.domain;

import java.util.Optional;

/**
 * This class represents IPv4 address. It intentionally uses a private constructor and relies
 * on a factory method to create a new valid instance of the class.
 */
public final class Ip {
    /**
     * Decimal representation of IPv4 address.
     * For example:
     * 0.0.0.0 will become 0 decimal
     * 255.255.255.255 will become 4,294,967,295 decimal
     */
    private final long decimal;

    /**
     * Creates a new instance of IP taking 4 numbers. Intentionally not contains any validation because relies
     * on validation in a factory method which will not allow passing invalid values to this constructor.
     *
     * @param field1 first field
     * @param field2 second field
     * @param field3 third field
     * @param field4 fourth field
     */
    private Ip(short field1, short field2, short field3, short field4) {
        this.decimal = field1 * 16777216L + field2 * 65536L + field3 * 256L + field4;
    }

    public long getDecimalValue() {
        return decimal;
    }

    /**
     * A factory method which creates an Ip object from a string representation.
     *
     * @param line the string representation of the IP address
     * @return an Optional containing the Ip object if the string representation is valid,
     *         or an empty Optional if it is not valid
     */
    public static Optional<Ip> fromString(String line) {
        var fields = line.split("\\.");
        
        if (fields.length != 4) {
            return Optional.empty();
        }

        try {
            var field1 = Short.parseShort(fields[0]);
            var field2 = Short.parseShort(fields[1]);
            var field3 = Short.parseShort(fields[2]);
            var field4 = Short.parseShort(fields[3]);

            if (areFieldsValid(field1, field2, field3, field4)) {
                return Optional.of(new Ip(field1, field2, field3, field4));
            }
        } catch (NumberFormatException e) {
            //
        }

        return Optional.empty();
    }

    private static boolean areFieldsValid(short field1, short field2, short field3, short field4) {
        return field1 >= 0 && field1 <= 255 
            && field2 >= 0 && field2 <= 255 
            && field3 >= 0 && field3 <= 255 
            && field4 >= 0 && field4 <= 255;
    }
}