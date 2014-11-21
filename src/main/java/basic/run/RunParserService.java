package basic.run;

import basic.creator.ChapterSelectorService;
import basic.reader.FileReaderService;
import basic.reader.FolderReaderService;
import basic.util.BookUtil;
import basic.writer.FileService;
import basic.writer.FolderCreatorService;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import static basic.creator.ChapterSelectorService.separateChapters;
import static basic.reader.FileReaderService.getFixedFileLines;
import static basic.reader.FolderReaderService.readFilesInFolder;
import static basic.util.BookUtil.getBookNameOnly;
import static basic.writer.FileService.createFileAndWrite;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class RunParserService {
    public static final String FOLDER_INPUT = "/input";
    public static final String FOLDER_OUTPUT = "/output";
    private static String property = System.getProperty("user.home");
    private static HashMap<String, Collection<String>> BOOKS = new HashMap<>(1000);
    private static HashMap<String, HashMap<String, Collection<String>>> CHAPTERS = new HashMap<>(100000);

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
            CHAPTERS.put(getBookNameOnly(book), chapterMap);
        }

        // create chapter files
        for (String outputFolderName : CHAPTERS.keySet()) {
            FolderCreatorService.createFolder(property + FOLDER_OUTPUT, outputFolderName);
            HashMap<String, Collection<String>> chapters = CHAPTERS.get(outputFolderName);

            for (String chapter : chapters.keySet()) {
                createFileAndWrite(property + FOLDER_OUTPUT + "/" + outputFolderName, chapter, chapters.get(chapter));
            }
        }


    }
}
