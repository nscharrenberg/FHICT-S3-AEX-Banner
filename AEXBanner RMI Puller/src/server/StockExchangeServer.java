/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Noah Scharrenberg
 */
public class StockExchangeServer {
    
    private static int portNumber = 1099;
    private static String bindingName = "stock exchange";
    private Registry registry = null;
    private StockExchange stockExchange = null;
    
    public StockExchangeServer() {
        // Create StockExchange
        try {
            stockExchange = new StockExchange();
            System.out.println("Server: Stockexchange created!");
        } catch (RemoteException ex) {
            Logger.getLogger(StockExchangeServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to stockexchange: " + ex.getMessage());
        }
        
        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port " + portNumber);
        } catch (RemoteException ex) {
            Logger.getLogger(StockExchangeServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to registry: " + ex.getMessage());
        }
        
        // Bind stock exchange using the registry
        try {
            registry.rebind(bindingName, stockExchange);
            System.out.println("Server: Stockexchange binded!");
        } catch (RemoteException ex) {
            Logger.getLogger(StockExchangeServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to bind stock exchange: " + ex.getMessage());
        }            
    }
    
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
             System.out.println("Server: Full list of IP addresses:");
             for(InetAddress allMyIp : allMyIps) {
                System.out.println("    " + allMyIp);
             }   
            }            
        } catch (UnknownHostException ex) {
            Logger.getLogger(StockExchangeServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to get IP Address of local host: " + ex.getMessage());
        }
        
        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(StockExchangeServer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to retrieve network interface list: " + ex.getMessage());
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("SERVER IS LAUNCHING...");
        printIPAddresses();
        StockExchangeServer server = new StockExchangeServer();
    }
    
}
