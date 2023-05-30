import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditDepartementComponent } from './edit-departement.component';

describe('EditDepartementComponent', () => {
  let component: EditDepartementComponent;
  let fixture: ComponentFixture<EditDepartementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditDepartementComponent]
    });
    fixture = TestBed.createComponent(EditDepartementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
