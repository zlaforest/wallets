package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 08/02/2018.
 */
public class SimpleUser implements User{

    int id;
    String name;

    public SimpleUser(){
    }

    public SimpleUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){return id;}

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name; }

    public List<Wallet> getWallets() {
        return null;
    }

}
