import { FormControl } from '@angular/forms';

export interface IParticipantAdd {
  name: string;
  paymentMethod: string;
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
  paymentMethod: FormControl<string | null>;
  lastName: FormControl<string | null>;
  idNumber: FormControl<number | null>;
  info: FormControl<string | null>;
}

export interface IParticipantBusinessFormData {
  name: FormControl<string | null>;
  paymentMethod: FormControl<string | null>;
  numOfParticipants: FormControl<number | null>;
  regCode: FormControl<number | null>;
  info: FormControl<string | null>;
}
