
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap, share } from 'rxjs/operators';
import { LineItem } from '../products/line-item';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
const apiUrl = 'http://localhost:8081/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  getCart(customerId: string): Observable<LineItem[]> {
    return this.http.get<LineItem[]>(`${apiUrl}/${customerId}` )
      .pipe(
        share(),
        tap(_ => this.log('fetched cart')),
        catchError(this.handleError<LineItem[]>('getCart', []))
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
