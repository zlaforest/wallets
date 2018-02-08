package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class SimpleUser implements User{

    int id;
    String name;

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){return this.id;}

    public String getName() {
        return this.name;
    }


    public List<Wallet> getWallets() {
        return null;
    }

}
