import { Component, OnInit } from '@angular/core';
import {getQueryValue} from "@angular/core/src/view/query";
import {Wallet} from "../model/wallet";
//import {priceToDollar} from "../model/coins";
import {PricingService} from "../pricing.service";

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {

  wallet = new Wallet();

  constructor(public pricingService:PricingService) {
    /*this.wallet.pricingService = pricingService;
    pricingService.loadPrices().then(data => console.log(' >>>> ', data));
*/
    this.wallet.pricingService = pricingService;
    pricingService.loadPrices()
      .then( data => console.log('>>>>',data))
      .then( () => this.initWallet() );
    //     ^^^ function without param ; prices are now loaded
  }

  initWallet(){
    this.wallet.deposit(100000);
    this.wallet.buy(1, 'BTC');
    this.wallet.buy(10, 'XRP');
  }

  ngOnInit() {


  }

  deposit(value:string) {
    this.wallet.deposit(parseFloat(value));
  }

 /* _totalDollarValue(){
    return (this.wallet._totalDollarValue());
  }*/

  totalDollarValue() {
    this.loadPrices();
    return (this.wallet.totalDollarValue());
  }

  buy(value:string, symbol: string) {
    let stock = (this.wallet.stock_dollars());
    if(stock > parseFloat(value)) this.wallet.buy(parseFloat(value), symbol);
    else console.log('>>> Stock not enough, deposit some $');
   // this.wallet.buy(parseFloat(value), symbol);
  }

  sell(value: string, symbol: string){
    this.wallet.sell(parseFloat(value), symbol);
  }

  getColor(symbol: string){
    return (this.pricingService.getColor(symbol));
  }

  loadPrices(){
    this.wallet.pricingService.loadPrices();
    this.getLinesOrderByValue();
  }

  getLinesOrderByValue(){
    this.wallet.lines.map(line => line.value).sort();

  }



}
