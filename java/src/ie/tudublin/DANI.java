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
			for(int j = 0; j < words.length; j++){
				words[j] = words[j].replaceAll("[^\\w\\s]",""); // Remove punction characters
				words[j] = words[j].toLowerCase();
				allWords.add(new Word(words[j]));

			}
		}


		for(Word w : allWords){
			System.out.println(w);
		}
	}

	public void setup() {
		colorMode(HSB);
		loadFile();
       
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
