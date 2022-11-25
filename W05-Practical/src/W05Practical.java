
import java.io.*;
import java.util.*;

public class W05Practical {
    public static void main(String[] args) throws IOException {
        try {
            if (args.length <= 2) {
                SentenceReader reader = new SentenceReader();
                List<String> sentences = reader.readAllSentences(args[0]);
                List<String> bigramCandidate = new ArrayList<>();
                HashSet<String> candidate;
                List<String> bigramQuery = new ArrayList<>();
                HashSet<String> query;
                double jaccardIndex = 0;
                List<ScoredResult<String>> scores = new ArrayList<>();
                for (int i = 0; i < sentences.size(); i++) {
                    int intersection = 0;
                    bigramQuery = bigramize(reader.sanitiseSentence(args[1]));
                    String sentencesToBigram = sentences.get(i);
                    bigramCandidate = bigramize(sentencesToBigram);
                    query = new HashSet<String>(bigramQuery);
                    candidate = new HashSet<String>(bigramCandidate);
                    for (String j : candidate) {
                        if (query.contains(j)) {
                            intersection++;
                        }
                    }
                    int union = candidate.size() + query.size();
                    jaccardIndex = (double) intersection / (union - intersection);  //jaccard index calculation
                    scores.add(new ScoredResult(sentences.get(i), jaccardIndex));
                }
                Collections.sort(scores);                               //sorting ensures that the highest similarity score will be printed first
                for (int k = 0; k < 50; k++) {                          //for loop to get the first 50 sentences
                    System.out.println(scores.get(k));
                }
            } else {
                System.out.println("Check that you have quotation marks around the query");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java W03Practical <input_file> <query_string>");
        }
    }

    //bigramize method is used to convert the sentences to bigrams
    public static List<String> bigramize(String sentence) {
        ArrayList<String> bigram = new ArrayList<>();
        for (int i = 0; i < sentence.length() - 1; i++) {       //sentence.length() - 1 is to avoid array out of bounds
            bigram.add(sentence.substring(i, i + 2));           //i+2 is used as the endIndex is exclusive
        }
        return bigram;
    }
}