import { IBusiness } from './business.model';
import { ICitizen } from './citizen.model';

export interface IParticipant {
  id: number;
  paymentMethod: string;
  name: string;
  citizen: ICitizen | null;
  business: IBusiness | null;
}
