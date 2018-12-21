package doanhedieuhanh;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Product extends JLabel {
    private String name;

    public Product(String name) {
        this.name = name;
        this.setText(name);
        this.setPreferredSize(new Dimension(30, 100));
//        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setForeground(Color.BLACK);
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.black));
    }
    
}
