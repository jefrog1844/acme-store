
import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap, share } from 'rxjs/operators';
import { Product } from './product';
import { PurchaseRequest } from './purchase-request';
import { LineItem } from './line-item';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private _addedToCart: Subject<LineItem> = new Subject<LineItem>();
  addedToCart$ = this._addedToCart.asObservable();

  constructor(private http: HttpClient) { }

  getProductList(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.storeUrl}`)
      .pipe(
        share(),
        tap(_ => this.log('fetched product list')),
        catchError(this.handleError<Product[]>('getProductList', []))
      );
  }

  addToCart(purchaseRequest: PurchaseRequest): Observable<LineItem> {
    return this.http.post<LineItem>(`${environment.storeUrl}`, purchaseRequest, httpOptions).pipe(
      tap((entity: LineItem) => {
        this.log(`added product to cart w/ sku=${entity.product.sku}`);
        this._addedToCart.next(entity);
      }),
      catchError(this.handleError<LineItem>('addToCart'))
    );
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string): void {
    console.log('APIService: ' + message);
  }

  private handleError<T>(operation = 'operation', result?: T): any {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
