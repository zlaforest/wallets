package io.pax.cryptos.domain;

import java.util.List;
import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */
public interface Wallet {

    int getId();
    String getName();
    //Optional<User> getUser();
    default Optional<User> getUser(){
        return null;
    }
    List<? extends Line> getLines();

}
