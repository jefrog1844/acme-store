
import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap, share } from 'rxjs/operators';
import { LineItem } from '../products/line-item';
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  responseType: 'text',
};

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private _checkedOut: Subject<string> = new Subject<string>();
  checkedOut$ = this._checkedOut.asObservable();

  constructor(private http: HttpClient) { }

  getCart(customerId: string): Observable<LineItem[]> {
    return this.http.get<LineItem[]>(`${environment.cartUrl}${customerId}`)
      .pipe(
        share(),
        tap(_ => this.log('fetched cart')),
        catchError(this.handleError<LineItem[]>('getCart', []))
      );
  }

  checkout(customerId: string): Observable<any> {
    return this.http.put(`${environment.cartUrl}${customerId}${environment.checkout}`, '', {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      responseType: 'text',
    })
      .pipe(
        tap((receipt) => {
          this.log(`receipt: ${receipt}`);
          this._checkedOut.next(receipt);
        }
        ),
        catchError(this.handleError<any>('checkout', ''))
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
