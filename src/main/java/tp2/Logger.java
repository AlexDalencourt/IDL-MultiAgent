package tp2;

import tp2.model.ConstantParams;

public class Logger {

	public static void log(Object in) {
		if(ConstantParams.showTrace()) {
			System.out.println(in);
		}
	}
}
