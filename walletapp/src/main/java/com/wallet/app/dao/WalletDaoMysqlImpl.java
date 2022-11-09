package com.wallet.app.dao;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class WalletDaoMysqlImpl implements WalletDao{

    private Connection connection;

    public WalletDaoMysqlImpl(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public Wallet addWallet(Wallet newWallet) throws WalletException {

            String sql = "INSERT INTO wallet(id,name,balance,password) VALUES(?,?,?,?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, newWallet.getId());
                preparedStatement.setString(2, newWallet.getName());
                preparedStatement.setDouble(3, newWallet.getBalance());
                preparedStatement.setString(4, newWallet.getPassword());
                System.out.println(preparedStatement);
                Integer count = preparedStatement.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return newWallet;

        }

    @Override
    public Wallet getWalletById(Integer walletId) throws WalletException {
        Wallet wallet = null;
        if (connection != null) {
            String sql = "Select * from wallet where id=?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, walletId);
                ResultSet resultset = preparedStatement.executeQuery();
                while (resultset.next()) {
                    wallet = new Wallet(resultset.getInt(1), resultset.getString(2), resultset.getDouble(3),
                            resultset.getString(4));
                }

            } catch (SQLException e) {
                throw new WalletException(e.getMessage());
            }

        }
        return wallet;
    }

    @Override
    public Wallet updateWallet(Wallet updateWallet)throws WalletException
    {
        Wallet wallet= new Wallet();
        String sql = "UPDATE wallet set Balance=? WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, updateWallet.getBalance());
            preparedStatement.setInt(2, updateWallet.getId());
            preparedStatement.executeUpdate();
            String q="Select * from wallet where id="+updateWallet.getId()+"";
            ResultSet rs=preparedStatement.executeQuery(q);
            if(rs.next())
            {
                wallet = new Wallet
                        (rs.getInt("id"),rs.getString("name"),rs.getDouble("balance"),rs.getString("password")
                        );
                return wallet;

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return wallet;
    }

    @Override
    public Wallet deleteWalletById(Integer walletID)throws WalletException
    {
        Wallet wallet = new Wallet();
        String sql="DELETE FROM wallet WHERE id="+walletID+"";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            Integer count = preparedStatement.executeUpdate();
            if (count == 1)
                System.out.println("Employee removed successfully from DB.");
            else
                System.out.println("Employee could not be removed to DB.");
        }

        catch (SQLException e) {
            e.printStackTrace();
        }


        return wallet;

    }

    @Override
    public Map<Integer, Wallet> getWallets() {
        return null;
    }
}
