import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { StoreFrontComponent } from './store-front/store-front.component';
import { ProductsComponent } from './products/products.component';
import { CartComponent } from './cart/cart.component';
import { ReceiptComponent } from './receipt/receipt.component';
import { ProductComponent } from './products/product/product.component';
import { ProductContainer } from './products/product/product.container';
import { ProductsContainer } from './products/products.container';
import { StoreFrontContainer } from './store-front/store-front.container';
import { CartContainer } from './cart/cart.container';



@NgModule({
  declarations: [
    AppComponent,
    StoreFrontContainer,
    StoreFrontComponent,
    ProductsContainer,
    ProductsComponent,
    CartContainer,
    CartComponent,
    ReceiptComponent,
    ProductContainer,
    ProductComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
