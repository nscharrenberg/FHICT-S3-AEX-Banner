/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.TimerTask;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import shared.IFunds;
import shared.IStockExchange;

/**
 *
 * @author Noah Scharrenberg
 */
public class BannerController extends UnicastRemoteObject {
    private AEXBanner banner;
    private IStockExchange stockExchange;
    private static String bindingName = "stockexchange";
    private Registry registry = null;
    private Timer timer;
    
    public BannerController(AEXBanner banner, String ipAddress, int portNumber) throws RemoteException {
        this.banner = banner;
        this.timer = new Timer();
        connect(ipAddress, portNumber);
        System.out.println("Client: Connected to " + ipAddress + " with port " + portNumber);     
        timer.scheduleAtFixedRate(new TimerTask() { 
            @Override
            public void run() {
                updateRates();
            }
        
        }, 0, 1000);
    }
    
    public void stop() {
        timer.cancel();
    }
    
    public void updateRates() {
        String ratesLbl = "";
        
        try {
            for (IFunds fund : stockExchange.getRates()) {
                ratesLbl += fund.getName() + ": ";
                double fundRate = fund.getRate();
                
                if (fundRate < 10) {
                    ratesLbl += 0;
                }
                
                String rates = String.valueOf(new DecimalFormat(".##").format(fund.getRate()));
                ratesLbl += rates + " - ";
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        banner.setRates(ratesLbl);
    }
    
    public void connect(String ipAddress, int portNumber) {
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
            System.out.println("Client: registry located!");
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Client: Failed to locate registry " + ex.getMessage());
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Failed to locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                stockExchange = (IStockExchange) registry.lookup(bindingName);
                System.out.println("Client: stockexchange binded by bindingName");
            } catch (RemoteException ex) {
                Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Client: Failed to bind stockexchange");
            } catch (NotBoundException ex) {
                Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Client: Failed to bind stockexchange");
            }
        }

        // Print result binding stockexchange
        if (stockExchange != null) {
            System.out.println("Client: stockexchange has been bound!");
        } else {
            System.out.println("Client: Failed to bind stockexchange");
        }
    }
}
