import { Injectable } from '@angular/core';
import {Coin} from "./model/coins";
import {HttpClient} from "@angular/common/http";


@Injectable()
export class PricingService {

  // Asynchrone
  coins: Coin [];

  constructor(public http:HttpClient) { }

  loadPrices() {
    let url = 'https://api.coinmarketcap.com/v1/ticker/?limit=10';

    //console.log('>>>>>>>>>> Updating ok');
    function mapper(coin) {
      return {
        name: coin.name,
        symbol: coin.symbol,
        price: coin.price_usd,
        up : coin.percent_change_1h>0 ? true:false
      }
    }

    return this.http.get(url)
      .toPromise()
      .then(internetCoins => (internetCoins as any).map(mapper))
      .then(joliCoins => {
        this.coins = joliCoins;
        return joliCoins;

      });
  }
    /*return this.http.get(url)
      .toPromise()
      .then(internetCoins => {
          this.coins = (internetCoins as any).map(coin => ({
            name: coin.name,
            symbol: coin.symbol,
            price: coin.price_usd
          }));
        return this.coins;
        });
  }*/

  priceToDollar(quantity, symbol){
    for(let index = 0; index<(this.coins.length); index++){
      let coin = this.coins[index];
      if(symbol == coin.symbol){
        //console.log('Having', coin);
        return (quantity*coin.price);
      }
    }
    throw (symbol + ' not found');
  }


  getColor(symbol: string){
    if(symbol === 'USD') return 'black';

    let upCoin = this.coins.find(coin => coin.symbol === symbol);
    if(upCoin.up == true) return 'green';
    return 'red';
  }

}
