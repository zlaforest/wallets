import { Injectable } from '@angular/core';
import {SteakService} from "./steak.service";
import {BunService} from "./bun.service";

@Injectable()
export class BurgerService {

  constructor(public bunService: BunService, public steakService: SteakService)
  { }

  getPrice(){
    return 20;
  }

}
