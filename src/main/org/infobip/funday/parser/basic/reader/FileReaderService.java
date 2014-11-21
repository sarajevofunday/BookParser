package main.org.infobip.funday.parser.basic.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class FileReaderService {
    public static int EXPECTED_NUMBER_OF_LINES = 100000;
    private FileReaderService() {}

    public static Collection<String> getFixedFileLines(String file) throws IOException {
        Collection<String> lines = new ArrayList<String>(EXPECTED_NUMBER_OF_LINES);

        File fileInput = new File(file);
        FileReader fileReader = new FileReader(fileInput);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferReader.readLine()) != null) {
            String fixedLine = fixLine(line);

            if (!fixedLine.isEmpty()) {
                lines.add(fixedLine);
            }
        }

        return lines;
    }

    public static String fixLine(String line) {
        String trimedLine = line.trim();
        String newLine = "";

        for (final char c : trimedLine.toCharArray()) {
            if (checkChar(c)) {
                newLine += c;
            }
        }

        return newLine;
    }

    private static boolean checkChar(char c) {
        if (Character.isLetter(c)) {
            return true;
        }

        if (c == '.' || c == ',' || c == '?' || c == '!' || c == ';' || c == ':' || c == ' ') {
            return true;
        }

        return false;
    }
}
