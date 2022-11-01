import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IEvent } from '../model/event.model';
import { BackendService } from '../shared/backend.service';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root',
})
export class EventsService {
  constructor(private backendService: BackendService) {}

  getEvents(fetchType: string): Observable<IEvent[]> {
    return this.backendService.getEvents(fetchType);
  }

  addNewEvent(event: {
    name: string;
    date: string;
    location: string;
    info: string;
  }): Observable<IEvent> {
    return this.backendService.addNewEvent({
      name: event.name,
      info: event.info,
      location: event.location,
      date: moment(event.date, 'DD.MM.yyyy HH:mm').toDate(),
    });
  }

  removeEvent(id: number): Observable<unknown> {
    return this.backendService.removeEvent(id);
  }
}
