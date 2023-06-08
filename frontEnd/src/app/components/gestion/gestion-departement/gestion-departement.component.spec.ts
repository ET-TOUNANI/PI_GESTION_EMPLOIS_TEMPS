import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDepartmentComponent } from './gestion-departement.component';

describe('GestionDepartementComponent', () => {
  let component: GestionDepartmentComponent;
  let fixture: ComponentFixture<GestionDepartmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionDepartmentComponent]
    });
    fixture = TestBed.createComponent(GestionDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
