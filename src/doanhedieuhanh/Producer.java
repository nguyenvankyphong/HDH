package doanhedieuhanh;

public class Producer implements Runnable {

	ProductQueue q;

	public Producer(ProductQueue q) {
		this.q = q;
		new Thread(this, "Producer").start();
//        this.start();
	}

	public void run() {
		while (true) {
			 // producer put items 
			System.out.println("to producer thread");
			q.putProductIntoQ("#01579B");
			q.putProductIntoQ("#76FF03");
//			q.putProductIntoQ("#E65100");
	     	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
