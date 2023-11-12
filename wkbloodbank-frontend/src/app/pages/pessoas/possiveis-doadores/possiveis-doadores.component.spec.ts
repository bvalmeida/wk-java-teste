import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PossiveisDoadoresComponent } from './possiveis-doadores.component';

describe('PossiveisDoadoresComponent', () => {
  let component: PossiveisDoadoresComponent;
  let fixture: ComponentFixture<PossiveisDoadoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PossiveisDoadoresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PossiveisDoadoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
