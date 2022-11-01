import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IEventFormData } from 'src/app/model/event-add.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
})
export class EventFormComponent implements OnInit {
  eventForm: FormGroup<IEventFormData>;

  @Input() title: string = '';

  btnStyle1: ButtonStyle = ButtonStyle.Secondary;
  btnStyle2: ButtonStyle = ButtonStyle.Primary;

  @Output('onSubmit') click: EventEmitter<{
    name: string | undefined | null;
    date: string | undefined | null;
    location: string | undefined | null;
    info: string | undefined | null;
  }> = new EventEmitter();

  constructor(private formBuilder: FormBuilder) {
    this.eventForm = this.formBuilder.group({
      name: '',
      date: '',
      location: '',
      info: '',
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    let { name, date, location, info } = this.eventForm.value;
    this.click.emit({ name, date, location, info });
  }
}
