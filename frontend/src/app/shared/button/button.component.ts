import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

export enum ButtonStyle {
  None = 'hover:bg-gray-200',
  Primary = 'bg-sky-800 rounded-md hover:bg-sky-600 active:bg-sky-700 text-white',
  Secondary = 'bg-neutral-300 rounded-md hover:bg-neutral-400 active:bg-neutral-500',
}

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
})
export class ButtonComponent implements OnInit {
  @Input() title: string = '';
  @Input() buttonStyle: ButtonStyle = ButtonStyle.None;
  @Input() buttonType: string = 'submit';

  @Output('onClick') click: EventEmitter<void> = new EventEmitter();

  constructor() {}

  ngOnInit(): void {}

  onButtonClick() {
    this.click.emit();
  }
}
