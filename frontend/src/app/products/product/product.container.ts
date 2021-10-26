import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from '../product';
import { ProductsService } from '../products.service';
import { PurchaseRequest } from '../purchase-request';

@Component({
  selector: 'app-product',
  template: `<app-product-ui [product]="product" (addProduct)="addToCart($event)"></app-product-ui>`,
  styles: []
})
export class ProductContainer implements OnDestroy, OnInit {

  @Input() product: Product;
  private unsubscribe = new Subscription();

  constructor(private api: ProductsService) { }

  ngOnInit(): void {
  }

  addToCart(product: Product): void {
    const pr: PurchaseRequest = {customerId: '1', sku: product.sku};
    const addSub = this.api.addToCart(pr).subscribe((lineItem) => {
      console.log(`lineItem ${lineItem.product.description}`);
    });
    this.unsubscribe.add(addSub);
  }

  ngOnDestroy(): void {
    this.unsubscribe.unsubscribe();
  }

}
