import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import {
  IParticipantAdd,
  IParticipantCitizenAdd,
  IParticipantCitizenFormData,
} from 'src/app/model/participant-add.model';
import { IParticipant } from 'src/app/model/participant.model';
import { ButtonStyle } from 'src/app/shared/button/button.component';

@Component({
  selector: 'app-participant-citizen-form',
  templateUrl: './participant-citizen-form.component.html',
})
export class ParticipantCitizenFormComponent implements OnInit {
  @Input() title: string = '';

  @Input() participant: IParticipant = {
    id: 0,
    business: null,
    citizen: null,
    name: '',
    paymentMethod: 'Cash',
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
    paymentMethod: new FormControl('Cash'),
  });

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    if (this.participant.citizen) {
      this.participantForm = this.formBuilder.group({
        idNumber: this.participant.citizen.idNumber,
        info: this.participant.citizen.info,
        lastName: this.participant.citizen.lastName,
        name: this.participant.name,
        paymentMethod: this.participant.paymentMethod,
      });
    }
  }

  onSubmit(): void {
    let { name, idNumber, info, lastName, paymentMethod } =
      this.participantForm.value;

    if (name && idNumber && info && lastName && paymentMethod) {
      if (this.participant.citizen) {
        this.editSubmit.emit({
          id: this.participant.id,
          name,
          paymentMethod,
          citizen: {
            id: this.participant.citizen.id,
            lastName,
            idNumber,
            info,
          },
          business: null,
        });
      } else {
        this.submit.emit({
          name,
          paymentMethod,
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
