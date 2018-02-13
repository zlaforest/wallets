package io.pax.cryptos.ws;

import io.pax.cryptos.dao.UserDao;
import io.pax.cryptos.domain.jdbc.FullUser;
import io.pax.cryptos.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 09/02/2018.
 */
@Path("users") //http://localhost:8080/cryptos/api/users
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }

    @GET
    @Path("{id}") // this is a PathParam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        FullUser user = new UserDao().findUserWithWallets(userId);
        System.out.println(user);
        return user;
    }

    // JaxRS annotations
   @POST
    /* returns future wallets with an id */
    public User createUser(FullUser user /**sent user, has no id*/) {

        // Guards
        //Optional<User> option = wallet.getUser();
      if (user.getName().length() < 2) {
            throw new NotAcceptableException("\n406: No user name must have at least 2 letters\n");
        }

        try {
            int id = new UserDao().createUser(user.getName());
            return new FullUser(id, user.getName(), user.getWallets());

        } catch (SQLException e) {
            // Breaks POLA
            throw new ServerErrorException("\nDatabase error, sorry\n", 500);
        }


    }
}


