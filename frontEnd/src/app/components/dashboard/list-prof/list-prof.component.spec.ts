import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProfComponent } from './list-prof.component';

describe('ListProfComponent', () => {
  let component: ListProfComponent;
  let fixture: ComponentFixture<ListProfComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListProfComponent]
    });
    fixture = TestBed.createComponent(ListProfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
