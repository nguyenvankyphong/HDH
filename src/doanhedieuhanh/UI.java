package doanhedieuhanh;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame implements ActionListener {

	private JPanel queueArea;
	private JButton produceBtn1, produceBtn2, comsumeBtn1, setSizeQueue;
	private JTextField sizeQueueTxField;

	public static Semaphore semProducer = new Semaphore(1);
	public static Semaphore semConsumer = new Semaphore(1);
	int size = 5;

	private ProductQueue pq;

	public UI() {
		super("HDH");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

//		this.setLayout(new FlowLayout());
		this.getContentPane().setLayout(null);
		initComponents();
		this.setSize(600, 500);
		setVisible(true);
	}

	public void initComponents() {

		produceBtn1 = new JButton("Produce A");
		produceBtn1.addActionListener(this);
		produceBtn1.setBounds(100, 100, 100, 30);

		produceBtn2 = new JButton("Produce B");
		produceBtn2.addActionListener(this);
		produceBtn2.setBounds(220, 100, 100, 30);

		comsumeBtn1 = new JButton("Consume");
		comsumeBtn1.addActionListener(this);
		comsumeBtn1.setBounds(340, 100, 100, 30);

		setSizeQueue = new JButton("Set");
		setSizeQueue.addActionListener(this);
		setSizeQueue.setBounds(220, 50, 100, 30);
		
		produceBtn1.setEnabled(false);
		produceBtn2.setEnabled(false);
		comsumeBtn1.setEnabled(false);

		this.add(produceBtn1);
		this.add(produceBtn2);
		this.add(comsumeBtn1);
		this.add(setSizeQueue);

		sizeQueueTxField = new JTextField(100);
		sizeQueueTxField.setBounds(100, 50, 100, 30);
		this.add(sizeQueueTxField);

//		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setSizeQueue) {
			try {
				this.size = Integer.parseInt(sizeQueueTxField.getText());
				pq = new ProductQueue(size, semProducer, semConsumer);
				pq.setBounds(100, 180, 340, 250);
				this.add(pq);
				setSizeQueue.setEnabled(false);
				sizeQueueTxField.setEditable(false);
				produceBtn1.setEnabled(true);
				produceBtn2.setEnabled(true);
				comsumeBtn1.setEnabled(true);
			} catch (NumberFormatException en) {
				JOptionPane.showMessageDialog(this, "Please take a correct type", "Message Dialog",
						JOptionPane.WARNING_MESSAGE);
			}
			repaint();
		} 
		else if (e.getSource() == produceBtn1) {
			System.out.println("Producer has produced:");
	        Producer producer1 = new Producer(pq, "#FF1744");
	        producer1.start();
		} 
		else if (e.getSource() == produceBtn2) {
			System.out.println("Producer2");
	        Producer producer2 = new Producer(pq, "#C6FF00");
	        producer2.start();
		} 
		else if (e.getSource() == comsumeBtn1) {
			System.out.println("Consumer has consumed:");
	        Consumer consumer1 = new Consumer(pq);
	        consumer1.start();
		}

	}

}
