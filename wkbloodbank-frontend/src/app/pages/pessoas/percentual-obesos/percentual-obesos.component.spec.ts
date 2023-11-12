import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PercentualObesosComponent } from './percentual-obesos.component';

describe('PercentualObesosComponent', () => {
  let component: PercentualObesosComponent;
  let fixture: ComponentFixture<PercentualObesosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PercentualObesosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PercentualObesosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
