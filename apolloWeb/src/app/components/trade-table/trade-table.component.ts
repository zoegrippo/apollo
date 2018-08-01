import { GridOptions } from 'ag-grid';
import { Trade } from '../../classes/trade';
import { TradeService } from '../../services/trade/trade.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trade-table',
  templateUrl: './trade-table.component.html',
  styleUrls: ['./trade-table.component.css']
})
export class TradeTableComponent implements OnInit {

  trades: Trade[];
  gridOptions: GridOptions;
  columnDefs: any[];
  rowData: Trade[];

  constructor(private tradeService: TradeService) { }

  ngOnInit() {
    this.initGrid();
    this.getTradesByStrategyId(1);
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
      {headerName: 'Ticker', field: 'stock'},
      {headerName: 'Volume', field: 'size'},
      {headerName: 'Price', field: 'price'},
      {headerName: 'Buy', field: 'buy'},
      {headerName: 'Date', field: 'tradeDate'},
      {headerName: 'Status', field: 'state'}
    ];
  }

  getTradesByStrategyId(strategyId: number): void {
    this.tradeService.getTradesByStrategyId(strategyId)
      .subscribe(trades => {
        console.log(trades);
        this.trades = trades;
        this.gridOptions.rowData = this.trades;
        // setRowData is what re-renders the grid with the new data
        this.gridOptions.api.setRowData(trades);
      });
  }

}
