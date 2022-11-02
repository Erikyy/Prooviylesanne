import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { IEvent } from 'src/app/model/event.model';
import {
  IParticipantAdd,
  IParticipantBusinessAdd,
  IParticipantCitizenAdd,
} from 'src/app/model/participant-add.model';
import { IParticipant } from 'src/app/model/participant.model';
import { IPaymentMethod } from 'src/app/model/payment-method.model';
import { EventsService } from '../../events.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
})
export class EventComponent implements OnInit {
  activeRoute: string = '';
  eventId: number = -1;
  paymentMethods: IPaymentMethod[] = [];

  event: IEvent = {
    date: new Date(),
    name: '',
    info: '',
    location: '',
    id: -1,
    participants: [],
  };

  constructor(private router: Router, private eventService: EventsService) {
    this.router.events.subscribe((data) => {
      if (data instanceof NavigationEnd) {
        this.activeRoute = data.url;
      }
    });
  }

  ngOnInit(): void {
    this.getEvent();
    this.getPaymentMethods();
  }

  getEvent() {
    //first parameter is always event id
    let eventId = this.activeRoute.split('/')[1];
    this.eventService.getEventById(+eventId).subscribe((event) => {
      this.event = event;
      this.eventId = event.id;
    });
  }

  getPaymentMethods() {
    this.eventService.getPaymentMethods().subscribe((methods) => {
      this.paymentMethods = methods;
    });
  }

  getIdNumOrRegCode(participant: IParticipant): number {
    if (participant.citizen !== null) {
      return participant.citizen.idNumber;
    } else if (participant.business !== null) {
      return participant.business.regCode;
    } else {
      return 0;
    }
  }

  removeParticipant(participantId: number) {
    this.eventService
      .removeParticipantFromEvent(this.eventId, participantId)
      .subscribe(() => {
        const url = this.router.url;
        this.router
          .navigateByUrl('.', { skipLocationChange: true })
          .then(() => {
            this.router.navigateByUrl(url);
          });
      });
  }

  onSubmit(participant: IParticipantAdd) {
    console.log('submit');

    this.eventService
      .addParticipantToEvent(this.eventId, participant)
      .subscribe(() => {
        const url = this.router.url;
        this.router
          .navigateByUrl('.', { skipLocationChange: true })
          .then(() => {
            this.router.navigateByUrl(url);
          });
      });
  }

  onBackClicked(): void {
    this.router.navigate(['/']);
  }
}
