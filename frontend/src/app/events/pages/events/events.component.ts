import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventsService } from '../../events.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
})
export class EventsComponent implements OnInit {
  constructor(private router: Router, private eventsService: EventsService) {}

  ngOnInit(): void {}

  onAddClick() {
    this.router.navigate(['add-event']);
  }

  removeEvent(id: number) {
    this.eventsService.removeEvent(id).subscribe(() => {
      this.router.navigateByUrl('.', { skipLocationChange: true }).then(() => {
        this.router.navigateByUrl('/');
      });
    });
  }
}
