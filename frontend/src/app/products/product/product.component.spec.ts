import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Product } from '../product';

import { ProductComponent } from './product.component';

describe('ProductComponent', () => {
  let component: ProductComponent;
  let fixture: ComponentFixture<ProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductComponent);
    component = fixture.componentInstance;
    component.product = { sku: 1, description: 'Box of Chocolate', price: 12.45, countryOfOrigin: 'USA', exempt: true };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
