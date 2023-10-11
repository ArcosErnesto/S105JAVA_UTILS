package n1ejercicio1;

import java.io.File;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		File ruta = new File("src\\directory");

		System.out.println(ruta.getAbsolutePath());

		String[] list = ruta.list();

		Arrays.sort(list);

		for (String string : list) {
			System.out.println(string);
		}
	}
}
