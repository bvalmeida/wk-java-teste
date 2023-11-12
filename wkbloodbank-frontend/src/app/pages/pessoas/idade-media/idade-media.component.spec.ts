import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IdadeMediaComponent } from './idade-media.component';

describe('IdadeMediaComponent', () => {
  let component: IdadeMediaComponent;
  let fixture: ComponentFixture<IdadeMediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IdadeMediaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IdadeMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
