import { IParticipant } from './participant.model';

export interface IEvent {
  id: number;
  name: string;
  date: Date;
  location: string;
  info: string;
  participants: IParticipant[];
}
