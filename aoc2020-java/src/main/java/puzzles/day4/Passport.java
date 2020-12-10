package puzzles.day4;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Passport {
	private String byr; // (Birth Year)
	private String iyr; // (Issue Year)
	private String eyr; // (Expiration Year)
	private String hgt; // (Height)
	private String hcl; // (Hair Color)
	private String ecl; // (Eye Color)
	private String pid; // (Passport ID)
	private String cid; // (Country ID)
}
