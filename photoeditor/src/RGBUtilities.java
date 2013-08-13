
public class RGBUtilities {

	public static int toRed(int rgb) {
		return (rgb>>16) & 0xff;
	}

	public static int toGreen(int rgb) {
		return (rgb>>8) & 0xff;
	}

	public static int toBlue(int rgb) {
		return rgb & 0xff;
	}

	static int toRGB(int r, int g, int b) {
		return (r<<16 | g<<8 | b);
	}

}
