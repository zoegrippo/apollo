import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { TradeService } from '../../services/trade/trade.service';
import { Trade } from '../../classes/trade';

@Component({
  selector: 'app-trade-graph',
  templateUrl: './trade-graph.component.html',
  styleUrls: ['./trade-graph.component.css']
})
export class TradeGraphComponent implements OnInit {

  chart: Chart;
  trades: any[];
  xaxis = ['1', '2', '3', '4'];
  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.getTrades();
    this.initChart();
  }

  getTrades() {
    this.tradeService.getTradesByStrategyId(1)
      .subscribe(trades => {
        console.log(trades);
        this.trades = [1, 2, -2, 3];
      });
  }

  addSomething() {
    this.chart.data.datasets[0].data.push(this.trades[1] * this.trades[this.trades.length - 1]);
    this.chart.data.labels.push('new');
    this.chart.update();
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
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }],
        }
      }
    });
  }


}
