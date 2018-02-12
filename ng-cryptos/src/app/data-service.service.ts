import {Injectable} from '@angular/core';
import {User} from "./model/user";
import {HttpClient} from "@angular/common/http";
import {Wallet} from "./model/wallet";

@Injectable()
export class DataService {

  constructor(public http: HttpClient) {
  }

  fetchUsers(): Promise<User[]> {
    console.log('>>>>>> ');
    return this.http
      .get('http://localhost:8080/cryptos/api/users')
      .toPromise()
      .then(data => {
        //console.log(data);
        return data as User[]
      })
  }

  fechWallets(): Promise<Wallet[]>{
    console.log('>>>>>> ');
    return this.http
      .get('http://localhost:8080/cryptos/api/wallets')
      .toPromise()
      .then(data => {
        //console.log(data);
        return data as Wallet[]
      })
  }


  fetchUserWithWallets(user: User): Promise<User> {
    let url='http://localhost:8080/cryptos/api/users/'+user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with wallet: ', data);
        return data as User
      })

  }

  createWallet(wallet:Wallet){
    let url = 'http://localhost:8080/cryptos/api/wallets';

    let dto={ // Data transfer object. Aux petits oignons pour Jax-B
      name: wallet.name,
      user: wallet.user
    };

    console.log('Sending wallet: ', wallet);
    // When posting, we send data to url
    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data));
      //.catch(e => console.error('Fail :(', e));
  }

  createUser(user:User){
    let url = 'http://localhost:8080/cryptos/api/users';

    let dto={ // Data transfer object. Aux petits oignons pour Jax-B
      name: user.name,
      id: user.id,
      wallets:user.wallets
    };

    console.log('Sending user: ', user);
    // When posting, we send data to url
    return this.http.post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data));
    //.catch(e => console.error('Fail :(', e));
  }




}
