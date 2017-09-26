package strpr;

import com.atilika.kuromoji.ipadic.Tokenizer;
import edu.cmu.lti.jawjaw.JAWJAW;
import edu.cmu.lti.jawjaw.pobj.POS;

import java.util.ArrayList;
import java.util.Set;

public class StringExtractor {

    public static int CountNoun(String sentence){

        Tokenizer tokenizer = new Tokenizer();
        return tokenizer.tokenize(sentence).size();
    }

    public static ArrayList<String> collectSynonym(String word, POS pos){

        ArrayList<String> result = new ArrayList<>();

        result.addAll(JAWJAW.findHypernyms(word, pos));
        result.addAll(JAWJAW.findHyponyms(word, pos));
        result.addAll(JAWJAW.findEntailments(word, pos));
        result.addAll(JAWJAW.findTranslations(word, pos));
        result.addAll(JAWJAW.findDefinitions(word, pos));

        return result;

    }

}
