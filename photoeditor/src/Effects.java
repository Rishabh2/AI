
public class Effects {
	/**
	 * Returns a new image (2D array) based on the command and the source parameters.
	 * This method delegates all of the work to PixelEffects class
	 * @param cmd - the command to execute
	 * @param source - the primary image source (not changed)
	 * @param background - the secondary (background) image (not changed)
	 * @return the new image or null if the command failed.
	 */
	public static int[][] process(String cmd, int[][] source, int[][] background) {
		if (cmd.equals("half")) {
			return PixelEffects.half(source);
		}
		else if (cmd.equals("rotate")) {
			return PixelEffects.rotateLeft(source);
		}
		else if (cmd.equals("flip")) {
			return PixelEffects.flip(source);
		}
		else if (cmd.equals("mirror")) {
			return PixelEffects.mirror(source);
		}
		else if (cmd.equals("redeye")) {
			return PixelEffects.redeye(source, background);
		}
		else if (cmd.equals("funky")) {
			return PixelEffects.funky(source,background);
		}
		else if (cmd.equals("grayscale")) {
			return PixelEffects.grayscale(source);
		}
		else if (cmd.equals("vignette")) {
			return PixelEffects.vignette(source);
		}
		else if (cmd.equals("cool")) {
			return PixelEffects.cool(source);
		}
		else if (cmd.equals("fade")) {
			return PixelEffects.fade(source);
		}
		else if (cmd.equals("blur")) {
			return PixelEffects.blur(source);
		}
		else if (cmd.equals("brighten")) {
			return PixelEffects.brighten(source);
		}
		else if (cmd.equals("resize")) {
			return PixelEffects.resize(source, background);
		}
		else if (cmd.equals("merge")) {
			return PixelEffects.merge(source, background);
		}
		else if (cmd.equals("key")) {
			return PixelEffects.chromaKey(source, background);
		}
		else if (cmd.equals("hide")) {
			return Stenography.hide(source, background);
		}
		else if (cmd.equals("extract")) {
			return Stenography.extract(source);
		}
		else {
		}
		System.out.println("Todo: Implement Effects.process("+cmd+")");
		throw new RuntimeException("Unknown command:"+cmd);
	}
}
