package student.examples.ggengine.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Item {
	protected int width;
	protected int height;
	protected int speedX;
	protected int speedY;
	protected int top;
	protected int left;
	protected int rotation;
	protected int rotationSpeed;
	// TODO: protect the type
	protected ItemType itemType;
}
