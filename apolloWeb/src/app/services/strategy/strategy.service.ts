import { STRATEGIES } from './../classes/mock-strategies';
import { Strategy } from './../classes/strategy';
import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { STRATEGY_NAMES } from '../classes/mock-strategies';

@Injectable({
  providedIn: 'root'
})
export class StrategyService {

  constructor() {
  }

  getStrategyNames(): Observable<string[]> {
    return of(STRATEGY_NAMES);
  }

  getStrategies(): Observable<Strategy[]> {
    return of(STRATEGIES);
  }

  createStrategy(strategy: Strategy): Observable<number> {
    // http request to create strategy
    // return new strategy id
    return of(5);
  }
}
