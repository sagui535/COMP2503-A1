package application;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.Comparator; 
import application.Token;

/** 
 * COMP 2503 Winter 2024 Assignment 1 
 * 
 * This program reads a text file and compiles a list of the 
 * words together with how many each word appears. 
 *
 * Words from a standard list of stop words are not included.
*/
public class A1 
{
   
   private ArrayList<Token> wordlist = new ArrayList<Token>();

   private String[] stopwords = { "a", "about", "all", "am", "an", 
         "and", "any", "are", "as", "at", "be", "been", "but", "by", "can", 
         "cannot", "could", "did", "do", "does", "else", "for", "from", 
         "get", "got", "had", "has", "have", "he", "her", "hers", "him", 
         "his", "how", "i", "if", "in", "into", "is", "it", "its", "like", 
         "more", "me", "my", "no", "now", "not", "of", "on", "one", 
         "or", "our", "out", "said", "say", "says", "she", "so", "some",
         "than", "that", "the", "their", "them", "then", "there", "these", 
         "they", "this", "to", "too", "us", "upon", "was", "we", "were", 
         "what", "with", "when", "where", "which", "while", "who", 
          "whom", "why", 
         "will", "you", "your"};

   private int totalwordcount = 0;

   private int stopwordcount = 0;

   private Scanner input = new Scanner( System.in);
   
   /**
    * The main method to execute the program. Initializes the A1.
    * 
    * @param args Not used in this application.
    */
   public static void main(String[ ] args) 
   {	
      A1 a1 = new A1();

      a1.run();
   }

   /**
    * Prints the results of the words. This includes total words count, unique words count,
    * stop words count, and lists of most frequent, least frequent, and all words sorted alphabetically.
    */
   private void printResults() 
   {
       System.out.println( "Total Words: " + totalwordcount);
       System.out.println( "Unique Words: " + wordlist.size()); 
       System.out.println( "Stop Words: " + stopwordcount);
       System.out.println();
       System.out.println( "10 Most Frequent");
       mostFreq(); 
       System.out.println();
       System.out.println( "10 Least Frequent"); 
       leastFreq();
       System.out.println();
       System.out.println( "All");
       sortAll();
   }
   
   /**
    * Sorts the word list in descending order of frequency and prints the first 10 words.
    */
    private void mostFreq(){
    	 Collections.sort(wordlist, Comparator.comparing(Token::getFrequency, Comparator.reverseOrder())
    			.thenComparing(Token::getWord));

    	for (int i = 0; i < Math.min(10, wordlist.size()); i++) {
            Token token = wordlist.get(i);
            System.out.println(token.getWord() + " : " + token.getFrequency());
        }
    }
    
    /**
     * Sorts the word list in ascending order of frequency and prints the first 10 words.
     */
       private void leastFreq() {
           Collections.sort(wordlist, Comparator.comparing(Token::getFrequency)
           		.thenComparing(Token::getWord));

           for (int i = 0; i < Math.min(10, wordlist.size()); i++) {
               Token token = wordlist.get(i);
               System.out.println(token.getWord() + " : " + token.getFrequency());
           }
       }

       /**
        * Sorts the word list alphabetically and prints all words with their frequencies.
        */
       private void sortAll() {
    	   Collections.sort(wordlist, Comparator.comparing(Token::getWord));

    	    for (Token token : wordlist) {
    	        if (!token.getWord().isEmpty()) {
    	            System.out.println(token.getWord() + " : " + token.getFrequency());
    	        }
    	    }   
   }

   /**  
   Read the input file and process it. 
   Input is on standard input and is read one 
   word at a time -- separated by whitespace. 
 
   All non alphabetic characters are stripped out and 
   words are all converted to lower case. 
  
   Any non-stopword word is stored in the list of words 
   and the number of occurances is tracked.
   */
   private void readFile() 
   {
	   while (input.hasNext()) {
           String word = input.next().toLowerCase().replaceAll("[^a-z]", "");

           if (!word.isEmpty()) {
               if (isStopWord(word)) {
                   stopwordcount++;
                   totalwordcount++;
               } else {
                   processToken(word);
               }
           }
	   }
   }
   
   /**
    * Processes a single word: checks if it's already in the word list and updates frequency, or adds it
    * if it's not present.
    * 
    * @param word The word to process.
    */
   private void processToken(String word) {
       // Check if the word is already in the wordlist
       Token token = new Token(word);
       int index = wordlist.indexOf(token);

       if (index != -1) {
           // Word is already in the list, increase the frequency count
           wordlist.get(index).incrementFreq();
       } else {
           // Word is not in the list, add a new token to the wordlist
           wordlist.add(token);
       }
       totalwordcount++;
   }
   
   /**
    * Determines whether a given word is a stop word.
    * 
    * @param word The word to check.
    * @return true if the word is a stop word, false otherwise.
    */
   private boolean isStopWord(String word) {
	   for (String stopword : stopwords) {
		   if (stopword.equals(word)) {
			   return true;
		   }
	   }
	   return false;
   }

   /**
    * The main method that drives the program. It reads the file and then prints the results.
    */
   public void run()
   {
      readFile();
      printResults();
   }
}
