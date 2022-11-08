import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { IEvent } from 'src/app/model/event.model';
import { EventsService } from '../../events.service';
import * as moment from 'moment';
import { Location } from '@angular/common';

@Component({
  selector: 'app-update-event',
  templateUrl: './update-event.component.html',
})
export class UpdateEventComponent implements OnInit {
  activeRoute: string = '';
  eventId: number = -1;

  event: IEvent = {
    date: new Date(),
    id: -1,
    info: '',
    location: '',
    name: '',
  };

  constructor(
    private eventService: EventsService,
    private location: Location,
    private router: Router
  ) {
    this.router.events.subscribe((data) => {
      if (data instanceof NavigationEnd) {
        this.activeRoute = data.url;
      }
    });
  }

  ngOnInit(): void {
    this.getEvent();
  }

  getEvent(): void {
    let eventId = this.activeRoute.split('/')[1];
    this.eventService.getEventById(+eventId).subscribe((event) => {
      this.event = event;
      this.eventId = event.id;
    });
  }

  onSubmit(values: {
    name: string | undefined | null;
    date: string | undefined | null;
    location: string | undefined | null;
    info: string | undefined | null;
  }) {
    let { name, date, location, info } = values;

    if (name && date && location && info) {
      this.eventService
        .updateEvent({
          id: this.eventId,
          date: moment(date, 'DD.MM.yyyy HH:mm').toDate(),
          location,
          info,
          name,
        })
        .subscribe({
          complete: () => {
            this.router.navigate(['/']);
          },
          error: () => {
            alert('Ürituse muutmine põrus');
          },
        });
    }
  }

  onBackClicked() {
    this.location.back();
  }
}
