package basic.writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.Files.createDirectory;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class FolderCreatorService {
    private FolderCreatorService() {}

    public static boolean createFolder(String folderLocation, String folderName) {
        File folder = new File(folderLocation + "/" + folderName);
        folder.mkdirs();

        // add checker and if it didn't create anything, return false

        return true;
    }
}
