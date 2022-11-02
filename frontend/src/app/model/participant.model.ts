import { IBusiness } from './business.model';
import { ICitizen } from './citizen.model';
import { IPaymentMethod } from './payment-method.model';

export interface IParticipant {
  id: number;
  paymentMethod: IPaymentMethod;
  name: string;
  citizen: ICitizen | null;
  business: IBusiness | null;
}
