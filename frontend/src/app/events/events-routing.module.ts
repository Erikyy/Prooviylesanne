import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFountComponent } from '../page-not-fount/page-not-fount.component';
import {
  AddEventPage,
  EventDetailPage,
  EventsPage,
  ParticipantPage,
} from './pages/';

const routes: Routes = [
  {
    path: '',
    component: EventsPage,
    title: 'Avaleht',
    data: {
      show: true,
    },
  },
  {
    path: 'add-event',
    component: AddEventPage,
    title: 'Ürituste lisamine',
    data: {
      show: true,
    },
  },
  { path: ':id', component: EventDetailPage },
  {
    path: ':id/participant/:pid',
    component: ParticipantPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsRoutingModule {}
