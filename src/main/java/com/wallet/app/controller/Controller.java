package com.wallet.app.controller;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.Service.WalletService;
import com.wallet.app.Service.Walletserviceimpl;

public class Controller {
    public static void main(String[] args) {
        WalletService walletService = new Walletserviceimpl();
        try {
           Wallet wallet = walletService.registerWallet(new Wallet(1, "Ford", 1000.0, "123"));
           Wallet wallet1 = walletService.registerWallet(new Wallet(2,"BMW",1248.0,"12345"));
           System.out.println("This is Wallet ="+wallet);System.out.println("this is Wallet1 ="+wallet1);System.out.println("login = "+walletService.login(1,"123"));
           System.out.println("add funds to wallet1 ="+walletService.addFundsToWallet(1,100.0));
           System.out.println("showwallet Balance ="+walletService.showWalletBalance(1));
           System.out.println("fundTransfer ="+walletService.fundTransfer(1,2,100.0));
           System.out.println("After fund transfer"+wallet);
           System.out.println("After fund transfer"+wallet1);
           System.out.println("unRegisterWallet1"+walletService.unRegisterWallet(1,"123"));
           System.out.println("get all wallets"+walletService.getAllWallets());
           System.out.println("Withdraw funds"+walletService.withdrawFunds(2,100.0));
           System.out.println("Final Wallets"+walletService.getAllWallets());
        } catch (WalletException e) {
            e.printStackTrace();
        }

    }
}
