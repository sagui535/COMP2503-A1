package application;

public class Token implements Comparable<Token>{
	private String word;
	private int frequency;
	
	public Token(String word) {
		this.word = word;
		this.frequency = 1;
	}
	
	// setter getters

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public void incrementFreq() {
		frequency++;
	}
	
	// Comparable interface implementation
	@Override
	public int compareTo(Token other) {
	    return Integer.compare(this.frequency, other.frequency);
	}


    // Equals method override
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Token other = (Token) obj;
        return this.word.equals(other.word);
    }
}



