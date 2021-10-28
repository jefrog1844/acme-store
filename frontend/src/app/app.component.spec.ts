import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { StoreFrontContainer } from './store-front/store-front.container';
import { StoreFrontComponent } from './store-front/store-front.component';
import { CartContainer } from './cart/cart.container';
import { CartComponent } from './cart/cart.component';
import { ProductsContainer } from './products/products.container';
import { ProductsComponent } from './products/products.component';
import { ProductContainer } from './products/product/product.container';
import { ProductComponent } from './products/product/product.component';
import { HttpClientModule } from '@angular/common/http';

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [
        AppComponent,
        StoreFrontContainer,
        StoreFrontComponent,
        CartContainer,
        CartComponent,
        ProductsContainer,
        ProductsComponent,
        ProductContainer,
        ProductComponent,
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'ACME Store'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('ACME Store');
  });
});
