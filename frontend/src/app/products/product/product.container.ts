import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../product';

@Component({
  selector: 'app-product',
  template: `<app-product-ui [product]="product"></app-product-ui>`,
  styles: []
})
export class ProductContainer implements OnInit {

  @Input() product: Product;

  constructor() { }

  ngOnInit(): void {
  }

}
