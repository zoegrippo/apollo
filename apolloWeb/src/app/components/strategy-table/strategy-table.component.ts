import { StrategyService } from './../../services/strategy/strategy.service';
import { Component, OnInit } from '@angular/core';
import { Strategy } from '../../classes/strategy';
import { GridOptions } from 'ag-grid';


@Component({
  selector: 'app-strategy-table',
  templateUrl: './strategy-table.component.html',
  styleUrls: ['./strategy-table.component.css']
})
export class StrategyTableComponent implements OnInit {

  strategyNames: string[];
  newStrategy: Strategy;
  strategies: Strategy[];

  gridOptions: GridOptions;
  columnDefs: any[];
  rowData: Strategy[];
  grid: any;

  constructor(private strategyService: StrategyService) { }

  ngOnInit() {
    this.initGrid();
    this.resetNewStrategy();
    this.getStrategyNames();
    this.getStrategies();
  }

  initGrid(): void {
    this.gridOptions = <GridOptions>{
      onGridReady : () => {
        this.gridOptions.api.sizeColumnsToFit();
      }
    };
    this.initColumnData();
    this.gridOptions.columnDefs = this.columnDefs;
    this.gridOptions.rowData = [];
  }

  initColumnData(): void {
    this.columnDefs = [
      {headerName: 'Name', field: 'strategyName'},
      {headerName: 'Ticker', field: 'stock'},
      {headerName: 'Volume', field: 'startingVol'},
      {headerName: 'Exit Profit %', field: 'exitProfitPercent'},
      {headerName: 'Exit Loss %', field: 'exitLossPercent'},
      {headerName: 'Std Devs', field: 'stdDevs'},
      {headerName: 'Timespan', field: 'shortTime'},
      {headerName: 'Running', field: 'onoff'}
    ];
  }

  getStrategies(): void {
    this.strategyService.getStrategies()
      .subscribe(strategies => {
        console.log(strategies);
        this.strategies = strategies;
        this.gridOptions.rowData = this.strategies;
        // setRowData is what re-renders the grid with the new data
        this.gridOptions.api.setRowData(strategies);
      });
  }

  getStrategyNames(): void {
    this.strategyService.getStrategyNames()
      .subscribe(strategyNames => {
        this.strategyNames = strategyNames;
        if (this.strategyNames.length) {
          this.newStrategy.strategyName = this.strategyNames[0];
        }
      });
  }

  createStrategy(): void {
    console.log(this.newStrategy);
    this.strategyService.createStrategy(this.newStrategy)
      .subscribe(id => {
        console.log(id);
        this.newStrategy.id = id;
        this.strategies.push(this.newStrategy);
        this.gridOptions.rowData = this.strategies;
        // udpateRowData is what will re-render the grid
        this.gridOptions.api.updateRowData({add: [this.newStrategy]});
        this.resetNewStrategy();
      });
  }

  resetNewStrategy(): void {
      this.newStrategy = new Strategy(
      null,
      this.strategyNames ? this.strategyNames[0] : null,
      {'id': 1},
      true,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }

}
