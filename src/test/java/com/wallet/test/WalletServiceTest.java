package com.wallet.test;

import com.wallet.app.Service.WalletService;
import com.wallet.app.Service.Walletserviceimpl;
import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WalletServiceTest {
    WalletService walletService = new Walletserviceimpl();


    @BeforeAll
    public static void setupTestData() {
        System.out.println("Create all test data");
    }
    @Test
    public void registerWalletTest() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            assertNotNull(wallet);
            assertEquals(100, wallet.getId());
        }catch (WalletException e){
            e.printStackTrace();
        }
    }
    @Test
    public void loginTest() throws WalletException {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(100, "test name1", 100.0, "123"));
            assertEquals(true, walletService.login(100, "123"));
        }catch(WalletException e){
            e.printStackTrace();
        }

    }

    @Test
    void addFundsToWalletTest() {
        try{
        Wallet wallet=walletService.registerWallet(new Wallet(100,"test name1",100.0,"123"));
        assertEquals(200.0,walletService.addFundsToWallet(100,100.0));
    }catch(WalletException e){
            e.printStackTrace();
        }
    }

    @Test
    public void showWalletBalanceTest() {
        try {
            Wallet wallet =walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));

            assertEquals(1000.0, walletService.showWalletBalance(100));
        } catch (WalletException e) {
            e.printStackTrace();
        }
    }


    @Test
    void fundTransferTest() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            Wallet wallet1 = walletService.registerWallet(new Wallet(101, "test name2", 2000.0, "12345"));
            assertEquals(500.0,walletService.fundTransfer(100,101,500.0));
        }catch(WalletException e){
            e.printStackTrace();
        }
    }

    @Test
    void unRegisterWalletTest() {
        try {
            Wallet wallet = walletService.registerWallet(new Wallet(100, "test name1", 1000.0, "123"));
            //  assertNull(wallet);
           // assertEquals(false, walletService.login(100, "1234"), "Incorrect password");

       assertEquals(wallet, walletService.unRegisterWallet(100, "123"));
    }
        catch(WalletException e){
            e.printStackTrace();
        }
    }

    @Test
    void withdrawFundsTest() {
        try{
            Wallet wallet=walletService.registerWallet(new Wallet(100,"test name1",1000.0,"123"));
assertEquals(500.0,walletService.withdrawFunds(100,500.0));
        }catch(Exception e){

        }
    }

  /* @Test
    void getAllWalletsTest() {
        try{
            WalletDao walletRepository = new WalletDaoImpl();
            Wallet wallet1 = walletService.registerWallet(new Wallet(101, "test name2", 2000.0, "12345"));
            System.out.println(wallet1);
            assertEquals(wallet1,walletRepository.getWalletById(102));
    }catch(Exception e){
            e.printStackTrace();}
    }*/
    @AfterAll
    public static void destroyTestData() {
        System.out.println("Clear all test data");
    }
}
