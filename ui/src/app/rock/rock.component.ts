import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'rock',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rock.component.html',
  styleUrl: './rock.component.css',
})
export class RockComponent {
  @Input() top: number = 0;
  @Input() left: number = 0;


}
