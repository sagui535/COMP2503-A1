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

   public static void main( String[ ] args) 
   {	
      A1 a1 = new A1();

      a1.run();
   }

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
       
       /* TODO:
        * Use collection sort to sort the wordlist in descending frequency order.
        * Then print the first 10.
        */
       // sorts the word list in descending frequency order and then print the first 10.  
   }
    private void mostFreq(){
    	 Collections.sort(wordlist, Comparator.comparing(Token::getFrequency, Comparator.reverseOrder())
    			.thenComparing(Token::getWord));

    	for (int i = 0; i < Math.min(10, wordlist.size()); i++) {
            Token token = wordlist.get(i);
            System.out.println(token.getWord() + " : " + token.getFrequency());
        }
    }
    
       /* TODO:
        * Use collection sort to sort the wordlist in ascending frequency order.
        * Then print the first 10.
        */
       // sorts the word list in ascending frequency order and then print the first 10.
       private void leastFreq() {
           // TODO: Use collection sort to sort the wordlist in ascending frequency order.
           // Then print the first 10.
           Collections.sort(wordlist, Comparator.comparing(Token::getFrequency)
           		.thenComparing(Token::getWord));

           for (int i = 0; i < Math.min(10, wordlist.size()); i++) {
               Token token = wordlist.get(i);
               System.out.println(token.getWord() + " : " + token.getFrequency());
           }
       }

       
       /* TODO:
        * Use collection sort to sort the wordlist by its natural ordering (alphabetical).
        * Then print all the words.
        */
       // sorts word list in alphabetical order and then print all words
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
	   /* TODO: read the input one word at a time, turn to lower-case, 
	    * trim the whitespaces and all other characters but a-z.
	    * If the word is a stopword, ignore it. Otherwise,
 		* Create a new token object, if not already in the wordlist, 
 		* add the token to the word list, otherwise, increase the frequency count.
 		*/
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
   
   private boolean isStopWord(String word) {
	   for (String stopword : stopwords) {
		   if (stopword.equals(word)) {
			   return true;
		   }
	   }
	   return false;
   }

   /** Run the program. Read the file, then print the results. 
   */
   public void run()
   {
      readFile();
      printResults();
   }

}
