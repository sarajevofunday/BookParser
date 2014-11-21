package basic.run;

import basic.creator.ChapterSelectorService;
import basic.reader.FileReaderService;
import basic.reader.FolderReaderService;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import static basic.creator.ChapterSelectorService.separateChapters;
import static basic.reader.FileReaderService.getFixedFileLines;
import static basic.reader.FolderReaderService.readFilesInFolder;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class RunParserService {
    public static final String FOLDER_INPUT = "/input";
    public static final String FOLDER_OUTPUT = "/output";
    private static String property = System.getProperty("user.home");
    private static HashMap<String, Collection<String>> BOOKS = new HashMap<>(1000);

    public static void main(String[] args) throws IOException {
        // get books
        Collection<String> bookNames = readFilesInFolder(property + FOLDER_INPUT);

        // collect books
        for (String book : bookNames) {
            Collection<String> fixedFileLines = getFixedFileLines(property + FOLDER_INPUT + "/" +  book);
            BOOKS.put(book, fixedFileLines);
        }

        // create chapters
        for (String book : bookNames) {
            Collection<String> bookLines = BOOKS.get(book);
            HashMap<String, Collection<String>> chapterMap = separateChapters(bookLines);

//            for (String chapter : chapterMap.keySet()) {
//                System.out.println("book=" + book + ", chapter=" + chapter + ": " + chapterMap.get(chapter));
//            }
        }

        

        // create chapter files
    }
}
