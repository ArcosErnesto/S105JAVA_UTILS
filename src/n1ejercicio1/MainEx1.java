package n1ejercicio1;

import java.io.File;
import java.util.Arrays;

public class MainEx1 {

	public static void main(String[] args) {

		File path = new File("src" + File.separator + "directory");

		try {
			System.out.println("Directorio absoluto: " + path.getAbsolutePath());

			String[] list = path.list();

			if (list != null) {
				Arrays.sort(list);

				if (list.length == 0) {
					System.out.println("El directorio está vacío.");
				} else {
					for (String string : list) {
						System.out.println(string);
					}
				}
			} else {
				System.out.println("El directorio está vacío.");
			}
		} catch (NullPointerException e) {
			System.out.println("El directorio no existe o no es válido.");
		}
	}
}
