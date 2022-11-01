import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-mini-banner',
  templateUrl: './mini-banner.component.html',
})
export class MiniBannerComponent implements OnInit {
  @Input() title: string = '';

  constructor() {}

  ngOnInit(): void {}
}
