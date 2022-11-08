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
import { Location } from '@angular/common';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
})
export class EventComponent implements OnInit {
  activeRoute: string = '';
  eventId: number = -1;
  paymentMethods: IPaymentMethod[] = [];

  currentDate: Date = new Date();

  event: IEvent = {
    date: new Date(),
    name: '',
    info: '',
    location: '',
    id: -1,
  };

  participants: IParticipant[] = [];

  constructor(
    private router: Router,
    private location: Location,
    private eventService: EventsService
  ) {
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

    this.eventService
      .getParticipantsFromEvent(+eventId)
      .subscribe((participants) => {
        this.participants = participants;
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
      .subscribe({
        complete: () => {
          this.ngOnInit();
        },
        error: () => {
          alert('Osaleja eemaldamine põrus');
        },
      });
  }

  onSubmit(participant: IParticipantAdd) {
    console.log(participant);

    this.eventService
      .addParticipantToEvent(this.eventId, participant)
      .subscribe({
        complete: () => {
          this.ngOnInit();
        },
        error: () => {
          alert('Osaleja lisamine põrus');
        },
      });
  }

  onEditSubmit(participant: IParticipant) {
    this.eventService
      .addParticipantToEvent(this.eventId, participant)
      .subscribe({
        complete: () => {
          this.ngOnInit();
        },
        error: () => {
          alert('Osaleja lisamine põrus');
        },
      });
  }
  onBackClicked(): void {
    this.location.back();
  }
}
