import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { LineItem } from '../products/line-item';
import { ProductsService } from '../products/products.service';
import { CartComponent } from './cart.component';
import { CartService } from './cart.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartComponent],
  template: `<app-cart-ui
    [lineItems]="lineItems"
    [receipt]="receipt"
    (checkout)="checkout()"
  ></app-cart-ui>`,
  styles: [],
})
export class CartContainer implements OnDestroy, OnInit {
  lineItems: LineItem[] = [];
  receipt: string = '';
  customerId = '1';
  private unsubscribe: Subscription = new Subscription();

  constructor(
    private api: CartService,
    private productsService: ProductsService
  ) {}

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    const sub = this.api
      .getCart(this.customerId)
      .subscribe((lineItems: LineItem[]) => {
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
      .subscribe((lineItems: LineItem[]) => {
        // switchmap unsubscribes to this subscription
        this.lineItems = lineItems;
      });
    this.unsubscribe.add(addedSub);

    const checkoutSub = this.api.checkedOut$.subscribe((receipt: any) => {
      this.lineItems = [];
    });
    this.unsubscribe.add(checkoutSub);
  }

  checkout(): void {
    const checkSub = this.api
      .checkout(this.customerId)
      .subscribe((receipt: string) => {
        this.receipt = receipt;
      });
    this.unsubscribe.add(checkSub);
  }

  ngOnDestroy(): void {
    this.unsubscribe.unsubscribe();
  }
}
