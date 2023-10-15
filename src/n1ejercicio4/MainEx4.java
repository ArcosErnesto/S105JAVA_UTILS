package n1ejercicio4;

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
import java.util.Scanner;

public class MainEx4 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String userPath;
		File path = new File("src" + File.separator + "directory");
		File writeFile = new File("src" + File.separator + "directory" + File.separator + "listFiles.txt");

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

		readListFile(writeFile);
		userPath = selectFile();

		File filePath = new File("src" + File.separator + userPath);
		System.out.println(filePath);

		readFile(filePath);

	}

	public static void ListDirectory(File path, File writeFile) {

		if (path.isDirectory()) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true))) {
				bw.write("(D) " + path.getName() + " - Última modificación: " + getDate(path));
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
				bw.write("(F) " + path.getName() + " - Última modificación: " + getDate(path));
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

	public static void readListFile(File writeFile) {
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

	public static String selectFile() {
		String userFile, userDirectory, userPath = null;
		System.out.println("¿En qué directorio está el archivo para leer?");
		userDirectory = sc.nextLine();

		while (userPath == null) {
			if (userDirectory.equals("directory")) {
				userDirectory = userDirectory + File.separator;
				System.out.println("¿Qué archivo de la lista quieres leer?");
				userFile = sc.nextLine();
				userPath = userDirectory + userFile;
			} else if (userDirectory.equals("subDirectory1")) {
				System.out.println("¿Qué archivo de la lista quieres leer?");
				userFile = sc.nextLine();
				userDirectory = "directory" + File.separator + userDirectory + File.separator;
				userPath = userDirectory + userFile;
			} else {
				System.out.println("Directorio no encontrado. Ingresa 'directory' o 'subDirectory1'.");
				userDirectory = sc.nextLine();
			}
		}

		return userPath;
	}

	private static void readFile(File filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado.");
		} catch (IOException e) {
			System.out.println("Error de lectura del archivo.");
		}
	}
}
