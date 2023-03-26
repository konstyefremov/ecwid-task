package konstyefremov.IpAddrCounter;

import konstyefremov.IpAddrCounter.implementations.InMemoryIpBitSet;
import konstyefremov.IpAddrCounter.implementations.IpBitSetIpDatabase;
import konstyefremov.IpAddrCounter.utils.FileProcessor;

import java.io.*;

/**
 * This console program counts unique IPv4 addresses in the provided file. It can process very big files (100Gb+)
 * IP file should have IP addresses each placed on a new line. If some lines are not valid IPv4, the program will ignore them.
 * Usage:
 * IpAddrCounter FILENAME
 */
public class IpAddrCounter {
    public static void main(String[] args) {
        if (args.length > 0) {
            var fileName = args[0];

            var ipBitSet = new InMemoryIpBitSet();
            var ipDatabase = new IpBitSetIpDatabase(ipBitSet);

            try {
                var fileProcessor = new FileProcessor(fileName, ipDatabase);
                var uniqueIpsCount = fileProcessor.getUniqueIpsCount();
                System.out.printf("The file \"%s\" contains %d unique IPs", fileName, uniqueIpsCount);
            } catch (FileNotFoundException e) {
                System.out.printf("The file \"%s\" does not exist", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("IpAddrCounter version 1.0.0");
            System.out.println();
            System.out.println("Usage:");
            System.out.println();
            System.out.println("IpAddrCounter FILE - Count unique IPv4 in a file");
        }
    }
}