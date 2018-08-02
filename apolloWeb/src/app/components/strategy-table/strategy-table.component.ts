import { MediatorService } from '../../services/mediator/mediator.service';
import { StrategyService } from '../../services/strategy/strategy.service';
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
  selectedStrategy: Strategy;
  strategies: Strategy[];

  gridOptions: GridOptions;
  columnDefs: any[];
  rowData: Strategy[];
  grid: any;

  constructor(
    private strategyService: StrategyService,
    private mediatorService: MediatorService
  ) { }

  ngOnInit() {
    this.initGrid();
    this.resetNewStrategy();
    this.getStrategyNames();
    this.getStrategies();
  }

  initGrid(): void {
    this.initColumnData();
    this.gridOptions = <GridOptions>{
      onGridReady : () => {
        this.gridOptions.api.sizeColumnsToFit();
      },
      rowSelection: 'single',
      // onRowSelected: this.onRowSelected,
      suppressRowClickSelection: true
    };
    this.gridOptions.columnDefs = this.columnDefs;
    this.gridOptions.rowData = [];
  }

  initColumnData(): void {
    this.columnDefs = [
      {headerName: 'Name', field: 'strategyName', checkboxSelection: true},
      {headerName: 'Ticker', field: 'stock'},
      {headerName: 'Volume', field: 'startingVol', editable: true },
      {headerName: 'Exit Profit %', field: 'exitProfitPercent', editable: true },
      {headerName: 'Exit Loss %', field: 'exitLossPercent', editable: true },
      {headerName: 'Std Devs', field: 'stdDevs', editable: true },
      {headerName: 'Timespan', field: 'shortTime', editable: true },
      {headerName: 'Running', field: 'onoff', editable: true }
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
    this.strategyService.createOrUpdateStrategy(this.newStrategy)
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

  onRowSelected(event): void {
    const node = event.node;
    if (node.selected) {
      this.selectedStrategy = node.data;
      this.mediatorService.selectedStrategy.next(this.selectedStrategy);
      console.log('selected strategy: ' + this.selectedStrategy);
    }
  }

  onCellValueChanged(event): void {
    console.log('cell value changed event');
    console.log(event);
    const updatedStrategy = event.data;
    if (typeof(updatedStrategy.onoff) !== typeof(true)) {
      updatedStrategy.onoff === 'false' ? updatedStrategy.onoff = false : updatedStrategy.onoff = true;
    }
    this.strategyService.createOrUpdateStrategy(updatedStrategy)
      .subscribe(ret => {
        console.log('Updated strategy. Server Response: ' + ret);
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
