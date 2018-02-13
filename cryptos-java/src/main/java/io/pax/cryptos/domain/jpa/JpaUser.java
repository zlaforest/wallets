package io.pax.cryptos.domain.jpa;

import io.pax.cryptos.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
@Entity
public class JpaUser implements User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;

    @OneToMany
    List<JpaWallet> wallets = new ArrayList<>();

    // unwritten default empty constructor
    @Override
    public int getId() {return this.id;}

    @Override
    public String getName() {return this.name;}

    @Override
    public List<JpaWallet> getWallets() {//return null;}
            return this.wallets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }*/

    @Override
    public String toString() {
        return this.getName();
    }
}
