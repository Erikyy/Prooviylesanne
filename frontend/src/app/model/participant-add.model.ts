import { FormControl } from '@angular/forms';
import { IPaymentMethod } from './payment-method.model';

export interface IParticipantAdd {
  id: number | null;
  name: string;
  paymentMethodId: number;
  citizen: IParticipantCitizenAdd | null;
  business: IParticipantBusinessAdd | null;
}

export interface IParticipantCitizenAdd {
  lastName: string;
  idNumber: number;
  info: string;
}

export interface IParticipantBusinessAdd {
  regCode: number;
  numOfParticipants: number;
  info: string;
}

export interface IParticipantCitizenFormData {
  name: FormControl<string | null>;
  paymentMethod: FormControl<number | null>;
  lastName: FormControl<string | null>;
  idNumber: FormControl<number | null>;
  info: FormControl<string | null>;
}

export interface IParticipantBusinessFormData {
  name: FormControl<string | null>;
  paymentMethod: FormControl<number | null>;
  numOfParticipants: FormControl<number | null>;
  regCode: FormControl<number | null>;
  info: FormControl<string | null>;
}
