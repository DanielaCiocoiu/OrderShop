package client.gui;

import client.controller.OrderController;

import javax.swing.*;

public class OrderFrame extends JFrame {
    private JList orderList;
    private JPanel mainPanel;

    public OrderFrame(int productId) {

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

}
