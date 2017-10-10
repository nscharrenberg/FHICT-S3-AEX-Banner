/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.local;

import java.util.Timer;

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
        
        String rateLbl = new String();
        for (IFunds fund : market.getRates()) {
            rateLbl += fund.getName() + ": " + fund.getRate() + " - ";
        }
        banner.setRates(rateLbl);
        
    }
    
    public void stop() {
        timer.cancel();
    }
}
