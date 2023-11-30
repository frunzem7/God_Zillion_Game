package student.examples.ggengine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item {
	protected int width;
	protected int height;
	protected int speedX;
	protected int speedY;
	protected int top;
	protected int left;
	protected int rotation;
	protected int rotationSpeed;
	protected ItemType itemType;
}
