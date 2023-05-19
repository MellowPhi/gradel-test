package readability;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Om Thapa
 */
public abstract class Document {
    private String text;

    /**
     * Create a new document from a given text
     * @param text The text content of the document
     */
    protected Document(String text) {
        this.text = text;
    }

    /**
     * Returns a tokens of a text document that match the regex pattern
     * @param pattern The regex specifying the desired token pattern
     * @return List of tokens from the documents text that match the regex pattern
     */

    protected List<String> getTokens(String pattern) {
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokenSplitter = Pattern.compile(pattern);
        Matcher m = tokenSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }

    /**
     * Helper method to check if a char is a vowel
     * @param c Char to be checked
     * @return boolean value to indicate if it is a vowel
     */
    private boolean isVowel(char c) {
        if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u') || (c == 'y')) {
            return true;
        }
        return false;
    }

    /**
     * Counts the number of vowels in a word
     * @param word
     * @return total number of vowels in a word
     */

    protected int countSyllables(String word) {
        // Convert it to lowercase
        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

//        // Define a regular expression pattern to match vowel sounds
//		Pattern pattern = Pattern.compile("[aeiouy]");
//
//		// Count the number of matches for the pattern
//		Matcher matcher = pattern.matcher(cleanWord);
//		int count = 0;
//		while (matcher.find()) {
//            // System.out.println(matcher.group());
//			count++;
//		}
//
//		// Adjust the count for special cases
//		char lastChar = cleanWord.charAt(cleanWord.length() - 1);
//		if (lastChar == 'e') {
//			count--;
//		}
//		if (count == 0) {
//			count = 1;
//		}
//
//        return count;

        int numSyllables = 0;
		boolean newSyllable = true;
		for (int i = 0; i < cleanWord.length(); i++)
		{
			if (i == word.length()-1 && cleanWord.charAt(i) == 'e'
					&& newSyllable && numSyllables > 0) {
				numSyllables--;
			}
			if (newSyllable && isVowel(cleanWord.charAt(i))) {
				newSyllable = false;
				numSyllables++;
			}
			else if (!isVowel(cleanWord.charAt(i))) {
				newSyllable = true;
			}
		}
		return numSyllables;

    }

    protected double automatedReadabilityScore() {
        return 4.71 * getNumChars() / getNumWords()
                + 0.5 * getNumWords() / getNumSentences() - 21.43;
    }

    protected double fleshScore() {
        return 0.39 * (getNumWords() / (double) getNumSentences()) + 11.8 * (getNumSyllables()[0] / (double)getNumWords()) - 15.59;
    }

    protected double smogScore() {
        return 1.042 * Math.sqrt(getNumSyllables()[1] * 30 / (double)getNumSentences()) + 3.1291;
    }

    protected double colemanLiauScore() {
        return 0.0588 *(getNumChars() / (double) getNumWords() * 100) - 0.296 * (getNumSentences() / (double) getNumWords() * 100)-15.8;
    }

    protected int readingLevel(int readScore) {
        int age = 0;
        if (readScore < 14){
            age = readScore + 5;
        } else {
            age = readScore + 8;
        }
        return age;
    }

    /**
     * @return Return the entire document text
     */
    public String getText() {
        return this.text;
    }

    public abstract int getNumSentences();

    public abstract int getNumWords();

    public abstract int getNumChars();

    public abstract int[] getNumSyllables();

}
