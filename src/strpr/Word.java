package strpr;

/**
 * JSence just word class.
 * @author Akihiro Takai
 */
public class Word {

    private String word;
    private String part;

    public Word(String word, String part){
        this.word = word;
        this.part = part;
    }

    public String getWord(){
        return word;
    }

    public String getPart(){
        return part;
    }
}
