/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.local;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Noah Scharrenberg
 */
public class BannerController {
    private AEXBanner banner;
    private IStockExchange market;
    private Timer timer;
    
    public BannerController(AEXBanner banner) {
        this.banner = banner;
        this.market = new MockStockExchange();
        this.timer = new Timer();
        
        timer.schedule(new TimerTask() { 
            @Override
            public void run() {
                updateRates();
            }
        
        }, 0, 100);
        
    }
    
    public void stop() {
        timer.cancel();
    }
    
    public void updateRates() {
        String ratesLbl = "";
        
        for (IFunds fund : market.getRates()) {
            ratesLbl += fund.getName() + ": ";
            double fundRate = fund.getRate();
            
            if (fundRate < 10) {
                ratesLbl += 0;
            } 
            
            String rates = String.valueOf(new DecimalFormat(".##").format(fund.getRate()));
            ratesLbl += rates + " - ";
        }
        
        banner.setRates(ratesLbl);
        
    }
}
