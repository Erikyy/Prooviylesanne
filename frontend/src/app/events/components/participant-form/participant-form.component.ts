import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import {
  IParticipantAdd,
  IParticipantBusinessAdd,
  IParticipantCitizenAdd,
} from 'src/app/model/participant-add.model';
import { IParticipant } from 'src/app/model/participant.model';
import { IPaymentMethod } from 'src/app/model/payment-method.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-participant-form',
  templateUrl: './participant-form.component.html',
})
export class ParticipantFormComponent implements OnInit {
  @Input() title: string = '';

  @Input() paymentMethods: IPaymentMethod[] = [];

  @Output('onSubmit') submit: EventEmitter<IParticipantAdd> =
    new EventEmitter();

  @Output('onEditSubmit') editSubmit: EventEmitter<IParticipant> =
    new EventEmitter();

  @Output('onBackClicked') backClick: EventEmitter<void> = new EventEmitter();

  participantTypeForm: FormGroup<{
    participantType: FormControl<string | null>;
  }>;

  constructor(private formBuilder: FormBuilder) {
    this.participantTypeForm = this.formBuilder.group({
      participantType: 'citizen',
    });
  }

  ngOnInit(): void {}

  onSubmit(participant: IParticipantAdd): void {
    console.log(participant);

    this.submit.emit(participant);
  }

  onEditSubmit(participant: IParticipant): void {
    this.editSubmit.emit(participant);
  }

  onBackClicked(): void {
    this.backClick.emit();
  }
}
