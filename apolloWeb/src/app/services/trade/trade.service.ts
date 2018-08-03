import { environment } from './../../../environments/environment.prod';
import { MediatorService } from './../mediator/mediator.service';
import { Trade } from '../../classes/trade';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { of, Observable, ReplaySubject } from 'rxjs';
import { SERVER, PORT } from '../../constants/configConstants';
import { TRADES } from '../../classes/mock-trades';

@Injectable({
  providedIn: 'root'
})
export class TradeService {

  trades = new ReplaySubject<Trade[]>();

  constructor(
    private http: HttpClient,
    private mediatorService: MediatorService
  ) {
  }

  getTradesByStrategyId(strategyId: number): void {
    console.log('trade-service: getting trades for strategyId: ' + strategyId);
    const url = `${environment.host}/trade/${strategyId}`;
    this.http.get<Trade[]>(url).subscribe(trades => {
      this.trades.next(trades);
      console.log('trade-service: trades have been returned from http');
    });

    // of(TRADES).subscribe(trades => {
    //   this.trades.next(trades);
    // });
  }

  getLatestTrades(strategyId: number, tradeId: any) {
    const url = `${environment.host}/trade`;
    let params = new HttpParams();
    // HttpParams is immutable so append methods return new instances
    params = params.append('id', String(strategyId));
    params = params.append('time', String(tradeId));
    return this.http.get<Trade[]>(url, {'params': params});
  }


}
