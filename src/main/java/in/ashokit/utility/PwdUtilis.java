package in.ashokit.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtilis {

	public static String generatePassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@";
		String pwd = RandomStringUtils.random( 6, characters );
		return pwd;
	}
}
