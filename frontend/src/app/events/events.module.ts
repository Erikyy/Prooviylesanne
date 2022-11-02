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
import { ParticipantFormComponent } from './components/participant-form/participant-form.component';
import { ParticipantCitizenFormComponent } from './components/participant-form/participant-citizen-form/participant-citizen-form.component';
import { ParticipantBusinessFormComponent } from './components/participant-form/participant-business-form/participant-business-form.component';
@NgModule({
  declarations: [EventsComponent, AddEventComponent, EventsListComponent, ParticipantComponent, EventFormComponent, EventComponent, ParticipantFormComponent, ParticipantCitizenFormComponent, ParticipantBusinessFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    EventsRoutingModule,
  ],
})
export class EventsModule {}
