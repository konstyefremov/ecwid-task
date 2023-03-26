package konstyefremov.IpAddrCounter.utils;

import konstyefremov.IpAddrCounter.domain.Ip;
import konstyefremov.IpAddrCounter.interfaces.IpDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This utility class is used to process
 */
public class FileProcessor {
    private final String fileName;
    private final IpDatabase ipDatabase;

    public FileProcessor(String fileName, IpDatabase ipDatabase) {
        this.fileName = fileName;
        this.ipDatabase = ipDatabase;
    }

    public long getUniqueIpsCount() throws IOException {
        try (var reader = new BufferedReader(new FileReader(fileName))){
            var line = reader.readLine();

            while (line != null) {
                Ip.fromString(line).ifPresent(ipDatabase::tryRegister);
                line = reader.readLine();
            }

            return ipDatabase.getTotalRegistered();
        }
    }
}
