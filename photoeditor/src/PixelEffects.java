
public class PixelEffects {

	public static int[][] copy(int[][] source) {
		return resize(source,source.length, source[0].length);
	}
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
		return source;
	}

	public static int[][] half(int[][] source) {
		int width = source.length;
		int height = source[0].length;
		return resize(source, width / 2, height / 2);
	}

	public static int[][] resize(int[][] source, int[][] reference) {
		int width = reference.length;
		int height = reference[0].length;
		return resize(source, width, height);
	}

	/**
	 *	Flips the image over the x-axis.
	 */
	public static int[][] flip(int[][] source) {
		return source;
	}

	/**
	 *	Mirrors the image over the y-axis.
	 */
	public static int[][] mirror(int[][] source) {
		return source;
	}

	/**
	 *	Rotates the image left.
	 */
	public static int[][] rotateLeft(int[][] source) {
		return rotate(rotate(rotate(source)));
	}

	/**
	 *	Rotates the image right.
	 */
	public static int[][] rotate(int[][] source) {
		return source;
	}

	/** 
	 * Merge the red,blue,green components from two images.
	 */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		return sourceA;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image.
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		return foreImage;
	}

	/** 
	 *	Remove redeye. Note that sourceB is not used.
	 */
	public static int[][] redeye(int[][] source, int[][] sourceB) {
		return source;
	}

	/**
	 *	Add some funk to the image.
	 */
	public static int[][] funky(int[][] source, int[][] sourceB) {
		return source;
	}
	
    /**
     *  A very useful utility function
     */
    public static int changePixel(int pixel, int amount) {
        return pixel;
	}
    
	public static int[][] grayscale(int[][] source) {
		return source;
	}
	
	public static int[][] brighten(int[][] source) {
		return source;
	}
	
	public static int[][] fade(int[][] source) {
		return source;
	}
	
	public static int[][] vignette(int[][] source) {
		return source;
	}
	
	
	
	public static int[][] darken(int[][] source) {
		return source;
	}
	
	public static int[][] cool(int[][] source) {
		return source;
	}
	
	public static int[][] blur(int[][] source) {
		return source;
	}
}
