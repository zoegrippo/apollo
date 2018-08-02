import { STRATEGIES } from '../../classes/mock-strategies';
import { STRATEGY_NAMES } from '../../classes/mock-strategies';
import { SERVER, PORT } from '../../constants/configConstants';
import { Strategy } from '../../classes/strategy';
import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StrategyService {

  httpOptions: any;

  constructor(private http: HttpClient) {
  }

  getStrategyNames(): Observable<string[]> {
    return of(STRATEGY_NAMES);
  }

  getStrategies(): Observable<Strategy[]> {
    const url = `${SERVER}:${PORT}/strategy`;
    return this.http.get<Strategy[]>(url);
  }

  createOrUpdateStrategy(strategy: Strategy): Observable<any> {
    // http request to create strategy
    // return new strategy id
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    const url = `${SERVER}:${PORT}/strategy`;
    return this.http.post(url, JSON.stringify(strategy), this.httpOptions);
  }


  extractData(response: Response): any {
    const body = response.json();
    return body;
  }
}
