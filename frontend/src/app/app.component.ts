import { Component } from '@angular/core';
import { RouteItem } from './shared/main-header/route-item';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'frontend';
  routes: RouteItem[] = [
    {
      path: '',
      name: 'Avaleht',
    },
    {
      path: 'add-event',
      name: 'Ürituste lisamine',
    },
  ];
}
