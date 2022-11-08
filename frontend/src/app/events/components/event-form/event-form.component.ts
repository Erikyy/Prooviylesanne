import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { IEventFormData } from 'src/app/model/event-add.model';
import { IEvent } from 'src/app/model/event.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
})
export class EventFormComponent implements OnInit {
  eventForm: FormGroup<IEventFormData> = new FormGroup({
    date: new FormControl(),
    info: new FormControl(),
    location: new FormControl(),
    name: new FormControl(),
  });

  @Input() title: string = '';

  @Input() update: boolean = false;
  @Input() event: IEvent = {
    date: new Date(),
    id: -1,
    info: '',
    location: '',
    name: '',
  };

  btnStyle1: ButtonStyle = ButtonStyle.Secondary;
  btnStyle2: ButtonStyle = ButtonStyle.Primary;

  @Output('onSubmit') click: EventEmitter<{
    name: string | undefined | null;
    date: string | undefined | null;
    location: string | undefined | null;
    info: string | undefined | null;
  }> = new EventEmitter();

  @Output('onBackClicked') backClicked: EventEmitter<void> = new EventEmitter();

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.eventForm = this.formBuilder.group({
      name: this.event.name,
      date: '',
      location: this.event.location,
      info: this.event.info,
    });
    console.log(this.event);
  }

  onSubmit() {
    let { name, date, location, info } = this.eventForm.value;
    this.click.emit({ name, date, location, info });
  }

  onBackClicked() {
    this.backClicked.emit();
  }
}
