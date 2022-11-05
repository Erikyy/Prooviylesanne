import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventsRoutingModule } from './events-routing.module';

import { SharedModule } from '../shared/shared.module';
import { EventsListComponent } from './components/events-list/events-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EventFormComponent } from './components/event-form/event-form.component';
import { ParticipantFormComponent } from './components/participant-form/participant-form.component';
import { ParticipantCitizenFormComponent } from './components/participant-form/participant-citizen-form/participant-citizen-form.component';
import { ParticipantBusinessFormComponent } from './components/participant-form/participant-business-form/participant-business-form.component';
import { AddEventPage, EventsPage, ParticipantPage } from './pages';
@NgModule({
  declarations: [
    EventsPage,
    AddEventPage,
    EventsListComponent,
    ParticipantPage,
    EventFormComponent,
    EventsPage,
    ParticipantFormComponent,
    ParticipantCitizenFormComponent,
    ParticipantBusinessFormComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    EventsRoutingModule,
  ],
})
export class EventsModule {}
