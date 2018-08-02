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
    console.log('trade-table initialized');
  }

  initSubscriptions(): void {
    this.tradeService.trades.subscribe(trades => {
      console.log('trade-table got trades: ' + trades);
      this.trades = trades;
      this.gridOptions.rowData = this.trades;
      // setRowData is what re-renders the grid with the new data
      this.gridOptions.api.setRowData(trades);
    });
  }

  initGrid(): void {
    this.gridOptions = <GridOptions>{
      onGridReady : () => {
        this.gridOptions.api.sizeColumnsToFit();
        this.initSubscriptions();
      }
    };
    this.initColumnData();
    this.gridOptions.columnDefs = this.columnDefs;
    this.gridOptions.rowData = [];
    console.log('trade-table ag-grid initialized!');
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

}
