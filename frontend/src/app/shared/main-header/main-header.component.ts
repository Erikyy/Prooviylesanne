import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RouteItem } from './route-item';

@Component({
  selector: 'app-header',
  inputs: ['routes'],
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.css'],
})
export class MainHeaderComponent implements OnInit {
  @Input() routes: RouteItem[];

  activeRoute: string = '';

  constructor(private router: ActivatedRoute) {
    this.routes = [];

    //this.activeRoute = active.title;
  }

  ngOnInit(): void {
    this.router.title.subscribe((title) => {
      console.log(title);

      if (title) {
        this.activeRoute = title;
        console.log(title);
      }
    });
    this.router.url.subscribe((data) => {
      console.log(data);
    });
  }
}
