import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlexGridItemComponent } from './flex-grid-item.component';

describe('FlexGridItemComponent', () => {
  let component: FlexGridItemComponent;
  let fixture: ComponentFixture<FlexGridItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlexGridItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FlexGridItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
