import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import {
  IParticipantAdd,
  IParticipantBusinessAdd,
  IParticipantBusinessFormData,
  IParticipantCitizenFormData,
} from 'src/app/model/participant-add.model';
import { IParticipant } from 'src/app/model/participant.model';
import { IPaymentMethod } from 'src/app/model/payment-method.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-participant-business-form',
  templateUrl: './participant-business-form.component.html',
})
export class ParticipantBusinessFormComponent implements OnInit {
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

  participantForm: FormGroup<IParticipantBusinessFormData> = new FormGroup({
    info: new FormControl(),
    name: new FormControl(),
    numOfParticipants: new FormControl(),
    paymentMethod: new FormControl(),
    regCode: new FormControl(),
  });

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    if (this.participant.business) {
      this.participantForm = this.formBuilder.group({
        regCode: this.participant.business.regCode,
        numOfParticipants: this.participant.business.numOfParticipants,
        info: this.participant.business.info,
        name: this.participant.name,
        paymentMethod: this.participant.paymentMethod.id,
      });
    }
  }

  onSubmit(): void {
    let { info, name, numOfParticipants, paymentMethod, regCode } =
      this.participantForm.value;
    console.log(this.participantForm.value);

    if (info && name && numOfParticipants && paymentMethod && regCode) {
      console.log('submit');

      if (this.participant.business) {
        this.editSubmit.emit({
          id: this.participant.id,
          name,
          paymentMethod: {
            id: paymentMethod,
            method: '',
          },
          business: {
            id: this.participant.business.id,
            regCode,
            numOfParticipants,
            info,
          },
          citizen: null,
        });
      } else {
        this.submit.emit({
          id: null,
          name,
          paymentMethodId: paymentMethod,
          business: {
            regCode,
            numOfParticipants,
            info,
          },
          citizen: null,
        });
      }
    }
  }

  onBackClicked(): void {
    this.backClick.emit();
  }
}
