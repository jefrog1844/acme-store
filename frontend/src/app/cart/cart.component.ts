import { Component, Input, OnInit } from '@angular/core';
import { LineItem } from '../products/line-item';

@Component({
  selector: 'app-cart-ui',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  @Input() lineItems: LineItem[];

  constructor() { }

  ngOnInit(): void {
  }

}
