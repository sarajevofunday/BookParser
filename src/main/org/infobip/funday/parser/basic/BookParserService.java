package main.org.infobip.funday.parser.basic;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class BookParserService {
    public static final String FOLDER_INPUT = "\\input\\";
    public static final String FOLDER_OUTPUT = "\\output\\";

    private static String property = System.getProperty("user.home");
    private static HashMap<String, Integer> usedNamesForChapters = new HashMap<String, Integer>(100);

    public static void main(String[] args) throws Exception {
        boolean first = true;
        String folderName = "secondBookColumbus";

        BufferedReader br = null;
        try {
            System.out.println("starting reading");
            String inputFileName = property + FOLDER_INPUT + folderName + ".txt";
            br = new BufferedReader(new FileReader(inputFileName));
            System.out.println("input file: " + inputFileName);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            Collection<String> chapterLines = new ArrayList<String>(1000);
            String chapterName = "";
            System.out.println("reading lines...");
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                int lengthOfLine = line.trim().length();

                if (line.isEmpty() || lengthOfLine < 1) {
                    System.out.println("line is empty...");
                    continue;
                } else {
                    System.out.println("line size: " + lengthOfLine);
                }

                System.out.println("line: " + line + " - is chapter name: " + chechChapterName(line));

                if (chechChapterName(line) && !first) {
                    createFile(chapterName, chapterLines, folderName);

                    chapterName = line.trim();
                    chapterLines = new ArrayList<String>(1000);
                } else if (chechChapterName(line)) {
                    first = false;
                    chapterName = line.trim();
                    createFolder(folderName);
                } else {
                    System.out.println("adding line to collection...");
                    chapterLines.add(line.trim());
                }
            }
            String everything = sb.toString();
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            br.close();
        }
    }

    private static boolean chechChapterName(String line) {
        for (char c : line.toCharArray()) {
            if ((c < 'A' || c > 'Z') && c != ' ') {
                return false;
            }
        }

        return true;
    }

    private static void createFolder(String file) {
        File dir = new File(property + FOLDER_OUTPUT + file);
        dir.mkdir();
    }

    private static void createFile(String chapterName, Collection<String> chapterLines, String folderName) {
        Integer integer = usedNamesForChapters.get(chapterName);

        if (integer == null) {
            integer = new Integer(1);
        } else {
            integer = new Integer(integer + 1);
        }

        usedNamesForChapters.put(chapterName, integer);

        Collection<String> fixedLines = fixLines(chapterLines);

        if (fixedLines.isEmpty()) {
            System.out.println("no lines...");
            return;
        }

        int numberOfLines = fixedLines.size();
        int counter = 0;

        try {
            String name = property + FOLDER_OUTPUT + folderName + "\\" + chapterName + integer + ".txt";
            System.out.println("creating file: " + name);
            File file = new File (name);
            System.out.println("File created!");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            System.out.println("number of lines: " + fixedLines.size());
            for(String fixedLine : fixedLines) {
                counter++;
                output.write(fixedLine);

                if (counter != numberOfLines) {
                    output.newLine();
                }

            }

            output.close();

        } catch (Exception e) {
            System.out.println("error with output: " + e);
        }
    }

    private static Collection<String> fixLines(Collection<String> chapterLines) {
        System.out.println("fixing lines...");
        Collection<String> toReturn = new ArrayList<String>(chapterLines.size());
        System.out.println("chapterLines size: " + chapterLines.size());
        for (String line : chapterLines) {
            line = line.trim();

            String newLine = "";
            for (final char c : line.toCharArray()) {
                System.out.println("checking c=" + c + ": " + checkChar(c));
                if (checkChar(c)) {
                    newLine += c;
                }
            }

            toReturn.add(newLine);
        }

        return toReturn;
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
