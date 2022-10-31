import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFountComponent } from '../page-not-fount/page-not-fount.component';
import { AddEventComponent } from './add-event/add-event.component';
import { EventsComponent } from './events.component';

const routes: Routes = [
  { path: '', component: EventsComponent, title: 'Avaleht' },
  {
    path: 'add-event',
    component: AddEventComponent,
    title: 'Ürituste lisamine',
  },
  { path: ':id', component: PageNotFountComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EventsRoutingModule {}
