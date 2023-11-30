package student.examples.ggengine.game;

import lombok.Getter;

@Getter
public class Ship extends Item {

	public Ship(int width, int height, int speedX, int speedY, int top, int left, int rotation, int rotationSpeed,
			ItemType itemType) {
		super(width, height, speedX, speedY, top, left, rotation, rotationSpeed, itemType);
	}
}
