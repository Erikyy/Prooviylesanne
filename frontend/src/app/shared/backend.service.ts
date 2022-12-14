import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IEvent } from '../model/event.model';

import { environment } from 'src/environments/environment';
import { catchError, map, Observable, of, retry, throwError } from 'rxjs';
import { IEventAdd } from '../model/event-add.model';
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
export class BackendService {
  constructor(private http: HttpClient) {}

  getEvents(fetchType: string): Observable<IEvent[]> {
    return this.http
      .get<IEvent[]>(`${environment.apiUrl}/events?event=${fetchType}`)
      .pipe(retry(1), catchError(this.handleError<IEvent[]>('getEvents', [])));
  }

  addNewEvent(event: IEventAdd): Observable<IEvent> {
    return this.http.post<IEvent>(`${environment.apiUrl}/events`, event).pipe(
      catchError(
        this.handleError<IEvent>('addEvent', {
          date: event.date,
          id: -1,
          location: event.location,
          name: event.name,
          info: event.info,
        })
      )
    );
  }

  updateEvent(event: IEvent): Observable<IEvent> {
    return this.http
      .put<IEvent>(`${environment.apiUrl}/events/${event.id}`, event)
      .pipe(
        catchError(
          this.handleError<IEvent>('addEvent', {
            date: event.date,
            id: event.id,
            location: event.location,
            name: event.name,
            info: event.info,
          })
        )
      );
  }

  removeEvent(id: number): Observable<unknown> {
    return this.http
      .delete(`${environment.apiUrl}/events/${id}`)
      .pipe(catchError(this.handleError('removeEvent')));
  }

  getEventById(id: number): Observable<IEvent> {
    return this.http.get<IEvent>(`${environment.apiUrl}/events/${id}`).pipe(
      catchError(
        this.handleError<IEvent>('getEventById', {
          id: id,
          location: '',
          name: '',
          date: new Date(),
          info: '',
        })
      )
    );
  }

  addParticipantToEvent(
    eventId: number,
    participant: IParticipantAdd | IParticipant
  ): Observable<IParticipant> {
    return this.http
      .post<IParticipant>(
        `${environment.apiUrl}/events/${eventId}/participants`,
        participant
      )
      .pipe(
        catchError(this.handleError<IParticipant>('addParticipantToEvent'))
      );
  }

  removeParticipantFromEvent(
    eventId: number,
    participantId: number
  ): Observable<unknown> {
    return this.http
      .delete(
        `${environment.apiUrl}/events/${eventId}/participants/${participantId}`
      )
      .pipe(catchError(this.handleError('deleteParticipant')));
  }

  getParticipants(): Observable<IParticipant[]> {
    return this.http
      .get<IParticipant[]>(`${environment.apiUrl}/participants/`)
      .pipe(
        catchError(this.handleError<IParticipant[]>('getParticipantsFromEvent'))
      );
  }

  getParticipantsFromEvent(eventId: number): Observable<IParticipant[]> {
    return this.http
      .get<IParticipant[]>(
        `${environment.apiUrl}/events/${eventId}/participants/`
      )
      .pipe(
        catchError(this.handleError<IParticipant[]>('getParticipantsFromEvent'))
      );
  }

  getParticipantFromEvent(
    eventId: number,
    participantId: number
  ): Observable<IParticipant> {
    return this.http
      .get<IParticipant>(
        `${environment.apiUrl}/events/${eventId}/participants/${participantId}`
      )
      .pipe(
        catchError(this.handleError<IParticipant>('getParticipantFromEvent'))
      );
  }

  updateParticipantInEvent(
    eventId: number,
    participant: IParticipant
  ): Observable<IParticipant> {
    return this.http
      .put<IParticipant>(
        `${environment.apiUrl}/participants/${participant.id}`,
        participant
      )
      .pipe(
        catchError(this.handleError<IParticipant>('updateParticipantInEvent'))
      );
  }

  getPaymentMethods(): Observable<IPaymentMethod[]> {
    return this.http
      .get<IPaymentMethod[]>(`${environment.apiUrl}/payment_methods`)
      .pipe(
        catchError(this.handleError<IPaymentMethod[]>('getPaymentMethods'))
      );
  }

  private handleError<T>(operation: string = 'operation', result?: T) {
    return (error: any) => {
      console.log(`ERROR: ${operation}`);

      return throwError(() => new Error(`ERROR: ${operation} ${error}`));
    };
  }
}
