import { ItemType } from './item-type.enum';

export interface Item {
  width: number;
  height: number;
  speedX: number;
  speedY: number;
  top: number;
  left: number;
  rotation: number;
  rotationSpeed: number;
  itemType: ItemType;
}
