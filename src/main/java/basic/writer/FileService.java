package basic.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class FileService {
    private FileService() {}

    public static void createFileAndWrite(String path, String fileName, Collection<String> lines) throws IOException {
        String name = path + "/" + fileName + ".txt";

        File file = new File (name);
        BufferedWriter output = null;


        try {
            output = new BufferedWriter(new FileWriter(file));

            for(String line : lines) {
                output.write(line);
            }
        } finally {
            output.close();
        }
    }


}
