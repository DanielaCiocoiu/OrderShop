package client.gui;

import client.controller.OrderController;
import lib.dto.OrderDTO;

import javax.swing.*;

public class OrderFrame extends JFrame {
    private JList orderList;
    private JPanel mainPanel;

    public OrderFrame(int productId) {
        //refresh();
        
        var model = new DefaultListModel<>();

        var orders = OrderController.getInstance().findByProductId(productId);

        orders.forEach(model::addElement);

        orderList.setModel(model);



        setTitle("Orders");
        setContentPane(mainPanel);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

/*    private void refresh() {
        int productId = 0; // ????
        var orders = OrderController.getInstance().findByProductId(productId);
        model.clear();
        orders.forEach(model::addElement);
    }*/
}
