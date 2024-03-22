import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisegniComponent } from './disegni.component';

describe('DisegniComponent', () => {
  let component: DisegniComponent;
  let fixture: ComponentFixture<DisegniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DisegniComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DisegniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
