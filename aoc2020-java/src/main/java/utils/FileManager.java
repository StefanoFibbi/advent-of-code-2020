package utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

public class FileManager {

	private FileManager() {}

	@SneakyThrows
	public static List<String> readFromResourcePath(String path) {
		URL filePathURL = ClassLoader.getSystemClassLoader().getResource(path);

		assert filePathURL != null;
		File file = new File(filePathURL.toURI());

		return Files.readAllLines(file.toPath());
	}

	public static List<Integer> readIntegerListFromResource(String resourcePath) {
		List<String> rows = FileManager.readFromResourcePath(resourcePath);
		return rows.stream().map(Integer::parseInt).collect(Collectors.toList());
	}

}
