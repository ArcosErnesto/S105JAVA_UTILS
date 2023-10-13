package n1ejercicio2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainEx2 {

	public static void main(String[] args) {
		File path = new File("src" + File.separator + "directory");

		try {
			ListDirectory(path);
		} catch (NullPointerException e) {
			System.out.println("El directorio no existe o no es válido.");
		}
	}

	public static void ListDirectory(File path) {
		if (path.isDirectory()) {
			System.out.println("D " + path.getName() + " - Última modificación: " + getDate(path));
			File[] list = path.listFiles();
			Arrays.sort(list);
			if (list != null) {
				for (File directory : list) {
					ListDirectory(directory);
				}
			} else {
				System.out.println("El directorio está vacío.");
			}
		} else if (path.isFile()) {
			System.out.println("F " + path.getName() + " - Última modificación: " + getDate(path));
		}
	}

	public static String getDate(File ruta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date(ruta.lastModified());
		return sdf.format(date);
	}
}
