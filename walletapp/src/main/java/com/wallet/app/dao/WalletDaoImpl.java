package com.wallet.app.dao;


import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

import java.util.HashMap;
import java.util.Map;

public class WalletDaoImpl implements WalletDao{

    Map<Integer, Wallet> wallets = new HashMap<>();



    @Override
    public Wallet addWallet(Wallet newWallet) throws WalletException {
        try{
            if(wallets.keySet().contains(newWallet.getId())) throw new WalletException("Wallet ID already exist");
            else this.wallets.put(newWallet.getId(), newWallet);
        }
        finally {
            return this.wallets.get(newWallet.getId());
        }
    }

    @Override
    public Wallet getWalletById(Integer walletId) throws WalletException {
        try {
            if (wallets.keySet().contains(walletId)) {
                this.wallets.get(walletId);
                return this.wallets.get(walletId);
            } else throw new WalletException("INVALID");
        } finally {
        }
    }

    @Override
    public Wallet updateWallet(Wallet updateWallet) throws WalletException {
        try {
            if(updateWallet != null) {
                this.wallets.put(updateWallet.getId(), updateWallet);
                return (wallets.get(updateWallet.getId()));
            } else { throw new WalletException("");}
        }finally{}
    }

    @Override
    public Wallet deleteWalletById(Integer walletId) throws WalletException {
        try{
            if(!wallets.keySet().contains(walletId)) throw new WalletException("Wallet not found. You don't have any existing account!");
            else{
                return (wallets.remove(walletId));
            }
        }
        finally { }
    }

      public  Map<Integer, Wallet> getWallets() {
        return wallets;}
    }




