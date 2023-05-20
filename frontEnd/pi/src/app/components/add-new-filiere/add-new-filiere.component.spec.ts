import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewFiliereComponent } from './add-new-filiere.component';

describe('AddNewFiliereComponent', () => {
  let component: AddNewFiliereComponent;
  let fixture: ComponentFixture<AddNewFiliereComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewFiliereComponent]
    });
    fixture = TestBed.createComponent(AddNewFiliereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
