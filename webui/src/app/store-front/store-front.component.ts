import { Component } from '@angular/core';
import { CartContainer } from '../cart/cart.container';
import { ProductsContainer } from '../products/products.container';

@Component({
  selector: 'app-store-front-ui',
  standalone: true,
  imports: [CartContainer, ProductsContainer],
  templateUrl: './store-front.component.html',
  styleUrl: './store-front.component.scss',
})
export class StoreFrontComponent {}
