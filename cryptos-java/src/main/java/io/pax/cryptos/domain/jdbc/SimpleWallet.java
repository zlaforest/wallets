package io.pax.cryptos.domain.jdbc;

import io.pax.cryptos.domain.Line;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import java.util.List;
import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */
public class SimpleWallet implements Wallet {

    int id;
    String name;

    // Useless constructor, but for Java EE
    public SimpleWallet(){
    }

    public SimpleWallet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<User> getUser() {
        return Optional.empty();
    }

    @Override
    public List<? extends Line> getLines() {
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {this.name = name;}


}
