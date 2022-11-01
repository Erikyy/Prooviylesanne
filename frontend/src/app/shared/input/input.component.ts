import { Component, Input, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  FormGroupDirective,
  ControlContainer,
} from '@angular/forms';
import { IEventFormData } from 'src/app/model/event-add.model';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css'],
  viewProviders: [
    {
      provide: ControlContainer,
      useExisting: FormGroupDirective,
    },
  ],
})
export class InputComponent implements OnInit {
  @Input() placeholder: string = '';
  @Input() controlName: string = '';
  @Input() formGroup: FormGroup<IEventFormData> = new FormGroup({
    name: new FormControl(),
    info: new FormControl(),
    date: new FormControl(),
    location: new FormControl(),
  });

  constructor() {}
  ngOnInit(): void {}
}
