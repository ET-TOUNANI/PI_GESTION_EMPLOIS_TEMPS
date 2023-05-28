import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewSalleComponent } from './add-new-salle.component';

describe('AddNewSalleComponent', () => {
  let component: AddNewSalleComponent;
  let fixture: ComponentFixture<AddNewSalleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddNewSalleComponent]
    });
    fixture = TestBed.createComponent(AddNewSalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
