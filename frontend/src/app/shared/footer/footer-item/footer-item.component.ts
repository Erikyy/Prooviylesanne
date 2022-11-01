import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer-item',
  templateUrl: './footer-item.component.html',
  styleUrls: ['./footer-item.component.css'],
})
export class FooterItemComponent implements OnInit {
  @Input() header: string = '';
  @Input() items: string[] = [];

  constructor() {}

  ngOnInit(): void {}
}
