package multiAgent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class ConstantParams {
	
	public static int numberOfNeighbours = 8;
	
	private static Random random = null;
	
	private static Properties props;
	
	private static int boxSize;
	
	static {
		Scanner scan = new Scanner(System.in);
		System.out.println("Path to properties file");
//		String path = scan.nextLine();
		String path = "param1.properties";
		scan.close();
		props = new Properties();
		try {
			props.load(new FileInputStream(new File(path)));
		} catch (IOException e) {
			throw new RuntimeException("Bad path to properties");
		}
		int seed = Integer.valueOf(props.getProperty("seed"));
		if(seed != 0) {
			random = new Random(seed);
		}else {
			random = new Random(System.currentTimeMillis());
		}
		String value = props.getProperty("box.size", "auto");
		if("auto".equals(value.trim().toLowerCase())) {
			boxSize = ConstantParams.getCanvasSizeX() < ConstantParams.getCanvasSizeY() ? 
					ConstantParams.getCanvasSizeX() / ConstantParams.getGridSizeX() : 
						ConstantParams.getCanvasSizeY() / ConstantParams.getGridSizeY();
		} else if(StringUtils.isNumeric(value)) {
			boxSize = Integer.valueOf(props.getProperty("box.size"));
		} else {
			throw new RuntimeException("Box size value " + value + " is not a correct value (auto or a number)");
		}
	}
	
	public static int getNumberOfParticules() {
		return Integer.valueOf(props.getProperty("nb.particles"));
	}
	
	public static int getNumberInitialOfSharks() {
		return Integer.valueOf(props.getProperty("nb.sharks"));
	}
	
	public static int getSharkBreedTime() {
		return Integer.valueOf(props.getProperty("shark.breed.time"));
	}
	
	public static int getSharkStarveTime() {
		return Integer.valueOf(props.getProperty("shark.starve.time"));
	}
	
	public static int getFishBreedTime() {
		return Integer.valueOf(props.getProperty("fish.breed.time"));
	}
	
	public static int getNumberInitialOfFishes() {
		return Integer.valueOf(props.getProperty("nb.fishes"));
	}
	
	public static int getNumberOfHunter() {
		return Integer.valueOf(props.getProperty("nb.hunter"));
	}
	
	public static Random getRandom() {
		return random;
	}

	public static int getGridSizeX() {
		return Integer.valueOf(props.getProperty("grid.size.x"));
	}

	public static int getGridSizeY() {
		return Integer.valueOf(props.getProperty("grid.size.y"));
	}

	public static boolean getTorus() {
		return Boolean.valueOf(props.getProperty("torus"));
	}

	public static SMATypes getSMA() {
		return SMATypes.valueOf(props.getProperty("scheduling"));
	}

	public static int getNumberOfTicks() {
		return Integer.valueOf(props.getProperty("nb.ticks"));
	}

	public static int getCanvasSizeX() {
		return Integer.valueOf(props.getProperty("canvas.size.x"));
	}

	public static int getCanvasSizeY() {
		return Integer.valueOf(props.getProperty("canvas.size.y"));
	}

	public static int getBoxSize() {
		return boxSize;
	}

	public static long getDelay() {
		return Long.valueOf(props.getProperty("delay"));
	}
	
	public static boolean showGrid() {
		return Boolean.valueOf(props.getProperty("grid"));
	}
	
	public static boolean showTrace() {
		return Boolean.valueOf(props.getProperty("trace"));
	}
	
	public static int refresh() {
		return Integer.valueOf(props.getProperty("refresh"));
	}
	
	public enum SMATypes {
		SEQUENTIAL, SEQUENTIALRANDOM, ALLRANDOM
	}
}
