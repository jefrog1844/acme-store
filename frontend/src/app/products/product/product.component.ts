import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product } from '../product';

@Component({
  selector: 'app-product-ui',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  @Input() product: Product;
  @Output() addProduct = new EventEmitter<Product>();

  constructor() { }

  ngOnInit(): void {
  }

  addToCart(product: Product): void {
    this.addProduct.emit(product);
  }

}
