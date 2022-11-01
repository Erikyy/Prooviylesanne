import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { PageNotFountComponent } from './page-not-fount/page-not-fount.component';
import { EventsModule } from './events/events.module';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [AppComponent, PageNotFountComponent],
  imports: [
    BrowserModule,
    HttpClientModule,
    SharedModule,
    EventsModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
