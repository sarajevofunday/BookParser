package parser.basic.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 *
 * using java 8 API
 */
public class FolderReaderService {
    private FolderReaderService() {}

    public static Collection<String> readFilesInFolder(String folder) throws IOException {
        Collection<String> files = new ArrayList<String>(100);

        Files.walk(Paths.get(folder)).forEach(filePath -> {
           if (Files.isRegularFile(filePath) && checkExtension(filePath)) {
               files.add(filePath.getFileName().toString());
           }
        });

        return files;
    }

    private static boolean checkExtension(Path filePath) {
        // TODO: add checker for extension
        return true;
    }

}
