package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> list;

    public Word(String word) {
        this.word = word;
        this.list = new ArrayList<>();
    }

    public Follow findFollow(String str){
        for(Follow follow : list){
            if(follow.getWord().matches(str)){
                return follow;
            }
        }
        return null;
    }

    public String printFollows(){
        String allFollows = "";
        for(Follow follow : list){
            allFollows += follow.toString() + " ";
        }

        return allFollows;
    }

    @Override
    public String toString() {
        return word + ": " +  printFollows();
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
