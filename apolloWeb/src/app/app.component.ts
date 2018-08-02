import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'apolloWeb';
  showStrategyTable = true;
  buttonText = 'Show Trades';
  toggleTable(): void {
    this.showStrategyTable = !this.showStrategyTable;
  }
}
