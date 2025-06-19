import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from './product';
import { ProductsComponent } from './products.component';
import { ProductsService } from './products.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [ProductsComponent],
  template: `<app-products-ui [products]="products"></app-products-ui>`,
  styles: [],
})
export class ProductsContainer implements OnDestroy, OnInit {
  products: Product[] = [];

  private unsubscribe: Subscription = new Subscription();

  constructor(private api: ProductsService) {}

  ngOnInit(): void {
    this.getProductList();
  }

  getProductList(): void {
    const sub = this.api.getProductList().subscribe((products: Product[]) => {
      this.products = products;
    });
    this.unsubscribe.add(sub);
  }

  ngOnDestroy(): void {
    this.unsubscribe.unsubscribe();
  }
}
