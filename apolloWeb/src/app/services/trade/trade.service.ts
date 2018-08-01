import { Trade } from '../../classes/trade';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { of, Observable } from 'rxjs';
import { SERVER, PORT } from '../../constants/configConstants';
import { TRADES } from '../../classes/mock-trades';

@Injectable({
  providedIn: 'root'
})
export class TradeService {

  constructor(private http: HttpClient) { }

  getTradesByStrategyId(strategyId: number): Observable<Trade[]> {
    const url = `${SERVER}:${PORT}/trade/${strategyId}`;
    // return this.http.get<Trade[]>(url);
    return of(TRADES);
  }

  getLatestTrades(strategyId: number, date: any) {
    const url = `${SERVER}:${PORT}/trade`;
    let params = new HttpParams();
    // HttpParams is immutable so append methods return new instances
    params = params.append('id', String(strategyId));
    params = params.append('time', String(date));
    return this.http.get<Trade[]>(url, {'params': params});
  }


}
