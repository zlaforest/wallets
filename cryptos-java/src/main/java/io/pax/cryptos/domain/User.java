package io.pax.cryptos.domain;

import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public interface User {

    int getId();
    List<? extends Wallet> getWallets();
    String getName();
    //List<Wallet> getWallets();


}
