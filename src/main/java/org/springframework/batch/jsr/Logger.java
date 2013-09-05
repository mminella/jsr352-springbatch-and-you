package org.springframework.batch.jsr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Logger {

	private static BufferedWriter bw;

	static {
		File file = new File("/tmp/log.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void log(String message) {
		try {
			bw.write(message + "\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
