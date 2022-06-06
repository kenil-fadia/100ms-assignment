package com.ms100.io;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class BasicFileOps {

	protected File _fileHandle;

	public boolean open(Object source) throws IOException {

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (((File) source).exists()) {
				desktop.open((File) source);
			}
			return true;
		}
		System.out.println("Desktop is not supported");
		return false;
	}

	public String createPath(String relativePath) {
		FileSystem path = FileSystems.getDefault();
		return path.getPath(System.getProperty("user.dir"), relativePath).toString();
	}

	public static void makeDirectory(File path) {
		File folder = path.getParentFile();
		if (!path.exists()) {
			folder.mkdirs();
		}
	}

	public static void makeDirectory(String path) {
		File scrFile = new File(path);
		makeDirectory(scrFile);
	}
}
