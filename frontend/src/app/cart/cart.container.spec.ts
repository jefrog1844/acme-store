import { from, Observable } from 'rxjs';
import { Product } from '../products/product';
import { LineItem } from '../products/line-item';
import { CartContainer } from './cart.container';
import { ProductsService } from '../products/products.service';
import { CartService } from './cart.service';

describe('CartContainer', () => {



  function setup(): any {
    const product: Product = { sku: 1, description: 'Box of Chocolate', price: 12.45, countryOfOrigin: 'USA', exempt: true };
    const quantity = 1;
    const lineItems: LineItem[] = [{ product, quantity }];

    const cartSpy =
      jasmine.createSpyObj('CartService', ['getCart', 'checkedOut$']);
    const productSpy =
      jasmine.createSpyObj('ProductsService', ['addedToCart$']);

    const stubValue: Observable<any> = from(lineItems);

    const cartContainer = new CartContainer(cartSpy, productSpy);

    return { cartContainer, stubValue, cartSpy, productSpy };
  }

  it('#ngOnInit should call getCart 1 time', async () => {
    const { cartContainer, stubValue, cartSpy, productSpy } = setup();
    cartContainer.getCart = jasmine.createSpy().and.returnValue('');

    cartContainer.ngOnInit();

    expect(cartContainer.getCart.calls.count())
      .toBe(1, 'spy method was called once');
  });

  it('#ngOnDestroy should complete subscription', () => {
    const { cartContainer, stubValue, cartSpy, productSpy } = setup();
    cartContainer.ngOnDestroy();
    expect(cartContainer.unsubscribe.closed).toBe(true);
  });

});
