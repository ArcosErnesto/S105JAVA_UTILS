package n1ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainEx3 {

	public static void main(String[] args) {
		File path = new File("src" + File.separator + "directory");
		File writeFile = new File("src" + File.separator + "n1ejercicio3" + File.separator + "listFiles.txt");

		try {
			if (writeFile.createNewFile()) {
				System.out.println("El archivo 'listFiles.txt' ha sido creado exitosamente.");
			} else {
				System.out.println("El archivo 'listFiles.txt' ya existe en la ubicación especificada.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true))) {
			bw.write(sdf.format(date) + " - Contenido de " + path.getAbsolutePath());
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error al escribir en archivo.");
			e.printStackTrace();
		}

		try {
			ListDirectory(path, writeFile);
		} catch (NullPointerException e) {
			System.out.println("El directorio no existe o no es válido.");
		}

		readFile(writeFile);

	}

	public static void ListDirectory(File path, File writeFile) {

		if (path.isDirectory()) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true))) {
				bw.write("D " + path.getName() + " - Última modificación: " + getDate(path));
				bw.newLine();
			} catch (IOException e) {
				System.out.println("Error al escribir en archivo.");
				e.printStackTrace();
			}
			File[] list = path.listFiles();
			Arrays.sort(list);
			if (list != null) {
				for (File directory : list) {
					ListDirectory(directory, writeFile);
				}
			} else {
				System.out.println("El directorio está vacío.");
			}
		} else if (path.isFile()) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true))) {
				bw.write("F " + path.getName() + " - Última modificación: " + getDate(path));
				bw.newLine();
			} catch (IOException e) {
				System.out.println("Error al escribir en archivo.");
				e.printStackTrace();
			}
		}

	}

	public static String getDate(File ruta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date(ruta.lastModified());
		return sdf.format(date);
	}

	public static void readFile(File writeFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(writeFile))) {
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura del archivo.");
			e.printStackTrace();
		}
	}
}
