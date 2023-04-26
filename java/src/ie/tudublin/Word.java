package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> list;

    public Word(String word, ArrayList<Follow> list) {
        this.word = word;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Word [word=" + word + ", list=" + list + "]";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<Follow> getList() {
        return list;
    }

    public void setList(ArrayList<Follow> list) {
        this.list = list;
    }

    
    
}
