import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFiliereComponent } from './edit-filiere.component';

describe('EditFiliereComponent', () => {
  let component: EditFiliereComponent;
  let fixture: ComponentFixture<EditFiliereComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditFiliereComponent]
    });
    fixture = TestBed.createComponent(EditFiliereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
