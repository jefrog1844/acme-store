import { NgFor } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductContainer } from './product/product.container';

@Component({
  selector: 'app-products-ui',
  standalone: true,
  imports: [NgFor, ProductContainer],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss'],
})
export class ProductsComponent implements OnInit {
  @Input() products: Product[] = [];

  constructor() {}

  ngOnInit(): void {}
}
