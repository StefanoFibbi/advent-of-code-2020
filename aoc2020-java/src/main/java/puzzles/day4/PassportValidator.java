package puzzles.day4;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public class PassportValidator {

	private final PassportValidationRule validationRule;
	private final boolean validateFieldsValue;

	public PassportValidator(PassportValidationRule validationRule, boolean validateFieldsValue) {
		this.validationRule = validationRule;
		this.validateFieldsValue = validateFieldsValue;
	}

	public PassportValidator(PassportValidationRule validationRule) {
		this(validationRule, false);
	}

	@SneakyThrows
	public boolean validatePassport(Passport passport) {
		boolean isValid = true;
		String[] mandatoryFields = this.validationRule.mandatoryFields();

		for (String f : mandatoryFields) {
			Method getFieldMethod = Passport.class.getMethod(f);
			String result = (String) getFieldMethod.invoke(passport);

			if (result == null) {
				isValid = false;
			}
			else if (this.validateFieldsValue) {
				isValid = isValid && this.validateField(f, result);
			}
		}

		return isValid;
	}

	@SneakyThrows
	public boolean validateField(String fieldName, String fieldValue) {
		Method method = PassportValidator.class.getMethod("validate" + StringUtils.capitalize(fieldName), String.class);
		return (boolean) method.invoke(this, fieldValue);
	}

	public boolean validateByr(String value) {
		return this.intInRange(value, 1920, 2002);
	}

	public boolean validateIyr(String value) {
		return this.intInRange(value, 2010, 2020);
	}

	public boolean validateEyr(String value) {
		return this.intInRange(value, 2020, 2030);
	}

	public boolean validateHgt(String value) {
		String intVal = value.substring(0, value.length() - 2);
		String measure = value.substring(value.length() - 2);
		switch (measure) {
			case "cm":
				return this.intInRange(intVal, 150, 193);

			case "in":
				return this.intInRange(intVal, 59, 76);

			default:
				return false;
		}
	}

	public boolean validateHcl(String value) {
		Pattern pattern = Pattern.compile("^(.*?)(#([a-fA-F0-9]{6}))$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public boolean validateEcl(String value) {
		List<String> acceptedCols = Arrays.asList(new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"});
		return acceptedCols.contains(value);
	}

	public boolean validatePid(String value) {
		Pattern pattern = Pattern.compile("^[0-9]{9}$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	private boolean intInRange(String value, int min, int max) {
		int year = Integer.parseInt(value);
		return year >= min && year <= max;
	}

}
