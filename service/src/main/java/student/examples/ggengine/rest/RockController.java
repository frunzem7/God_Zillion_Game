package student.examples.ggengine.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RockController {

	@GetMapping("rock")
	public Map<String, Integer> getData() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Random random = new Random();
		int low = 1;
		int high = 100;
		int result = random.nextInt(low, high);
		map.put("top", result);

		result = random.nextInt(low, high);
		map.put("left", result);

		low = 0;
		high = 360;
		result = random.nextInt(low, high);
		map.put("rotation", result);

		map.put("width", 100);
		map.put("hidth", 100);
		return map;
	}
}
