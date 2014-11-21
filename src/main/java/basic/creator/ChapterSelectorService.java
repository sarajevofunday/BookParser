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

        for (String line : book) {
            if (line != null && !line.isEmpty() && !line.trim().isEmpty()) {
                if (checkIsChapterName(line)) {
                    chapterLines = new ArrayList<>(10000);
                    result.put(line.trim(), chapterLines);
                } else {
                    chapterLines.add(line.trim());
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
