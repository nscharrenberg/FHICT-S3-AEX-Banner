/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.local;

/**
 *
 * @author Noah Scharrenberg
 */
public class Fund implements IFunds {
    private String name;
    private double rate;
    
    public Fund(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getRate() {
        return rate;
    }
}
