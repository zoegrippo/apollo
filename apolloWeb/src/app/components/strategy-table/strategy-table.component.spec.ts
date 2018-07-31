import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StrategyTableComponent } from './strategy-table.component';

describe('StrategyTableComponent', () => {
  let component: StrategyTableComponent;
  let fixture: ComponentFixture<StrategyTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StrategyTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StrategyTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
