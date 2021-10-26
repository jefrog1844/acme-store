import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { LineItem } from '../products/line-item';

@Component({
  selector: 'app-cart-ui',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  @Input() lineItems: LineItem[];
  @Input() receipt: string;
  @Output() checkout = new EventEmitter<any>();

  constructor() { }

  ngOnInit(): void {
  }

  onCheckout(): void {
    this.checkout.emit();
  }

}
