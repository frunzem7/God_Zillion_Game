package student.examples.ggengine;

import java.sql.Date;
import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GgengineApplication {

	public static void main(String[] args) {

		// Get the current timestamp
		long currentTimestamp = System.currentTimeMillis();

		// Calculate the timestamp for 24 hours ago
		long twentyFourHoursAgo = currentTimestamp - (24 * 60 * 60 * 1000);

		// Convert to Instant for better date manipulation
		Instant twentyFourHoursAgoInstant = Instant.ofEpochMilli(twentyFourHoursAgo);

		// Save the result as an int (truncate to int)
		int last24HoursTimestamp = (int) twentyFourHoursAgo;

		Date date1 = new Date(twentyFourHoursAgo);
		Date date2 = new Date(last24HoursTimestamp);
		System.out.println(date1);
		System.out.println(date2);

		System.out.println("Current Timestamp: " + currentTimestamp);
		System.out.println("Instant for 24 Hours Ago: " + twentyFourHoursAgoInstant);
		System.out.println("Last 24 Hours Timestamp (as int): " + last24HoursTimestamp);

		SpringApplication.run(GgengineApplication.class, args);
	}
}
