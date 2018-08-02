import { Strategy } from './../../classes/strategy';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MediatorService {
  selectedStrategy = new Subject<Strategy>();

  constructor() { }
}
