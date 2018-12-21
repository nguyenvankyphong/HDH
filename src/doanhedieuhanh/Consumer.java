/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanhedieuhanh;

/**
 *
 * @author Administrator
 */
public class Consumer implements Runnable {
     
    ProductQueue q;
     
    public Consumer(ProductQueue q){
        this.q=q;
        new Thread(this, "Consumer").start(); 
    }

     
    public void run(){
    	while (true) {
            // consumer get items 
            q.getProductFromQ();
            q.getProductFromQ();
            q.getProductFromQ();
            
            try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
            
        }
            
    }
 
}
