package doanhedieuhanh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ProductQueue extends JPanel {

	private JPanel showList;
	private JTextArea removedList;
	private static int size;
	static Semaphore semQueue = new Semaphore(1);
	static Semaphore semEmpty;
	static Semaphore semProduct = new Semaphore(0);

	private ArrayList<Product> list;

	public ProductQueue(int size) {
		super(true);
//		this.size = size;
		this.list = new ArrayList<>(size);
		
		this.setLayout(new BorderLayout());
		showList = new JPanel();
		showList.setBackground(Color.decode("#64B5F6"));
		showList.setLayout(new GridLayout(size, 0));
		this.add(showList, BorderLayout.CENTER);
		
		semEmpty = new Semaphore(size);
		
		removedList = new JTextArea(8, 9);
		removedList.setEditable(false);
		this.add(new JScrollPane(removedList), BorderLayout.EAST);
//		removedList.append("  Consumed List:\n");

//        this.setBorder(border);
		this.setVisible(true);
	}

	public void putProductIntoQ(String color) {
		try {
			semEmpty.acquire();
			semQueue.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Random ran = new Random();
		int a = ran.nextInt(5);
		Product p = new Product("" + a);
		p.setHorizontalAlignment(JLabel.CENTER);
		p.setForeground(Color.white);
		p.setBackground(Color.decode(color));
//                p.setPreferredSize(new Dimension(300/size, 30));
//                p.setBounds(100, 10, 10, 10*size);
//                p.setLocation(0, list.size()*50);
		this.list.add(p);
		System.out.println("add already");
		System.out.println("\nlist: " + list);
		loadProduct();
		revalidate();

		System.out.println("Produce: list = " + list.size());

		semQueue.release();
		semProduct.release();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

	public void getProductFromQ() {
		try {
			semProduct.acquire();
			semQueue.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		showList.remove(list.get(0));
		removedList.insert("               "+list.get(0).getText() + "\n", 0);
		list.remove(0);
		revalidate();
		System.out.println("Consume: list = " + list.size());

		semQueue.release();
		semEmpty.release();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

	public void loadProduct() {
		for (Product p : this.list) {
			showList.add(p);
		}
	}
}
