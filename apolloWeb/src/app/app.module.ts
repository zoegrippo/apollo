import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';

import { AppComponent } from './app.component';
import { StrategyTableComponent } from './components/strategy-table/strategy-table.component';

@NgModule({
  declarations: [
    AppComponent,
    StrategyTableComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AgGridModule.withComponents([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
