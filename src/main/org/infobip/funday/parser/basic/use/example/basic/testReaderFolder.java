package main.org.infobip.funday.parser.basic.use.example.basic;

import main.org.infobip.funday.parser.basic.reader.FolderReaderService;

import java.io.IOException;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class testReaderFolder {
    private static String property = System.getProperty("user.home");

    public static void main(String[] args) {
        String bookName = "";
        try {
            FolderReaderService.readFilesInFolder(property + "\\input");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
