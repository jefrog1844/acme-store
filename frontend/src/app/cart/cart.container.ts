import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { LineItem } from '../products/line-item';
import { ProductsService } from '../products/products.service';
import { CartService } from './cart.service';

@Component({
  selector: 'app-cart',
  template: `<app-cart-ui [lineItems]="lineItems"></app-cart-ui>`,
  styles: []
})
export class CartContainer implements OnDestroy, OnInit {

  lineItems: LineItem[];
  customerId = '1';
  private unsubscribe: Subscription = new Subscription();

  constructor(private api: CartService, private productsService: ProductsService) { }

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    const sub = this.api.getCart(this.customerId).subscribe(lineItems => {
      this.lineItems = lineItems;
    });
    this.unsubscribe.add(sub);

    const addedSub = this.productsService.addedToCart$
      .pipe(
        switchMap(() => {
          // switchmap unsubscribes to inner subscription
          return this.api.getCart(this.customerId);
        })
      )
      .subscribe((lineItems) => {
        // switchmap unsubscribes to this subscription
        this.lineItems = lineItems;
      });
    this.unsubscribe.add(addedSub);
  }

  ngOnDestroy(): void {
    this.unsubscribe.unsubscribe();
  }

}
