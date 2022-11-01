import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MiniBannerComponent } from './mini-banner.component';

describe('MiniBannerComponent', () => {
  let component: MiniBannerComponent;
  let fixture: ComponentFixture<MiniBannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MiniBannerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MiniBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
