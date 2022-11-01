import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css'],
})
export class HeroComponent implements OnInit {
  @Input() paragraph: string = '';
  @Input() imgUrl: string = '';

  constructor() {}

  ngOnInit(): void {}
}
