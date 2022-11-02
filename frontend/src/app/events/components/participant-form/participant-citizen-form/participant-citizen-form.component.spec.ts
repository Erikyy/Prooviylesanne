import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantCitizenFormComponent } from './participant-citizen-form.component';

describe('ParticipantCitizenFormComponent', () => {
  let component: ParticipantCitizenFormComponent;
  let fixture: ComponentFixture<ParticipantCitizenFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticipantCitizenFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticipantCitizenFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
