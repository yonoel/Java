package forkJoinTool;

import java.util.Random;

public class DocumentMock {
    private String[] words = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"};

    public String[][] generateDocument(int numLine, int numWords, String word) {
        int counter = 0;
        String[][] doc = new String[numLine][numWords];
        Random random = new Random();

        for (int i = 0; i < numLine; i++) {
            for (int j = 0; j < numWords; j++) {
                int index = random.nextInt(words.length);
                doc[i][j] = words[index];
                if (doc[i][j].equals(word)) {
                    counter++;
                }
            }
        }
        System.out.println("DocumentMock: the word appears " + counter + " time in the doc");
        return doc;
    }
}
