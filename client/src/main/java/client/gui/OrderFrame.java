package client.gui;

import client.controller.OrderController;
import client.controller.ProductController;
import lib.dto.OrderDTO;
import lib.dto.ProductDTO;

import javax.swing.*;

public class OrderFrame extends JFrame {
    private JList list1;
    private JPanel mainPanel;

    private DefaultListModel<OrderDTO> model = new DefaultListModel<>();

    public OrderFrame(int productId) {

        refresh();
        
        
        var model = new DefaultListModel<>();

        var orders = OrderController.getInstance().findByProductId(productId);

        orders.forEach(model::addElement);

        list1.setModel(model);

        setTitle("Orders");
        setContentPane(mainPanel);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    private void refresh() {
        int productId = 0; // ????
        var orders = OrderController.getInstance().findByProductId(productId);

        model.clear();

        orders.forEach(model::addElement);
    }
}
