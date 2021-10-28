import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { ProductsService } from './products.service';
import { from, Observable } from 'rxjs';
import { ProductsContainer } from './products.container';
import { Product } from './product';

describe('ProductsContainer', () => {

  const products: Product[] = [{ sku: 1, description: 'Box of Chocolate', price: 12.45, countryOfOrigin: 'USA', exempt: true }];
  function setup(): any {
    const serviceSpy =
      jasmine.createSpyObj('ProductsService', ['getProductList']);

    const stubValue: Observable<any> = from(products);
    const productsContainer = new ProductsContainer(serviceSpy);
    serviceSpy.getProductList.and.returnValue(stubValue);
    return { productsContainer, stubValue, serviceSpy };
  }

  it('#ngOnInit should return stubbed value from a spy', async () => {
    const { productsContainer, stubValue, serviceSpy } = setup();


    productsContainer.ngOnInit();

    expect(serviceSpy.getProductList.calls.count())
      .toBe(1, 'spy method was called once');
    expect(serviceSpy.getProductList.calls.count())
      .toBe(1, 'spy method was called once');
    expect(serviceSpy.getProductList())
      .toBe(stubValue, 'service returned stub value');

  });

  it('#ngOnInit should call getProductList 1 time', () => {
    const { productsContainer, stubValue, serviceSpy } = setup();
    const spy = spyOn(productsContainer, 'getProductList');

    productsContainer.ngOnInit();
    expect(spy).toHaveBeenCalled();
    expect(spy).toHaveBeenCalledTimes(1);

  });

  it('#ngOnDestroy should complete subscription', () => {
    const { productsContainer, stubValue, serviceSpy } = setup();
    productsContainer.ngOnDestroy();
    expect(productsContainer.unsubscribe.closed).toBe(true);
  });

});
