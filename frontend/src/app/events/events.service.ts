import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IEvent } from '../model/event.model';
import { BackendService } from '../shared/backend.service';
import * as moment from 'moment';
import {
  IParticipantAdd,
  IParticipantBusinessAdd,
  IParticipantCitizenAdd,
} from '../model/participant-add.model';
import { IParticipant } from '../model/participant.model';
import { IPaymentMethod } from '../model/payment-method.model';

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

  getEventById(id: number): Observable<IEvent> {
    return this.backendService.getEventById(id);
  }

  removeParticipantFromEvent(
    eventId: number,
    participantId: number
  ): Observable<unknown> {
    return this.backendService.removeParticipantFromEvent(
      eventId,
      participantId
    );
  }

  addParticipantToEvent(
    eventId: number,
    participant: IParticipantAdd | IParticipant
  ): Observable<IParticipant> {
    return this.backendService.addParticipantToEvent(eventId, participant);
  }

  getParticipantsFromEvent(eventId: number): Observable<IParticipant[]> {
    return this.backendService.getParticipantsFromEvent(eventId);
  }

  getParticipantFromEvent(
    eventId: number,
    participantId: number
  ): Observable<IParticipant> {
    return this.backendService.getParticipantFromEvent(eventId, participantId);
  }

  extactEventIdFromUrl(url: string): number {
    return +url.split('/')[1];
  }

  extactEventIdAndParticipantIdFromUrl(url: string): {
    eventId: number;
    participantId: number;
  } {
    let stringArr = url.split('/');

    return {
      eventId: +stringArr[1],
      participantId: +stringArr[3],
    };
  }

  updateParticipantInEvent(
    eventId: number,
    participant: IParticipant
  ): Observable<IParticipant> {
    return this.backendService.updateParticipantInEvent(eventId, participant);
  }

  getPaymentMethods(): Observable<IPaymentMethod[]> {
    return this.backendService.getPaymentMethods();
  }

  getParticipants(): Observable<IParticipant[]> {
    return this.backendService.getParticipants();
  }
}
