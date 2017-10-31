/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Noah Scharrenberg
 */
public interface IStockExchange extends Remote {
    ArrayList<IFunds> getRates() throws RemoteException;
}
