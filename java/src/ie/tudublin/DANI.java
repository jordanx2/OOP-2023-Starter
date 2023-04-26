package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	ArrayList<Word> allWords = new ArrayList<>();
	
	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;

    public String[] writeSonnet()
    {
        return null;
    }

	public void loadFile(){
		String[] line = loadStrings("small.txt"); // Load a text file into a String array

		for(int i = 0; i < line.length; i++){
			String[] words = line[i].split(" ");
			int j = 0;
			while(j < words.length - 1){
				words[j] = words[j].replaceAll("[^\\w\\s]",""); // Remove punction characters
				words[j] = words[j].toLowerCase();
				if(findWord(words[j]) == null){
					allWords.add(new Word(words[j]));
				}
				if(j != words.length - 1){
					updateFollow(words[j], words[j + 1].toLowerCase().replaceAll("[^\\w\\s]",""));
				} 

				j++;
			}

			boolean found = false;
			loop: for(Word w : allWords){
				if(w.getWord().matches(words[words.length - 1])){
					found = true;
					break loop;
				}
			}
			if(!found){
				allWords.add(new Word(words[words.length - 1].toLowerCase().replaceAll("[^\\w\\s]","")));
			}
			
		}
	}


	public void updateFollow(String str, String nextStr){
		Word word = findWord(str);
		Follow follow = word.findFollow(nextStr);
		if(follow == null){
			word.getList().add(new Follow(nextStr, 1));
		} else{	
			int count = follow.getCount() + 1;
			follow.setCount(count);
		}		
	}


	public Word findWord(String str){
		for(Word w : allWords){
			if(w.getWord().matches(str)){
				return w;
			}
		}

		return null;
	}

	public void printModel(){
		for(Word word : allWords){
			System.out.println(word);
		}
	}

	public void setup() {
		colorMode(HSB);
		loadFile();
		printModel();
       
	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
        
	}
}
