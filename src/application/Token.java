package application;

/**
 * Represents a token (word) and its frequency count.
 * Implements the Comparable interface to allow sorting based on frequency.
 */
public class Token implements Comparable<Token>{
	private String word;
	private int frequency;
	
    /**
     * Creates a new Token with the specified word.
     * Initially the frequency is set to 1.
     * 
     * @param word The word this token represents.
     */
	public Token(String word) {
		this.word = word;
		this.frequency = 1;
	}
	
	// setter getters
	
    /**
     * Returns the word for this token.
     * 
     * @return The word of this token.
     */
	public String getWord() {
		return word;
	}

    /**
     * Sets the word for this token.
     * 
     * @param word The word to set for this token.
     */
	public void setWord(String word) {
		this.word = word;
	}

    /**
     * Returns the frequency of this token.
     * 
     * @return The frequency of the token.
     */
	public int getFrequency() {
		return frequency;
	}

    /**
     * Sets the frequency of this token.
     * 
     * @param frequency The frequency to set for this token.
     */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
    /**
     * Increments the frequency of this token by one.
     */
	public void incrementFreq() {
		frequency++;
	}
	
    /**
     * Compares this token to another token based on frequency.
     * 
     * @param other The token to be compared with.
     * @return A negative integer, zero, or a positive integer as this token 
     *         is less than, equal to, or greater than the specified token.
     */
	@Override
	public int compareTo(Token other) {
	    return Integer.compare(this.frequency, other.frequency);
	}


    /**
     * Indicates whether some other object is "equal to" this one.
     * Two words are equal if they are the same.
     * 
     * @param obj The reference object to compare.
     * @return true if this object is the same as the obj argument.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Token other = (Token) obj;
        return this.word.equals(other.word);
    }
}



