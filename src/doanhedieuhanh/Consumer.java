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
public class Consumer extends Thread{
     
    ProductQueue q;
     
    public Consumer(ProductQueue q){
        this.q=q;
//        this.start();
    }

     
    public void run(){
            q.getProductFromQ();
            q.revalidate();
    }
 
}
