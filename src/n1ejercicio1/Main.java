package n1ejercicio1;

import java.io.File;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		File ruta = new File("src" + File.separator + "directory");

		try {
			System.out.println("Directorio absoluto: " + ruta.getAbsolutePath());

			String[] list = ruta.list();

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
