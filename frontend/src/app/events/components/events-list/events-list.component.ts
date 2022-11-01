import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IEvent } from 'src/app/model/event.model';
import { EventsService } from '../../events.service';

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
})
export class EventsListComponent implements OnInit {
  @Input() title: string = '';
  @Input() fetchType: string = '';
  @Input() removable: boolean = false;

  @Output('onRemoveClick') click: EventEmitter<number> = new EventEmitter();

  events: IEvent[] = [];

  constructor(private eventsService: EventsService) {}

  ngOnInit(): void {
    this.getEvents();
  }

  getEvents() {
    this.eventsService.getEvents(this.fetchType).subscribe((e) => {
      this.events = e;
    });
  }

  removeClicked(id: number) {
    this.click.emit(id);
  }
}
