package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.Wallet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
public class FullUser extends SimpleUser{

    List<Wallet> wallets = new ArrayList<>();

    public FullUser(){
        super();
    }

    public FullUser(int id, String name, List<Wallet> wallets) {
        super(id, name);
        this.wallets = wallets;
    }

    @Override
    public String toString() {
        return "(id:"+this.id+") " + this.name +":\t"+ this.wallets;
    }

    @Override
    public List<Wallet> getWallets() {
        return this.wallets;
    }
}
