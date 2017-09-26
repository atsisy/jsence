package strpr;

import com.atilika.kuromoji.ipadic.Tokenizer;
import edu.cmu.lti.jawjaw.JAWJAW;
import edu.cmu.lti.jawjaw.pobj.POS;
import java.util.ArrayList;

/**
 * JSence Extracting string class.
 * @author Akihiro Takai
 */
public class StringExtractor {

    public static int CountNoun(String sentence){

        Tokenizer tokenizer = new Tokenizer();
        return tokenizer.tokenize(sentence).size();
    }

    public static ArrayList<Word> collectSynonym(String word, String pos_str, POS pos){

        ArrayList<String> str_data = new ArrayList<>();
        ArrayList<Word> result = new ArrayList<>();

        str_data.addAll(JAWJAW.findHypernyms(word, pos));
        str_data.addAll(JAWJAW.findHyponyms(word, pos));
        str_data.addAll(JAWJAW.findEntailments(word, pos));
        str_data.addAll(JAWJAW.findTranslations(word, pos));
        str_data.addAll(JAWJAW.findDefinitions(word, pos));

        str_data.forEach(s -> result.add(new Word(s, pos_str)));

        return result;

    }

}
