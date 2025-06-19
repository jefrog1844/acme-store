import { Component } from '@angular/core';
import { StoreFrontContainer } from './store-front/store-front.container';
@Component({
  selector: 'app-root',
  imports: [StoreFrontContainer],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'ACME Store';
}
