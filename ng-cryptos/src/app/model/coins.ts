export interface Coin{
    name:string;
    symbol:string;
    price: number;
    up: boolean;
}

// dead code
/*
export const coins: Coin[] = [
    {
        name: 'Bitcoin',
        symbol: 'BTC',
        price: 10300
    },
    {
        name: 'Ethereum',
        symbol: 'ETH',
        price: 1100
    },
    {
        name: 'Ripple',
        symbol: 'XRP',
        price: 1.13
    }, {
        name: 'Bitcoin Cash',
        symbol: 'BCH',
        price: 1500
    }, {
        name: 'Cardano',
        symbol: 'ADA',
        price: 0.5
    }, {
        name: 'Stellar',
        symbol: 'XLM',
        price: 0.522
    }, {
        name: 'Néo',
        symbol: 'NEO',
        price: 143.03
    }, {
        name: 'Litecoin',
        symbol: 'LTC',
        price: 163.44
    }
];

export function priceToDollar(quantity, symbol){
    for(let index = 0; index<(coins.length); index++){
        let coin = coins[index];
        if(symbol == coin.symbol){
            //console.log('Having', coin);
            return (quantity*coin.price);
        }
    }
    throw (symbol + ' not found');
    // return null;
}*/
// module & exports only exist in NodeJS : 2011
//module.exports = coins;

