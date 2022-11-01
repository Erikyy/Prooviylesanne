import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventsRoutingModule } from './events-routing.module';
import { EventsComponent } from './pages/events.component';
import { SharedModule } from '../shared/shared.module';
import { AddEventComponent } from './pages/add-event/add-event.component';
import { EventsListComponent } from './components/events-list/events-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ParticipantComponent } from './pages/participant/participant.component';
import { EventFormComponent } from './components/event-form/event-form.component';
import { EventComponent } from './pages/event/event.component';
@NgModule({
  declarations: [EventsComponent, AddEventComponent, EventsListComponent, ParticipantComponent, EventFormComponent, EventComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    EventsRoutingModule,
  ],
})
export class EventsModule {}
