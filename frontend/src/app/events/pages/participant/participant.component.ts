import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { IParticipant } from 'src/app/model/participant.model';
import { EventsService } from '../../events.service';
import { IPaymentMethod } from 'src/app/model/payment-method.model';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
})
export class ParticipantComponent implements OnInit {
  participant: IParticipant = {
    business: null,
    citizen: null,
    id: 0,
    name: '',
    paymentMethod: {
      id: -1,
      method: '',
    },
  };
  paymentMethods: IPaymentMethod[] = [];

  constructor(
    private location: Location,
    private router: Router,
    private eventService: EventsService
  ) {}

  ngOnInit(): void {
    this.getParticipant();
    this.getPaymentMethods();
  }

  getPaymentMethods() {
    this.eventService.getPaymentMethods().subscribe((methods) => {
      this.paymentMethods = methods;
    });
  }

  getParticipant() {
    let { eventId, participantId } =
      this.eventService.extactEventIdAndParticipantIdFromUrl(this.router.url);
    this.eventService
      .getParticipantFromEvent(eventId, participantId)
      .subscribe((participant) => {
        this.participant = participant;
      });
  }

  onEditSubmit(participant: IParticipant): void {
    let { eventId, participantId } =
      this.eventService.extactEventIdAndParticipantIdFromUrl(this.router.url);
    this.eventService.updateParticipantInEvent(eventId, participant).subscribe({
      complete: () => {
        this.location.back();
      },
      error: (err) => {
        alert('Osaleja muutmine põrus');
      },
    });
  }

  onBackClicked(): void {
    this.location.back();
  }
}
