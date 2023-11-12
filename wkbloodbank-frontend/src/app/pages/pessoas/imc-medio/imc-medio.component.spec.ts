import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImcMedioComponent } from './imc-medio.component';

describe('ImcMedioComponent', () => {
  let component: ImcMedioComponent;
  let fixture: ComponentFixture<ImcMedioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImcMedioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImcMedioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
