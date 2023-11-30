package student.examples.ggengine.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Frame {
	private final Set<Item> items = new HashSet<>();

	public void addItem(Item item) {
		items.add(item);
	}
}
