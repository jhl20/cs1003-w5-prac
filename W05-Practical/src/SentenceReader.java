
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SentenceReader {

    List<String> allSentences = new ArrayList<String>();  //used at the end to store the sentences
    /**
     * Given a file path, this method will read the entire contents of the file,
     * split the text into sentences, and return a list of sentences.
     *
     * The sentence splitting is very basic: we just split on the full-stop character.
     *
     * The contents of each sentence are sanitised as well.
     * Sanitisation is done by replacing each character with the corresponding lower case character,
     * removing all punctuation characters, etc.
     *
     * @param filepath The file path for the input file
     * @return A list of all sentences in the file
     * @throws IOException May throw an IOException while reading the file
     */
    public List<String> readAllSentences(String filepath) throws IOException {
        try {
            String input = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] splitSentence = input.split("\\.");
            for (int i = 0; i < splitSentence.length; i++) {
                String sanitisedSentence = sanitiseSentence(splitSentence[i]);
                allSentences.add(sanitisedSentence);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
        return allSentences;
    }

    /**
     * Given a string, this method will sanitise it.
     * Sanitisation is done by replacing each character with the corresponding lower case character,
     * removing all punctuation characters, etc.
     *
     * @param sentence The input string
     * @return The output string
     */
    public String sanitiseSentence(String sentence) {
        List<String> words = new ArrayList<>();
        for (String word : sentence.split("\\s+")) {
            String sanitised = word.toLowerCase().replaceAll("[^a-z]+", "");
            if (!sanitised.isEmpty()) {
                words.add(sanitised);
            }
        }

        return joinWords(words);
    }

    /**
     * This is a private method to join a list of words into a sentence.
     *
     * @param words The list of words
     * @return A string which contains the words separated by spaces
     */
    private String joinWords(List<String> words) {
        String joined = "";
        if (words.size() > 0) {
            joined = words.get(0);
        }
        for (int i = 1; i < words.size(); i++) {
            joined += " " + words.get(i);
        }
        return joined;
    }
}
