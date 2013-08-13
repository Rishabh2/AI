/**
 Provided in 2013 TechLab Education
 
 The MIT License (MIT)
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

@SuppressWarnings("serial")
/**
 * A simple text editor view.
 * @author (your name) 
 */
public class TextEditor extends JApplet 
	implements ActionListener {
	private TextEditorModel model;

	// add components here
	private JTextField field;
	private JTextArea area;
	
	// Remember whether or not we have saved our work.
	private boolean saved = false;
	private String saveFile;

	/**
	 * Called by the browser or applet viewer to inform this JApplet that it has
	 * been loaded into the system. It is always called before the first time
	 * that the start method is called.
	 */
	public void init() {
		// Create a model and view object.
		model = new TextEditorModel();
		// Set the size of this applet to the size that the view wants.
		this.setSize(new Dimension(500, 500));
		// Make a new content pane to organize what we put into the applet - since we only have
		// a JTextArea, we make a 1 x 1 grid to ensure the text area resizes to the entire window.
		this.setLayout(new GridLayout(1, 1));
		
		initializeComponents();
	}

	/**
	 * Initializes the components of this applet.
	 */
	public void initializeComponents() {
		// Initialize the buttons.
        
        // Initializing a text area.
		area = new JTextArea(30, 30);
		add(area);
	}

	/**
	 * This method is called whenever a component with an action value is
	 * activated.
	 */
	public void actionPerformed(ActionEvent e) {
        
	}
}
