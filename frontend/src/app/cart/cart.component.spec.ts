import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LineItem } from '../products/line-item';
import { Product } from '../products/product';

import { CartComponent } from './cart.component';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  const product: Product = { sku: 1, description: 'Box of Chocolate', price: 12.45, countryOfOrigin: 'USA', exempt: true };
  const lineItems: LineItem[] = [
    { product, quantity: 1 }
  ];
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CartComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    component.lineItems = lineItems;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
