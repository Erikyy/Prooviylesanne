import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { RouteItem } from './route-item';

@Component({
  selector: 'app-header',
  inputs: ['routes'],
  templateUrl: './main-header.component.html',
})
export class MainHeaderComponent implements OnInit {
  routes: RouteItem[];

  activeRoute: string = '';

  constructor(private router: Router) {
    this.routes = this.router.config
      .filter((route) => {
        if (!route.data) {
          return false;
        }
        return true;
      })
      .map((route) => {
        let { title, path } = route;
        if (typeof title == 'string' && typeof path == 'string') {
          return {
            name: title,
            path: path,
          };
        } else {
          return {
            name: '',
            path: '',
          };
        }
      });

    //this.activeRoute = active.title;
  }

  ngOnInit(): void {
    this.router.events.subscribe((data) => {
      if (data instanceof NavigationEnd) {
        this.activeRoute = data.url.slice(1, data.url.length);
      }
    });
  }
}
