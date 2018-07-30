import { Component, OnInit } from '@angular/core';
import { StrategyService } from '../../services/strategy/strategy.service';
import { Strategy } from '../../services/classes/strategy';


@Component({
  selector: 'app-create-strategy',
  templateUrl: './create-strategy.component.html',
  styleUrls: ['./create-strategy.component.css']
})
export class CreateStrategyComponent implements OnInit {

  strategyNames: string[];
  strategies: Strategy[];
  newStrategy: Strategy;

  constructor(private strategyService: StrategyService) { }

  ngOnInit() {
    this.getStrategyNames();
    this.getStrategies();
    this.newStrategy = new Strategy(
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }

  getStrategyNames(): void {
    this.strategyService.getStrategyNames()
      .subscribe(strategyNames => this.strategyNames = strategyNames);
  }

  getStrategies(): void {
    this.strategyService.getStrategies()
      .subscribe(strategies => this.strategies = strategies);
  }

  onSubmit() {
    this.createStrategy(this.newStrategy);
  }

  createStrategy(strategy: Strategy): void {
    this.strategyService.createStrategy(strategy)
      .subscribe(id => {
        strategy.id = id;
        this.strategies.push(strategy);
      });
  }

}
