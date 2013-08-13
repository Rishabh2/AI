import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class TextEditorModel {
	
	/**
	 * The constructor for our applet.
	 */
	public TextEditorModel() {
		// any initialization for your model
	}

	public void inputText(String text) {
		System.out.println("The model just got: " + text);
	}
	
	public String openFile(String fileName) {
		File f = new File(fileName);
		String text = "";
		if (f.exists()) {	// if the file exists
			try {
				// make a new scanner that inputs this file
				Scanner s = new Scanner(f);
				// while the scanner can read a line
				while (s.hasNextLine()) {
					// add that line to our String variable
					text = text + s.nextLine() + "\n";
				}
				// finally, return the contents of text
				return text;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public void saveFile(String fileName, String text) {
		// Created a buffered writer
		FileWriter writer = null;
		try {
			writer = new FileWriter(fileName + ".txt");
			BufferedWriter bw = new BufferedWriter(writer);
			// use the buffered writer
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
