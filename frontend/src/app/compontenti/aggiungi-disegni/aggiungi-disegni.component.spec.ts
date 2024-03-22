import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AggiungiDisegniComponent } from './aggiungi-disegni.component';

describe('AggiungiDisegniComponent', () => {
  let component: AggiungiDisegniComponent;
  let fixture: ComponentFixture<AggiungiDisegniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AggiungiDisegniComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AggiungiDisegniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
