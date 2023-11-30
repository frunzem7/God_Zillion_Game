package student.examples.ggengine.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import student.examples.ggengine.entity.Frame;
import student.examples.ggengine.entity.ItemType;
import student.examples.ggengine.entity.Rock;
import student.examples.ggengine.entity.Ship;

@CrossOrigin
@RestController
public class FrameController {

	@PostMapping("update")
	public Frame update() {
		Frame frame = new Frame();
		Random random = new Random();
		
		Rock rock = new Rock();
		rock.setWidth(100);
		rock.setHeight(100);
		
		rock.setSpeedX(100);
		rock.setSpeedY(100);
		
		int low = 1;
		int high = 100;
		int result = random.nextInt(low, high);
		rock.setTop(result);
		
		result = random.nextInt(low, high);
		rock.setLeft(result);
		
		low = 1;
		high = 360;
		result = random.nextInt(low, high);
		rock.setRotation(result);
		
		rock.setRotationSpeed(5);
		rock.setItemType(ItemType.ROCK);
		frame.addItem(rock);
		
		Ship ship = new Ship();
		ship.setWidth(100);
		ship.setHeight(100);
		
		ship.setSpeedX(100);
		ship.setSpeedY(100);
		
		low = 1;
		high = 100;
		result = random.nextInt(low, high);
		ship.setTop(result);
		
		result = random.nextInt(low, high);
		ship.setLeft(result);
		
		low = 1;
		high = 360;
		result = random.nextInt(low, high);
		ship.setRotation(result);
		
		ship.setRotationSpeed(5);
		ship.setItemType(ItemType.SHIP);
		frame.addItem(ship);
		return frame;
	}
}
