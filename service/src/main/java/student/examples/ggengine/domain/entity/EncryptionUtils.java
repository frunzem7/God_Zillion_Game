package student.examples.ggengine.domain.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtils {
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String hashPassword(String plainPassword) {
		return passwordEncoder.encode(plainPassword);
	}

	public static boolean verifyPassword(String plainPassword, String hashedPassword) {
		return passwordEncoder.matches(plainPassword, hashedPassword);
	}
}
