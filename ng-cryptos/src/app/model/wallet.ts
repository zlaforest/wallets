//import {Coin, priceToDollar} from "./coins";

import {Coin} from "./coins";
import {PricingService} from "../pricing.service";

export class Line {
  value: number;
  constructor(public symbol: string, public quantity: number) {

  }
}

export class Wallet {

  lines: Line[] = [];
  pricingService: PricingService;

  deposit(dollars: number) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    if (usdLine == undefined) {
      let l = new Line('USD', dollars);
      //l.value = dollars;
      this.lines.push(l);
    }
    else { usdLine.quantity += dollars;
      //usdLine.value += dollars;
      }

    console.log("Deposit", dollars);
  }

  buy(quantity: number, symbol: string) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    let symbolPrice = this.pricingService.priceToDollar(quantity, symbol);

    usdLine.quantity -= symbolPrice;

    let symbolLine = this.lines.find(line => line.symbol === symbol);
    if (symbolLine == undefined){
      let l = new Line(symbol, quantity);
      l.value = symbolPrice;
      this.lines.push(l);
      //symbolLine.value = ;
    }
    else {symbolLine.quantity += quantity;
      symbolLine.value += symbolPrice;}



    console.log("Buying", symbolPrice);
  }

  totalDollarValue():number {
    let total = 0;
    for(let i=0; i<this.lines.length; i++){
      let line = this.lines[i];
      if(line.symbol === 'USD'){
        total += line.quantity;
      }
      else{
        total += this.pricingService.priceToDollar(line.quantity, line.symbol);
      }
    }
    return total;
  }

  sell(quantity: number, symbol: string){
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    let symbolPrice = this.pricingService.priceToDollar(quantity, symbol);

    usdLine.quantity += symbolPrice;

    let symbolLine = this.lines.find(line => line.symbol === symbol);
    if (symbolLine == undefined){
      let l = new Line(symbol, quantity);
      l.value = symbolPrice;
      this.lines.push(l);
    }
    else {symbolLine.quantity -= quantity;
           symbolLine.value -= symbolPrice;}

    console.log("Selling", symbolPrice);

   /*let line = this.lines.find(line => line.symbol === 'USD');
   let coinAmount = this.pricingService.priceToDollar(quantity, symbol);
   let dollarAmount = line.quantity ;

   let newAmount = dollarAmount + coinAmount;
   (this.lines.find(Line => Line.symbol === 'USD').quantity) = newAmount;

   //this.lines.push(new Line(symbol, newAmount));
   console.log("Selling", this.value());*/
   }

  stock_dollars() {
    let usdLine = this.lines.find(line => line.symbol === 'USD');

    if (usdLine == undefined) return 0;
    return usdLine.quantity;

  }

  /*value(){
   return ('>>> Wallet quantity in dollars : ' + this.lines.find(Line => Line.symbol === 'USD').quantity + ' $');
   //return this.lines.find(Line => Line.symbol === 'USD').quantity + '';
   }*/



  /*_totalDollarValue(){
   let usdSum = 0;

   for(let index = 0; index<(Coin.length); index++){
   let coin = Coin[index];
   let symbLine = (this.lines.find(line => line.symbol) == coin.symbol);
   if(symbLine != undefined && symbLine != 'USD'){
   usdSum += priceToDollar(symbLine.quantity, symbLine.symbol);
   }
   else if (symbLine === 'USD'){
   usdSum += symbLine.quantity;
   }
   }
   return usdSum;
   }*/

}
