import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventsRoutingModule } from './events-routing.module';
import { EventsComponent } from './events.component';
import { SharedModule } from "../shared/shared.module";
import { AddEventComponent } from './add-event/add-event.component';


@NgModule({
  declarations: [
    EventsComponent,
    AddEventComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    EventsRoutingModule
  ]
})
export class EventsModule { }
