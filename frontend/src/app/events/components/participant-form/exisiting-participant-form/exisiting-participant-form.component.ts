import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EventsService } from 'src/app/events/events.service';
import { IParticipant } from 'src/app/model/participant.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-exisiting-participant-form',
  templateUrl: './exisiting-participant-form.component.html',
})
export class ExisitingParticipantFormComponent implements OnInit {
  @Output('onSubmit') submit: EventEmitter<IParticipant> = new EventEmitter();

  participants: IParticipant[] = [];

  btnStyle1: ButtonStyle = ButtonStyle.Secondary;
  btnStyle2: ButtonStyle = ButtonStyle.Primary;

  participantForm = this.formBuilder.group({
    participant: {
      //here id is the most important rest can be discarded
      id: 0,
      paymentMethod: {
        id: 0,
      },
      name: '',
      citizen: null,
      business: null,
    },
  });

  constructor(
    private eventService: EventsService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getAllParticipants();
  }

  onSubmit(): void {
    let { participant } = this.participantForm.value;
    if (participant) {
      this.submit.emit({
        id: participant.id,
        name: participant.name,
        business: participant.business,
        citizen: participant.citizen,
        paymentMethod: {
          id: participant.paymentMethod.id,
          method: '',
        },
      });
    }
  }

  onBackClicked(): void {}

  getAllParticipants() {
    this.eventService.getParticipants().subscribe((participants) => {
      this.participants = participants;
    });
  }
}
