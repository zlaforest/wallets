package io.pax.cryptos.dao;

import io.pax.cryptos.domain.jdbc.SimpleWallet;
import io.pax.cryptos.domain.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 06/02/2018.
 */
public class WalletDao {

    JdbcConnector connector = new JdbcConnector();

    public List<Wallet> listWallets() throws SQLException{

         List<Wallet> wallets = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM wallet");

        while(rs.next()){
            String name = rs.getString("name");
            int id = rs.getInt("id");
            wallets.add(new SimpleWallet(id, name));
            System.out.println("[id:"+id +"]\t" +  name);

        }
        rs.close();
        stmt.close();
        conn.close();

        return wallets;
    }

    public int createWallet(int userId, String name) throws SQLException {
        // Most important stuff of your life: NEVER EVER String concatenation in JDBC
        String query = "INSERT INTO wallet (name, user_id) VALUES (?,?)"; //('test',2)
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(2, userId);
        stmt.setString(1,name);
       /*int rows = stmt.executeUpdate(query);

        if(rows!=1){
            throw new SQLException("Something wrong happened with : "+query);
        }*/

        stmt.executeUpdate();

        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        int test_id = keys.getInt(1);

        stmt.close();
        conn.close();

        return test_id;


    }

    public void deleteWallet(int walletId) throws SQLException {
        String query = "DELETE FROM wallet WHERE id=?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, walletId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void deleteByName(String name) throws SQLException {

        String query = "DELETE FROM wallet WHERE name=?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

      public List<Wallet> findByName(String extract) throws SQLException {
        String query = "SELECT * FROM wallet WHERE name LIKE ?";

          Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, extract +"%");
        ResultSet rs = stmt.executeQuery();

        List<Wallet> wallets = new ArrayList<>();
        while (rs.next()) {
            wallets.add(new SimpleWallet(rs.getInt("id"), rs.getString("name")));
            System.out.println(rs.getString("name"));
        }
        stmt.close();

        return wallets;

    }

    public void updateWallet(int walletId, String newName) throws SQLException {
        String query = "UPDATE wallet SET name = ? WHERE id = ? ";
        //System.out.println(query);


        Connection conn = this.connector.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setInt(2,walletId);
        statement.setString(1,newName);
        statement.executeUpdate();
        statement.close();
        conn.close();

    }

    public void deleteAll(int userId) throws SQLException { // Delete all wallets from users

        String query = "DELETE FROM wallet WHERE user_id=?";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }



    public static void main(String[] args) throws SQLException {
        //System.out.println(new WalletDao().listWallets());
        //System.out.println(new WalletWs().getWallet());
        //new WalletDao().createWallet(2, "Some cool wallet name");
        //new WalletDao().deleteWallet(25);

        WalletDao dao = new WalletDao();
      //  dao.createWallet(2,"Test");
        //dao.deleteWallet(34);
        //dao.deleteByName("Test");
       // List<Wallet> wallets = new ArrayList<Wallet>();
       // wallets = dao.findByName("i");
        //System.out.println(wallets.size());
       //dao.deleteAll(12);

    }

}
