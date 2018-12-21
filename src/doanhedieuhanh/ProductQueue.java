package doanhedieuhanh;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProductQueue extends JPanel {

    static Semaphore semProducer;
    static Semaphore semConsumer;

    private final int size;
//    private ArrayList<Integer> list = new ArrayList<>(size);
    private ArrayList<Product> list;

    public ProductQueue(int size, Semaphore semProducer, Semaphore semConsumer) {
        super(true);
        this.size = size;
        this.list = new ArrayList<>(size);
        this.semConsumer = semConsumer;
        this.semProducer = semProducer;
//        this.setSize(100, 300);
        this.setBackground(Color.decode("#64B5F6"));
        
        
//        this.setBorder(border);
        this.setVisible(true);
    }

    public void putProductIntoQ(String color) {
        try {
            semProducer.acquire();

            if (list.size() != size) {
            	this.setLayout(new GridLayout(size, 1));
                Random ran = new Random();
                int a = ran.nextInt(5);
                Product p = new Product("");
                p.setBackground(Color.decode(color));
//                p.setPreferredSize(new Dimension(300/size, 30));
//                p.setBounds(100, 10, 10, 10*size);
//                p.setLocation(0, list.size()*50);
                list.add(p);
                loadProduct();

                System.out.println("Produce: list = " + list.size());

                Thread.sleep(1000);
            } else {
                System.out.println("Producer is disable");
            }

            semProducer.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getProductFromQ() {
        try {
            semConsumer.acquire();

            if (list.size() != 0) {
                this.remove(list.get(0));
                list.remove(0);
                System.out.println("Consume: list = " + list.size());
                Thread.sleep(1000);
            } else {
                System.out.println("list is empty. Consumer is disable");
            }

            semConsumer.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void loadProduct() {
        for (Product p : this.list) {
            this.add(p);
        }
    }
}
