import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IEvent } from '../model/event.model';

import { environment } from 'src/environments/environment';
import { catchError, map, Observable, of, retry } from 'rxjs';
import { IEventAdd } from '../model/event-add.model';

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
    console.log('post');

    return this.http.post<IEvent>(`${environment.apiUrl}/events`, event).pipe(
      catchError(
        this.handleError<IEvent>('addEvent', {
          date: event.date,
          id: -1,
          location: event.location,
          name: event.name,
          participants: [],
        })
      )
    );
  }

  removeEvent(id: number): Observable<unknown> {
    return this.http
      .delete(`${environment.apiUrl}/events/${id}`)
      .pipe(catchError(this.handleError('removeEvent')));
  }

  private handleError<T>(operation: string = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(`ERROR: ${operation} ${result}`);

      return of(result as T);
    };
  }
}
