import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from '../product';
import { ProductsService } from '../products.service';
import { PurchaseRequest } from '../purchase-request';
import { ProductComponent } from './product.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [ProductComponent],
  template: `<app-product-ui
    [product]="product"
    (addProduct)="addToCart($event)"
  ></app-product-ui>`,
  styles: [],
})
export class ProductContainer implements OnDestroy, OnInit {
  @Input() product: Product = new Product();
  private unsubscribe = new Subscription();

  constructor(private api: ProductsService) {}

  ngOnInit(): void {}

  addToCart(product: Product): void {
    const pr: PurchaseRequest = { customerId: '1', sku: product.sku };
    const addSub = this.api
      .addToCart(pr)
      .subscribe((lineItem: { product: { description: any } }) => {
        console.log(`lineItem ${lineItem.product.description}`);
      });
    this.unsubscribe.add(addSub);
  }

  ngOnDestroy(): void {
    this.unsubscribe.unsubscribe();
  }
}
