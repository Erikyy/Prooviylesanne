import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExisitingParticipantFormComponent } from './exisiting-participant-form.component';

describe('ExisitingParticipantFormComponent', () => {
  let component: ExisitingParticipantFormComponent;
  let fixture: ComponentFixture<ExisitingParticipantFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExisitingParticipantFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExisitingParticipantFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
