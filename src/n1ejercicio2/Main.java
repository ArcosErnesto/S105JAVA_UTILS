package n1ejercicio2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		File ruta = new File("src" + File.separator + "directory");

		try {
			ListDirectory(ruta);
		} catch (NullPointerException e) {
			System.out.println("El directorio no existe o no es válido.");
		}
	}

	public static void ListDirectory(File ruta) {
		if (ruta.isDirectory()) {
			System.out.println("D " + ruta.getName() + " - Última modificación: " + getDate(ruta));
			File[] list = ruta.listFiles();
			Arrays.sort(list);
			if (list != null) {
				for (File directory : list) {
					ListDirectory(directory);
				}
			} else {
				System.out.println("El directorio está vacío.");
			}
		} else if (ruta.isFile()) {
			System.out.println("F " + ruta.getName() + " - Última modificación: " + getDate(ruta));
		}
	}

	public static String getDate(File ruta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date(ruta.lastModified());
		return sdf.format(date);
	}
}
