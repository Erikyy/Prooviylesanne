import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFountComponent } from '../page-not-fount/page-not-fount.component';
import {
  AddEventComponent,
  EventComponent,
  EventsComponent,
  ParticipantComponent,
} from './pages/';

const routes: Routes = [
  {
    path: '',
    component: EventsComponent,
    title: 'Avaleht',
    data: {
      show: true,
    },
  },
  {
    path: 'add-event',
    component: AddEventComponent,
    title: 'Ürituste lisamine',
    data: {
      show: true,
    },
  },
  { path: ':id', component: EventComponent },
  {
    path: ':id/participant/:pid',
    component: ParticipantComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsRoutingModule {}
