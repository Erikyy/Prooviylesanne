import { TestBed } from '@angular/core/testing';

import { EventsService } from './events.service';

describe('EventsService', () => {
  let service: EventsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return event id as number', () => {
    let url = '/92';

    expect(service.extactEventIdFromUrl(url)).toBe(92);
  });

  it('should return eventId and participantId', () => {
    let url = '/92/participants/22';

    expect(service.extactEventIdAndParticipantIdFromUrl(url)).toBe({
      eventId: 92,
      participantId: 22,
    });
  });
});
