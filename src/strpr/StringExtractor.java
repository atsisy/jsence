package strpr;

import com.atilika.kuromoji.ipadic.Tokenizer;

public class StringExtractor {

    public static int CountNoun(String sentence){

        Tokenizer tokenizer = new Tokenizer();
        return tokenizer.tokenize(sentence).size();
    }

}
