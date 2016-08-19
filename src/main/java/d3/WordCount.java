package d3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCount {
    private static class Page {
        private String text;

        public Page(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    private static final HashMap<String, Integer> counts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        List<Page> pages = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(WordCount.class.getResourceAsStream("/harold"))
            );

            String line = reader.readLine();

            while (line != null) {
                StringBuilder sb = new StringBuilder();

                IntStream.range(1, 1000).forEach((a) -> {
                    try {
                        sb.append(reader.readLine());
                        sb.append(" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                line = reader.readLine();
                pages.add(new Page(sb.toString().replaceAll("[^A-Za-z0-9 ]", "")));
            }
        }

        System.out.println("pages: " + pages.size());

        LocalTime start = LocalTime.now();

        for (Page page : pages) {
            StringTokenizer st = new StringTokenizer(page.getText());
            while (st.hasMoreTokens()) countWord(st.nextToken().toLowerCase());
        }
        LocalTime end = LocalTime.now();
        System.out.println(Duration.between(start, end).toMillis() + "ms");

//        counts
//            .entrySet()
//            .stream()
//            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//            .filter((e) -> e.getKey().length() > 5)
//            .limit(30)
//            .forEach((a) -> System.out.printf("[%s]=[%d]%n", a.getKey(), a.getValue()));
    }

    private static void countWord(String word) {
        Integer currentCount = counts.get(word);
        if (currentCount == null) counts.put(word, 1);
        else counts.put(word, currentCount + 1);
    }
}
