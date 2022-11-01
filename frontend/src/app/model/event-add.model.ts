import { FormControl } from '@angular/forms';

export interface IEventAdd {
  name: string;
  date: Date;
  location: string;
  info: string;
}

export interface IEventFormData {
  name: FormControl<string | null>;
  date: FormControl<string | null>;
  location: FormControl<string | null>;
  info: FormControl<string | null>;
}
