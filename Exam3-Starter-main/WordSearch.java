import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class WordSearch {
    public static void main(String[] args) throws IOException {
        // File Reading
        String[] words = Files.readString(Paths.get(args[0])).split("\n");
        String[] documents = new String[args.length-1]; 
        for(int i = 0; i < args.length-1; i++) {
            documents[i] = Files.readString(Paths.get(args[i+1]));
        } 

        // Initializing variables  
        List<List<String>> listOfMatchingDocuments = new ArrayList<List<String>>();
        List<List<String>> listOfContainedWords = new ArrayList<List<String>>();
        for (int i = 0; i< documents.length; i++) { listOfContainedWords.add( new ArrayList<String>()); }
        String mostRelevantSearchTerm = "";
        int maxQueries = -1;

        // Matching Code
        for(String word : words) {
            List<String> matchingDocuments = new ArrayList<>();
            for(int i = 0; i < documents.length; i++) {
                String document = documents[i];
                if (document.contains(word)) {
                    matchingDocuments.add(args[i+1]);
                    listOfContainedWords.get(i).add(word);
                }
            }  
            if (matchingDocuments.size() > maxQueries) {
                mostRelevantSearchTerm = word;
                maxQueries = matchingDocuments.size();
            }   
            listOfMatchingDocuments.add(matchingDocuments);
        }

        // Find most relevant document
        int maxIndex = 0;
        int maxWords = -1;
        for (int i = 0; i < documents.length; i++) {
            if (listOfContainedWords.get(i).size() > maxWords) {
                maxIndex = i;
                maxWords = listOfContainedWords.get(i).size();
            }
        }
        String mostRelevantDocument = args[maxIndex + 1];
        
        // Output
        System.out.println("Most relevant search term: " + mostRelevantSearchTerm);
        System.out.println("Most relevant document: " + mostRelevantDocument + "\n");

        for(int i = 0; i < words.length; i++) {
            System.out.println(words[i] + ": " + listOfMatchingDocuments.get(i).size() + " " + listOfMatchingDocuments.get(i));
        }
        System.out.println();
        for(int i = 0; i < documents.length; i++) {
            System.out.println(args[i+1] + ": " + listOfContainedWords.get(i).size() + " " + listOfContainedWords.get(i));
        }
    }
}