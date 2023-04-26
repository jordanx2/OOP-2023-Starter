package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {

	ArrayList<Word> allWords = new ArrayList<>();
	
	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}
	String[] sonnet = new String[14];

    public String[] writeSonnet()
    {
		String[] son = new String[14];
		int numLines = 14;
		int i = 0;
		int j = 0;
		String sentence = "";
		
		while(i < numLines){
			Word randomWord = allWords.get((int)(random(0, allWords.size() - 1)));
			randomWord = findWord(randomWord.getWord());
			sentence += randomWord.getWord();

			if(!randomWord.isFollowsEmpty()){
				Word s = randomWord;
				inner: while(j < 8){
					Follow follow = s.getList().get((int) (random(0, s.getList().size())));
					sentence += " " + follow.getWord();
					s = findWord(follow.getWord());
					if(s == null || s.isFollowsEmpty()){	
						break inner;
					}
					j++;
				}	
				j = 0;
			}

			son[i] = sentence;
			sentence = "";
			i++;

		}

        return son;
    }

	public void loadFile(){
		String[] line = loadStrings("shakespere.txt"); // Load a text file into a String array

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
		background(0);
		colorMode(HSB);
		loadFile();
		System.out.println("PRINTING MODEL....");
		printModel();
		sonnet = writeSonnet();

		System.out.println("\nWRITING SONNET....");
		for(String s: sonnet){
			System.out.println(s);
		}
		
	}

	public void keyPressed() {
		if(keyCode == ' '){
			sonnet = writeSonnet();
		}
	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
		translate(width / 2, height / 2);
		translate(0, -200);
		int increment = 0;
		for(String s : sonnet){
			text(s, 0, increment );
			increment += 25;

		}

        
	}
}
