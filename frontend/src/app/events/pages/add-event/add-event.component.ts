import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { IEventFormData } from 'src/app/model/event-add.model';
import { EventsService } from '../../events.service';
import * as moment from 'moment';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
})
export class AddEventComponent implements OnInit {
  constructor(
    private eventsService: EventsService,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSubmit(values: {
    name: string | undefined | null;
    date: string | undefined | null;
    location: string | undefined | null;
    info: string | undefined | null;
  }): void {
    const { name, date, info, location } = values;
    if (name && date && info && location) {
      this.eventsService
        .addNewEvent({
          name,
          date: moment(date, 'DD.MM.yyyy HH:mm').toDate(),
          info,
          location,
        })
        .subscribe({
          complete: () => {
            this.router.navigate(['/']);
          },
          error: (err) => {
            //simple error handling
            alert('Ürituse lisamine põrus');
          },
        });
    }
  }

  onBackClicked() {
    this.location.back();
  }
}
