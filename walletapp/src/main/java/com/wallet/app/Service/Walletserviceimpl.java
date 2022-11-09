package com.wallet.app.Service;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dao.WalletDaoMysqlImpl;
import com.wallet.app.dao.WalletDaoUtility;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

import java.util.Map;

public class Walletserviceimpl implements WalletService {

    //private WalletDao walletRepository = new WalletDaoImpl();
    private WalletDao walletRepository = new WalletDaoMysqlImpl(WalletDaoUtility.getConnectionToMySQL());


    @Override
    public Wallet registerWallet(Wallet newWallet) throws WalletException {
       if(newWallet != null){
        return this.walletRepository.addWallet(newWallet);
    }else throw new WalletException("No such wallet");}

    @Override
    public Boolean login(Integer walletId, String password) throws WalletException {
        try{
        Boolean b = false;
        Wallet wallet = this.walletRepository.getWalletById(walletId);
        if (wallet != null){
            if (wallet.getPassword().equals(password))
                b = true;
        }else throw new WalletException("Incorrect password");
        return b;
    }finally{ }}

    @Override
    public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException {
        Wallet wallet = this.walletRepository.getWalletById(walletId);
        if(wallet.getId() != walletId)throw new WalletException("ID NOT MATCHED");
        else{
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.updateWallet(wallet);
        return wallet.getBalance();
    }}

    @Override
    public Double showWalletBalance(Integer walletId) throws WalletException {
        try{
            if(walletId != null){
        Wallet wallet = this.walletRepository.getWalletById(walletId);
        return wallet.getBalance();}
            else throw new  WalletException("INVALID DATA");
    }finally {

        }
    }

    @Override
    public Double fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException {
        try{
        Wallet wallet = this.walletRepository.getWalletById(fromId);
        Wallet wallet1 = this.walletRepository.getWalletById(toId);
        if(wallet.getBalance()-amount<0)throw new WalletException("No ENOUGH BALANCE");
        else{
        wallet.setBalance(wallet.getBalance() - amount);
        this.walletRepository.updateWallet(wallet);
        wallet1.setBalance(wallet1.getBalance() + amount);
        this.walletRepository.updateWallet(wallet1);
        return wallet.getBalance();
    }}finally {

        }
        }


    @Override
    public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException {
        try {
            if(this.login(walletId,password)){
                return this.walletRepository.deleteWalletById(walletId);
            }
            else throw new WalletException("Incorrect password");
        }
        finally { }
    }

    @Override
    public Double withdrawFunds(Integer walletId, Double amount) throws WalletException {
try {
    Wallet wallet1 = this.walletRepository.getWalletById(walletId);
    if (wallet1.getBalance() - amount < 0) throw new WalletException("Insufficient Money");
    wallet1.setBalance(wallet1.getBalance() - amount);
    this.walletRepository.updateWallet(wallet1);
    return wallet1.getBalance();
}finally {

}
}
   public Map<Integer,Wallet> getAllWallets(){
        return this.walletRepository.getWallets();
    }}