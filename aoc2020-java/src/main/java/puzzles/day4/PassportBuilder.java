package puzzles.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class PassportBuilder {

	public List<Passport> buildFromList(List<String> multiplePassportsList) {
		List<Passport> passports = new ArrayList<>();
		for(String p : multiplePassportsList) {
			passports.add(this.buildPassport(p));
		}
		return passports;
	}

	public Passport buildPassport(String passportLines) {
		Gson gson = new Gson();
		String passportJson = gson.toJson(this.buildPassportElementsMap(passportLines));
		return gson.fromJson(passportJson, Passport.class);
	}

	private Map<String, String> buildPassportElementsMap(String passport) {
		Map<String, String> passportElements = new HashMap<>();
		Arrays.stream(passport.split(" ")).forEach(element -> {
			String[] elementKeyVal = element.split(":");
			passportElements.put(elementKeyVal[0], elementKeyVal[1]);
		});
		return passportElements;
	}

}
