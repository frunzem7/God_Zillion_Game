import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { RockComponent } from './rock/rock.component';
import { ShipComponent } from './ship/ship.component';
import { Item } from './entity/item.interface';
import { FrameService } from './services/frame.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RockComponent, ShipComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'ui';
  items!: Item[];
  fs: FrameService = new FrameService();
  // constructor(private frameService: FrameService) {}

  ngOnInit(): void {
    setInterval(() => {
      this.items = this.fs.retrieveData();
    }, 1000);
  }
  // ngOnInit(): void {
  //   this.frameService
  //     .retrieveData()
  //     .then((response) => {
  //       this.items = response;
  //       console.log(response);

  //     })
  //     .then((data) => {
  //       console.log(this.items);

  //     });
  //   console.log(this.items);

  // }
}
