package doanhedieuhanh;

public class Producer extends Thread{
     
    ProductQueue q;
    String color;
     
    public Producer(ProductQueue q, String color){
        this.q=q;
        this.color = color;
        
//        this.start();
    }
    public void run(){
            q.putProductIntoQ(color);
            q.revalidate();
    }
 
}

interface ProducerInterface {
    public void didProduce(boolean isProduced, Product product);
    public void isAddedToStorage(Producer producer);
}
