package doanhedieuhanh;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame implements ActionListener {

	private JButton start, stop, setSizeQueue;
	private JTextField sizeQueueTxField;


	int size;

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

		start = new JButton("Start");
		start.addActionListener(this);
		start.setBounds(160, 100, 100, 30);

		stop = new JButton("Stop");
		stop.addActionListener(this);
		stop.setBounds(280, 100, 100, 30);
		
		JLabel name = new JLabel("PHONG KY:   Producer - Consumer");
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setBounds(150, 20, 250, 30);
		this.add(name);
		
		
		

//		comsumeBtn1 = new JButton("Consume");
//		comsumeBtn1.addActionListener(this);
//		comsumeBtn1.setBounds(340, 100, 100, 30);

		setSizeQueue = new JButton("Set");
		setSizeQueue.addActionListener(this);
		setSizeQueue.setBounds(280, 50, 100, 30);
		
		start.setEnabled(false);
		stop.setEnabled(false);

		this.add(start);
//		this.add(stop);
		this.add(setSizeQueue);

		sizeQueueTxField = new JTextField(100);
		sizeQueueTxField.setBounds(160, 50, 100, 30);
		this.add(sizeQueueTxField);

//		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setSizeQueue) {
			try {
				this.size = Integer.parseInt(sizeQueueTxField.getText());
				pq = new ProductQueue(size);
				pq.setBounds(100, 180, 340, 250);
				this.add(pq);
				setSizeQueue.setEnabled(false);
				sizeQueueTxField.setEditable(false);
				start.setEnabled(true);
				stop.setEnabled(true);
				
				JLabel qLabel = new JLabel("Queue");
				qLabel.setHorizontalAlignment(JLabel.CENTER);
				qLabel.setBounds(100, 150, 250, 30);
				this.add(qLabel);
				
				JLabel rmlistLabel = new JLabel("  Removed List:");
				rmlistLabel.setHorizontalAlignment(JLabel.CENTER);
				rmlistLabel.setBounds(260, 150, 250, 30);
				this.add(rmlistLabel);
			} catch (NumberFormatException en) {
				JOptionPane.showMessageDialog(this, "Please take a correct type", "Message Dialog",
						JOptionPane.WARNING_MESSAGE);
			}
			repaint();
		} 
		else if (e.getSource() == start) {
			System.out.println("started");
			new Producer(pq);
			new Consumer(pq);
			
		} 
		else if (e.getSource() == stop) {

		} 


	}

}
