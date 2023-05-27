import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionClasseComponent } from './gestion-classe.component';

describe('GestionClasseComponent', () => {
  let component: GestionClasseComponent;
  let fixture: ComponentFixture<GestionClasseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GestionClasseComponent]
    });
    fixture = TestBed.createComponent(GestionClasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
