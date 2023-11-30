import { Injectable } from '@angular/core';
import { Item } from '../entity/item.interface';

@Injectable({
  providedIn: 'root',
})
export class FrameService {
  item: Item[] = [];
  constructor() {}

  retrieveData(): Item[] {
    fetch('http://localhost:8080/update', {
      method: 'POST',
      mode: 'cors',
      cache: 'no-cache',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        this.item = data['items'];
      })
      .catch((error) => {
        console.error('Error fetching data: ', error);
      });
    return this.item;
  }
  // async retrieveData() {
  //   return await fetch('http://localhost:8080/update', {
  //     method: 'POST',
  //     mode: 'cors',
  //     cache: 'no-cache',
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   })
  //     .then((response) => response.json())
  //     .then((data) => data.items);
  // }
}
