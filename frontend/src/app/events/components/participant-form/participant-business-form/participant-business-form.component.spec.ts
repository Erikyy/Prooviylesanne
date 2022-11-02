import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantBusinessFormComponent } from './participant-business-form.component';

describe('ParticipantBusinessFormComponent', () => {
  let component: ParticipantBusinessFormComponent;
  let fixture: ComponentFixture<ParticipantBusinessFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticipantBusinessFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticipantBusinessFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
