import { environment } from './../../../environments/environment';
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
    const url = `${environment.host}/strategy`;
    return this.http.get<Strategy[]>(url);
  }

  createOrUpdateStrategy(strategy: Strategy): Observable<any> {
    // http request to create strategy
    // return new strategy id
    strategy.strategyName = strategy.strategyName.replace(' ', '').toLowerCase();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    const url = `${environment.host}/strategy`;
    return this.http.post(url, JSON.stringify(strategy), this.httpOptions);
  }


  extractData(response: Response): any {
    const body = response.json();
    return body;
  }
}
