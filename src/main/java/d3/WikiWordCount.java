package d3;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;

import d3.common.*;

public class WikiWordCount {
    private static final HashMap<String, Integer> counts = new HashMap<>();

    public static void main(String[] args) {
        Iterable<Page> pages = new Pages(10000, args[0]);

        LocalTime start = LocalTime.now();
        for (Page page : pages) {
            Iterable<String> words = new Words(page.getText());
            for (String word : words) countWord(word);
        }
        LocalTime end = LocalTime.now();
        System.out.println(Duration.between(start, end));

        counts.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .limit(30)
            .forEach((a) -> System.out.printf("[%s]=[%d]%n", a.getKey(), a.getValue()));
    }

    private static void countWord(String word) {
        Integer currentCount = counts.get(word);
        if (currentCount == null) counts.put(word, 1);
        else counts.put(word, currentCount + 1);
    }
}
