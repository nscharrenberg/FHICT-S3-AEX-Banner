/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.local;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Noah Scharrenberg
 */
public class MockStockExchange implements IStockExchange {
    private ArrayList<IFunds> funds;
    private Timer timer;
    private Random rand;
    
    public MockStockExchange() {
        
    }

    @Override
    public ArrayList<IFunds> getRates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
