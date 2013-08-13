import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PhotoEditor {

	static JFrame mainFrame;
	static JFileChooser chooser = null;
	static Map<JFrame, BufferedImage> frameMap = new HashMap<JFrame, BufferedImage>();
	static JFrame backgroundFrame;
	
	static int[][] sourcePixels;
	static JFrame lastSourceFrame;
	
	public static void example() {
		
		ArrayList <Integer> list = new ArrayList <Integer> ();
		list.add(1);	// Add a 1 to the end of the list.
		list.add(5);	// Add a 5 to the end of the list.
		list.get(0);	// Get the first (zero-eth) element of the list.
		
	}
	
	
	
	
	static void createFileChooser() {
		try {
			if(chooser == null)
				chooser = new JFileChooser();
		} catch (Exception ex) {
			System.out.println("Failed to create JFileChooser:" + ex.getMessage());
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Sorry selecting files and directories is not supported on your operating system. Photoscoop.java");
			// See http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6544857
			// Fixed in JDK6 update 18
		}
	}

	public static void main(String[] args) {
		File[] files = new File("testimages").listFiles();
		for (File f : files) {
			String name = f.getName().toLowerCase();
			if (name.endsWith(".jpg") || name.endsWith(".png"))
				openImageFile(f.getPath());
		}
	}

	/**
	 * Opens a file and displays the image. This method is used by main and open
	 * item.
	 * 
	 * @param filename
	 *            the file to be opened
	 */
	private static void openImageFile(String filename) {
		// Delegate the problem of reading the filename to another method!
		BufferedImage img = ImageUtilities.loadImage(filename);
		if (img == null) {
			JOptionPane.showMessageDialog(null, filename + " could not be opened.");
		} else
			openImage(img, filename); // create the window with a title - see
		// below
	}

	/**
	 * Creates a window ('jframe') and performs the necessary voodoo to display
	 * the window and a menu too.
	 */
	private static void openImage(BufferedImage img, String title) {
		JFrame frame = new JFrame();
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle(title);
		addMenus(frame);
		setImageForFrame(frame, img);
		frame.pack(); // calculate its size
		frame.setVisible(true); // display it
	}

	private static void setImageForFrame(JFrame frame, BufferedImage img) {
		// We track the mapping between a frame and an image
		frameMap.put(frame, img);
		frame.getContentPane().removeAll();
		// remove the old image (if it exists)
		// wrap the image up as an ImageIcon inside a JLabel...
		JLabel label;
		if (img != null)
			label = new JLabel(new ImageIcon(img));
		else
			label = new JLabel("No image");
		// And add the label to the frame:
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack(); // auto resize if the img has changed size
	}

	/**
	 * Add drop-down menus to the JFrame
	 * 
	 * @param frame
	 */
	public static void addMenus(final JFrame frame) {
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu effectsMenu = new JMenu("Effects");
		JMenu combineMenu = new JMenu("Combine");
		JMenu stenographyMenu = new JMenu("Stenography");
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(effectsMenu);
		menubar.add(combineMenu);
		menubar.add(stenographyMenu);

		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveAsItem = new JMenuItem("SaveAs");
		JMenuItem quitItem = new JMenuItem("Exit");
		JMenuItem backgroundItem = new JMenuItem("Use as background");
		JMenuItem undoItem = new JMenuItem("Undo");
		JMenuItem copyItem = new JMenuItem("Copy");
		JMenuItem pasteItem = new JMenuItem("Paste");
		JMenuItem captureScreenItem = new JMenuItem("Capture Screen");

		fileMenu.add(openItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(quitItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(captureScreenItem);
		editMenu.add(undoItem);

		combineMenu.add(backgroundItem);
		/*
		 * Inner anonymous classes hold the code to perform the operation.
		 */
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				createFileChooser();
				if (chooser != null && chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
					openImageFile(chooser.getSelectedFile().getPath());
			}
		});
		captureScreenItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				BufferedImage img = ImageUtilities.captureScreen();
				if (img != null)
					openImage(img, "screen-capture");
			}
		});

		saveAsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				createFileChooser();
				if (chooser != null && chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
					try {
						File file = chooser.getSelectedFile();
						if (!file.exists())
							ImageUtilities.saveImage(frameMap.get(frame), file);
						else
							JOptionPane.showMessageDialog(frame, "Sorry Dave, I do not overwrite existing files");
					} catch (IOException e) {

						e.printStackTrace();
					}
			}
		});
		/*
		 * The effect workhorse / glue. Make a new effectAction object and use
		 * it for all of the effects.
		 */
		ActionListener effectAction = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String cmd = ((JMenuItem) (ae.getSource())).getText();
				System.out.println("Action=" + cmd);

				BufferedImage sourceImage = frameMap.get(frame);

				sourcePixels = ImageUtilities.getRGBPixels(sourceImage);
				lastSourceFrame = frame;

				int[][] background = ImageUtilities.getRGBPixels(frameMap.get(backgroundFrame));
				if (background == null)
					background = new int[sourcePixels.length][sourcePixels[0].length];
				int[][] result = Effects.process(cmd, sourcePixels, background);
				// a new image is required if it's been resized
				sourceImage = ImageUtilities.setRGBPixels(sourceImage, result);
				setImageForFrame(frame, sourceImage);
			}
		};

		backgroundItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				backgroundFrame = frame;
			}
		});

		undoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (lastSourceFrame != null && sourcePixels != null) {
					BufferedImage sourceImage = frameMap.get(lastSourceFrame);
					int[][] tmp = ImageUtilities.getRGBPixels(sourceImage);
					sourceImage = ImageUtilities.setRGBPixels(sourceImage, sourcePixels);
					setImageForFrame(lastSourceFrame, sourceImage);
					sourcePixels = tmp;
				}
			}
		});
		copyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ImageUtilities.setClipboardImage(frameMap.get(frame));
			}
		});

		pasteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				BufferedImage image = ImageUtilities.getClipboardImage();
				if (image != null)
					openImage(image, "");
			}
		});

		for (String name : new String[] { "half", "rotate", "flip", "mirror", "redeye", "funky", "grayscale", "brighten", "blur", "vignette", "cool", "fade"}) {
			JMenuItem item = new JMenuItem(name);
			item.addActionListener(effectAction);
			effectsMenu.add(item);
		}
		
		for (String name : new String[] { "resize", "merge", "key" }) {
			JMenuItem item = new JMenuItem(name);
			item.addActionListener(effectAction);
			combineMenu.add(item);
		}
		
		for (String name : new String[] { "hide", "extract" }) {
			JMenuItem item = new JMenuItem(name);
			item.addActionListener(effectAction);
			stenographyMenu.add(item);
		}
		frame.setJMenuBar(menubar);
		frame.pack();
	}
}

