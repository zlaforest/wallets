package io.pax.cryptos.ws;

import io.pax.cryptos.dao.WalletDao;
import io.pax.cryptos.domain.FullWallet;
import io.pax.cryptos.domain.SimpleUser;
import io.pax.cryptos.domain.User;
import io.pax.cryptos.domain.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by AELION on 06/02/2018.
 */
@Path("wallets") //http://localhost:8080/cryptos/api/wallets
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletWs {

    @GET
    public List<Wallet> getWallet() throws SQLException {
        WalletDao dao = new WalletDao();
        return dao.listWallets();
    }

    // JaxRS annotations
    @POST
    /* returns future wallets with an id */
    public Wallet createWallet(FullWallet wallet /**sent wallet, has no id*/){

        // Guards
        Optional<User> option = wallet.getUser();

        if(! option.isPresent()){
            // 400x : navigator sent wrong information
            throw new NotAcceptableException("\n406: No user Id sent\n");
        }

        if (wallet.getName().length() < 2){
            throw new NotAcceptableException("\n406: No wallet name must have at least 2 letters\n");
        }

        try {
            int id = new WalletDao().createWallet(option.get().getId(), wallet.getName());

            User boundUser = wallet.getUser().get();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            return new FullWallet(id,  wallet.getName(), simpleUser);
        } catch (SQLException e) {
            // Breaks POLA
            throw new ServerErrorException("\nDatabase error, sorry\n", 500);
        }

    }

}
