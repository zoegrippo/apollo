import { TradeGraphComponent } from './components/trade-graph/trade-graph.component';
import { TradeTableComponent } from './components/trade-table/trade-table.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';
import { ChartsModule } from 'ng2-charts';

import { AppComponent } from './app.component';
import { StrategyTableComponent } from './components/strategy-table/strategy-table.component';

@NgModule({
  declarations: [
    AppComponent,
    StrategyTableComponent,
    TradeTableComponent,
    TradeGraphComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ChartsModule,
    AgGridModule.withComponents([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
