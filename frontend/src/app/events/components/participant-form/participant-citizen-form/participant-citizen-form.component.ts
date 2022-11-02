import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import {
  IParticipantAdd,
  IParticipantCitizenAdd,
  IParticipantCitizenFormData,
} from 'src/app/model/participant-add.model';
import { IParticipant } from 'src/app/model/participant.model';
import { IPaymentMethod } from 'src/app/model/payment-method.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-participant-citizen-form',
  templateUrl: './participant-citizen-form.component.html',
})
export class ParticipantCitizenFormComponent implements OnInit {
  @Input() title: string = '';

  @Input() paymentMethods: IPaymentMethod[] = [];

  @Input() participant: IParticipant = {
    id: 0,
    business: null,
    citizen: null,
    name: '',
    paymentMethod: {
      id: -1,
      method: '',
    },
  };

  @Output('onSubmit') submit: EventEmitter<IParticipantAdd> =
    new EventEmitter();

  @Output('onEditSubmit') editSubmit: EventEmitter<IParticipant> =
    new EventEmitter();

  @Output('onBackClicked') backClick: EventEmitter<void> = new EventEmitter();

  btnStyle1: ButtonStyle = ButtonStyle.Secondary;
  btnStyle2: ButtonStyle = ButtonStyle.Primary;

  participantForm: FormGroup<IParticipantCitizenFormData> = new FormGroup({
    idNumber: new FormControl(),
    info: new FormControl(),
    lastName: new FormControl(),
    name: new FormControl(),
    paymentMethod: new FormControl(1),
  });

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    if (this.participant.citizen) {
      this.participantForm = this.formBuilder.group({
        idNumber: this.participant.citizen.idNumber,
        info: this.participant.citizen.info,
        lastName: this.participant.citizen.lastName,
        name: this.participant.name,
        paymentMethod: this.participant.paymentMethod.id,
      });
    }
  }

  onSubmit(): void {
    console.log('submit');
    let { name, idNumber, info, lastName, paymentMethod } =
      this.participantForm.value;
    console.log(paymentMethod);
    if (name && idNumber && info && lastName && paymentMethod) {
      if (this.participant.citizen) {
        this.editSubmit.emit({
          id: this.participant.id,
          name,
          paymentMethod: {
            id: paymentMethod,
            method: '',
          },
          citizen: {
            id: this.participant.citizen.id,
            lastName,
            idNumber,
            info,
          },
          business: null,
        });
      } else {
        console.log('submit new');

        this.submit.emit({
          name,
          paymentMethodId: paymentMethod,
          citizen: {
            lastName,
            idNumber,
            info,
          },
          business: null,
        });
      }
    }
  }

  onBackClicked(): void {
    this.backClick.emit();
  }
}
