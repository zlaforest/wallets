import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class BunService {



  constructor(public http: HttpClient) { }

  getHeight(): Promise <any>{
    let url = 'https://api.coinmarketcap.com/v1/ticker/ethereum/';
    return this.http.get(url)
      .toPromise()
      .then(response => console.log(response))
      .then(response => 22)
      .catch(e => console.log(e));
  }

}
