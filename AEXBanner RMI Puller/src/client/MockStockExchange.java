/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import shared.Fund;
import shared.IFunds;
import shared.IStockExchange;

/**
 *
 * @author Noah Scharrenberg
 */
public class MockStockExchange implements IStockExchange {
    private ArrayList<IFunds> funds;
    private Timer timer;
    private Random rand;
    
    public MockStockExchange() {
        funds = new ArrayList<IFunds>();
        timer = new Timer();
        rand = new Random();
        
        // Hardcoded Company at Stock exchange
        funds.add(new Fund("Rabobank", 50));
        funds.add(new Fund("ING", 35));
        funds.add(new Fund("ABN Amro", 52));
        funds.add(new Fund("Phillips", 125));
        funds.add(new Fund("Fontys", 2));
        funds.add(new Fund("Bunq", 15));
        funds.add(new Fund("Samsung", 532));
        funds.add(new Fund("Comboman", 398));
        funds.add(new Fund("Red Star", 21));
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateRates();
            }
        
        }, 0, 2000);
    }

    @Override
    public ArrayList<IFunds> getRates() {
       return funds;
    }
    
    public void updateRates() {
        for (IFunds fund : funds) {
            double random = (rand.nextInt(4) - 5) + rand.nextDouble();
            Fund f = (Fund)fund;
            f.setRate(random);
        }
    }
}
