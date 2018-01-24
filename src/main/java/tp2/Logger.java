package tp2;

public class Logger {

	public static void log(Object in) {
		if(ConstantParams.showTrace()) {
			System.out.println(in);
		}
	}
}
