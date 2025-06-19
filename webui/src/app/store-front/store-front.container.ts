import { Component, OnInit } from '@angular/core';
import { StoreFrontComponent } from './store-front.component';

@Component({
  selector: 'app-store-front',
  standalone: true,
  imports: [StoreFrontComponent],
  template: `<app-store-front-ui></app-store-front-ui>`,
  styles: [],
})
export class StoreFrontContainer implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
