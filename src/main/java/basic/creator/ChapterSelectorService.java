package basic.creator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author aisajbegovic
 * @since 21.11.2014
 */
public class ChapterSelectorService {

    private ChapterSelectorService() {}

    public static HashMap<String, Collection<String>> separateChapters(Collection<String> book) {
        Collection<String> chapterLines = new ArrayList<>();
        HashMap<String, Collection<String>> result  = new HashMap<>(1000);
        HashMap<String, Integer> usedNamesForChapters = new HashMap<String, Integer>(100);

        for (String line : book) {
            if (line != null && !line.isEmpty() && !line.trim().isEmpty()) {
                line = line.trim();
                if (checkIsChapterName(line)) {
                    chapterLines = new ArrayList<>(10000);

                    if (usedNamesForChapters.containsKey(line)) {
                        Integer integer = usedNamesForChapters.get(line);

                        usedNamesForChapters.put(line, integer + 1);

                        line = line + usedNamesForChapters.get(line);
                    } else {
                        usedNamesForChapters.put(line, new Integer(1));
                        line = line + "1";
                    }

                    result.put(line, chapterLines);
                } else {
                    chapterLines.add(line);
                }
            }
        }

        return result;
    }

    public static boolean checkIsChapterName(String line) {
        for (char c : line.toCharArray()) {
            if ((c < 'A' || c > 'Z') && c != ' ' && c != '.' && c != '!') {
                return false;
            }
        }

        return true;
    }
}
