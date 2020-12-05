package utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

public class FileManager {

	private FileManager() {
	}

	@SneakyThrows
	public static List<String> readFromResourcePath(String path) {
		File file = getFileFromResourcePath(path);
		return Files.readAllLines(file.toPath());
	}

	@SneakyThrows
	public static String readFileFromResourcePathToString(String path) {
		File file = getFileFromResourcePath(path);
		byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));

		return new String(encoded, StandardCharsets.UTF_8);
	}

	private static File getFileFromResourcePath(String path) throws URISyntaxException {
		URL filePathURL = ClassLoader.getSystemClassLoader().getResource(path);

		assert filePathURL != null;
		return new File(filePathURL.toURI());
	}

	public static List<Integer> readIntegerListFromResource(String resourcePath) {
		List<String> rows = FileManager.readFromResourcePath(resourcePath);
		return rows.stream().map(Integer::parseInt).collect(Collectors.toList());
	}

}
