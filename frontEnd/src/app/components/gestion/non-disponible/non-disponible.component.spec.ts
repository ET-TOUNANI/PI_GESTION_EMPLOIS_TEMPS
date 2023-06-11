import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NonDisponibleComponent } from './non-disponible.component';

describe('NonDisponibleComponent', () => {
  let component: NonDisponibleComponent;
  let fixture: ComponentFixture<NonDisponibleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NonDisponibleComponent]
    });
    fixture = TestBed.createComponent(NonDisponibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
