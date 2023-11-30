import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'ship',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ship.component.html',
  styleUrl: './ship.component.css',
})
export class ShipComponent {
  @Input() top: number = 0;
  @Input() left: number = 0;
}
