package readability;

import java.util.List;
import java.util.Scanner;
/**
 * @author Om Thapa
 *
 */
public class TextDocument extends Document
{
    Scanner sc = new Scanner(System.in);
    public TextDocument(String text) {
        super(text);
    }

    @Override
    public int getNumSentences() {
        List<String> tokens = getTokens("[^.!?]+");
        return tokens.size();
    }

    @Override
    public int getNumWords() {
        // List<String> tokens = getTokens("[^ .,!?]+");
        List<String> tokens = getTokens("\\b\\d+,\\d+\\b|\\b\\w+\\b");
        // List<String> tokens = getTokens("\\b\\w+\\b");
        return tokens.size();
    }

    @Override
    public int getNumChars() {
        List<String> tokens = getTokens("\\S");
        return tokens.size();
    }

    @Override
    public int[] getNumSyllables() {
        List<String>tokenWords = getTokens("\\b\\d+,\\d+\\b|\\b\\w+\\b");
        int totalSyllables = 0;
        int totalPolySyllables = 0;
        for (String word : tokenWords) {
            int count = countSyllables(word);
            totalSyllables += count;
            if (count > 2) {
                totalPolySyllables += 1;
            }
//			System.out.println("Total vowels found so far " + totalSyllables);
        }
        return new int[] {totalSyllables, totalPolySyllables};

    }
    protected int getARIAge() {
        return this.readingLevel((int) Math.ceil(this.automatedReadabilityScore()));
    }
    protected int getFKAge() {
        return this.readingLevel((int) Math.ceil(this.fleshScore()));
    }
    protected int getSMOGAge() {
        return this.readingLevel((int) Math.ceil(this.smogScore()));
    }
    protected int getCLAge() {
        return this.readingLevel((int) Math.ceil(this.colemanLiauScore()));
    }

    protected String getIndexScoreType(String indexType){
        switch (indexType) {
            case "ARI":
                return "Automated Readability Index: " + String.format("%.2f", this.automatedReadabilityScore()) + " (about " + getARIAge() + "-year-olds).";
            case "FK":
                return "Flesch–Kincaid readability tests: " + String.format("%.2f", this.fleshScore()) + " (about " + getFKAge() + "-year-olds).";
            case "SMOG":
                return "Simple Measure of Gobbledygook: " + String.format("%.2f", this.smogScore()) + " (about " + getSMOGAge() + "-year-olds).";
            case "CL":
                return "Coleman–Liau index: " + String.format("%.2f", this.colemanLiauScore()) + " (about " + getCLAge() + "-year-olds).";
            case "all":
                return "Automated Readability Index: " + String.format("%.2f", this.automatedReadabilityScore()) + " (about " + getARIAge() + "-year-olds)." +
                        "\nFlesch–Kincaid readability tests: " + String.format("%.2f", this.fleshScore()) + " (about " + getFKAge() + "-year-olds)." +
                        "\nSimple Measure of Gobbledygook: " + String.format("%.2f", this.smogScore()) + " (about " + getSMOGAge() + "-year-olds)." +
                        "\nColeman–Liau index: " + String.format("%.2f", this.colemanLiauScore()) + " (about " + getCLAge() + "-year-olds).";
            default:
                return "Invalid option!";
        }

    }

    public void readabilityLevel() {
        int numSentences = this.getNumSentences();
        int numWords = this.getNumWords();
        int numChars = this.getNumChars();
        int[] totalSyllables = this.getNumSyllables();
        int numSyllables = totalSyllables[0];
        int numPolySyllables = totalSyllables[1];
        System.out.println("Words: " + numWords);
        System.out.println("Sentences: " + numSentences);
        System.out.println("Characters: " + numChars);
        System.out.println("Syllables: " + numSyllables);
        System.out.println("Polysyllables: " + numPolySyllables);
        String userOption = sc.next();
        System.out.println(getIndexScoreType(userOption));



//        System.out.println("Automated Readability Index: " + String.format("%.2f", this.automatedReadabilityScore()));
//        System.out.println("Flesch–Kincaid readability tests: " + String.format("%.2f", this.fleshScore()));
//        System.out.println("Simple Measure of Gobbledygook: " + String.format("%.2f", this.smogScore()));
//        System.out.println("Coleman–Liau index: " + String.format("%.2f", this.colemanLiauScore()));

//        if (avgWords <= 10) {
//            return "EASY";
//        } else {
//            return "HARD";
//        }
    }
}
