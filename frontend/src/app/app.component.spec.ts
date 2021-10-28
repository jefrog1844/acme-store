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
import { CartService } from './cart/cart.service';
import { ProductsService } from './products/products.service';
import { ReceiptContainer } from './receipt/receipt.container';
import { ReceiptComponent } from './receipt/receipt.component';

class MockCartService {
  getCart: [];
  checkout: '';
}
class MockProductsService {
  getProductList: [{
    'sku': '1',
    'description': 'Book',
    'price': 12.49,
    'countryOfOrigin': 'USA',
    'exempt': true
  },
    {
      'sku': '2',
      'description': 'Music CD',
      'price': 14.99,
      'countryOfOrigin': 'USA',
      'exempt': false
    },
    {
      'sku': '3',
      'description': 'Chocolate Bar',
      'price': 0.85,
      'countryOfOrigin': 'USA',
      'exempt': true
    },
    {
      'sku': '4',
      'description': 'Imported Box of Chocolates',
      'price': 10.00,
      'countryOfOrigin': 'GERMANY',
      'exempt': true
    },
    {
      'sku': '5',
      'description': 'Imported Bottle of Perfume',
      'price': 47.50,
      'countryOfOrigin': 'ITALY',
      'exempt': false
    },
    {
      'sku': '6',
      'description': 'Imported Bottle of Perfume',
      'price': 27.99,
      'countryOfOrigin': 'FRANCE',
      'exempt': false
    },
    {
      'sku': '7',
      'description': 'Bottle of Perfume',
      'price': 18.99,
      'countryOfOrigin': 'USA',
      'exempt': false
    },
    {
      'sku': '8',
      'description': 'Packet of Headache Pills',
      'price': 9.75,
      'countryOfOrigin': 'USA',
      'exempt': true
    },
    {
      'sku': '9',
      'description': 'Imported Box of Chocolates',
      'price': 11.25,
      'countryOfOrigin': 'SWEDEN',
      'exempt': true
    }];
  addToCart: null;
}
let cartService: CartService;
let productsService: ProductsService;

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
        ReceiptContainer,
        ReceiptComponent
      ],
    }).compileComponents();
    cartService = TestBed.inject(CartService);
    productsService = TestBed.inject(ProductsService);
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'frontend'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('frontend');
  });

  // it('should render title', () => {
  // const fixture = TestBed.createComponent(AppComponent);
  // fixture.detectChanges();
  //  const compiled = fixture.nativeElement;
  // expect(compiled.querySelector('.content span').textContent).toContain('frontend app is running!');
  // });
});
