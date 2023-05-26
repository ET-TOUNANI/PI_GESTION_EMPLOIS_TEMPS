import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDepartementComponent } from './gestion-departement.component';

describe('GestionDepartementComponent', () => {
  let component: GestionDepartementComponent;
  let fixture: ComponentFixture<GestionDepartementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionDepartementComponent]
    });
    fixture = TestBed.createComponent(GestionDepartementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
