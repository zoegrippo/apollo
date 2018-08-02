import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { TradeService } from '../../services/trade/trade.service';
import { Trade } from '../../classes/trade';
import { MediatorService } from '../../services/mediator/mediator.service';

@Component({
  selector: 'app-trade-graph',
  templateUrl: './trade-graph.component.html',
  styleUrls: ['./trade-graph.component.css']
})
export class TradeGraphComponent implements OnInit {

  chart: Chart;
  trades: any[];
  xaxis: any[];
  profitData: number[];
  ongoingProfit: number;
  constructor(
    private tradeService: TradeService,
    private mediatorService: MediatorService
  ) { }

  ngOnInit() {
    this.profitData = [];
    this.xaxis = [];
    this.ongoingProfit = 0;
    this.initSubscriptions();
    this.initChart();
  }

  computeProfitData(): void {
    let ongoingProfit = 0;
    let price1: number;
    let price2: number;
    let trade1;
    let trade2;
    for (let i = 0; i < this.trades.length - 1; i = i + 2) {
      trade1 = this.trades[i];
      trade2 = this.trades[i + 1];
      price1 = trade1.price * trade1.size;
      price2 = trade2.price * trade2.size;
      trade1.buy ? price1 *= -1 : price2 *= -1;
      ongoingProfit += price1 + price2;
      this.profitData.push(ongoingProfit);
      this.xaxis.push(this.formatDateString(new Date(trade2.tradeDate)));
    }
  }

  formatDateString(date: Date): String {
    return date.getHours().toString() + ':'
      + date.getMinutes().toString() + ':'
      + date.getSeconds().toString();
  }

  initSubscriptions(): void {
    this.mediatorService.selectedStrategy.subscribe(selectedStrategy => {
      this.getTrades(selectedStrategy.id);
    });
    this.tradeService.trades.subscribe(trades => {
      this.trades = trades;
      this.profitData = [];
      this.xaxis = [];
      this.ongoingProfit = 0;
      this.computeProfitData();
      this.chart.data.datasets[0].data = this.profitData;
      this.chart.data.labels = this.xaxis;
      this.chart.update();
    });
  }

  getTrades(strategyId: number): void {
    this.tradeService.getTradesByStrategyId(strategyId);
  }

  initChart(): void {
    this.chart = new Chart('canvas', {
      type: 'line',
      data: {
        labels: this.xaxis,
        datasets: [
          {
            data: this.trades,
            borderColor: '#3cba9f',
            fill: false
          }
        ]
      },
      options: {
        tooltips: {
          callbacks: {
            label: function(tooltipItems, data) {
              let str;
              tooltipItems.yLabel > 0 ? str = `$${tooltipItems.yLabel.toString()}` :
              str = tooltipItems.yLabel.toString().replace('-', '-$');
              return str;
            }
          }
        },
        title: {
          display: true,
          text: 'Profit of Selected Strategy'
        },
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true,
            ticks: {
              callback: function(value, index, values) {
                let str;
                value > 0 ? str = `$${value}` :
                str = value.toString().replace('-', '-$');
                return str;
              }
            }
          }],
        }
      }
    });
  }


}
