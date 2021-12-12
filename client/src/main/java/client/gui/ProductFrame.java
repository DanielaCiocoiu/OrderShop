package client.gui;

import client.controller.OrderController;
import client.controller.ProductController;
import lib.dto.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFrame extends JFrame {
    private JList list1;
    private JPanel mainPanel;
    private JTextField fieldProductName;
    private JTextField fieldPrice;
    private JButton addProductButton;
    private JButton addOrderButton;
    private JComboBox comboBoxCategory;
    private JTextField streetField;
    private JTextField telephoneField;
    private JTextField numberField;

    private UserDTO userDTO;

    private DefaultListModel<ProductDTO> model = new DefaultListModel<>();

    public ProductFrame(UserDTO userDTO) {
        this.userDTO = userDTO;

        comboBoxCategory.addItem(Category.GROCERY);
        comboBoxCategory.addItem(Category.TEXTILE);
        comboBoxCategory.addItem(Category.BEAUTY);

        list1.setModel(model);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        refresh();

        addProductButton.addActionListener(e -> addProduct());


        addOrderButton.addActionListener(e -> {
            var products = (List<ProductDTO>) list1.getSelectedValuesList();

            if (!products.isEmpty()) {
                var total = products.stream().mapToDouble(ProductDTO::getPrice).sum();
                var ids = products.stream().map(ProductDTO::getId).collect(Collectors.toSet());

                AddressDTO addressDTO = new AddressDTO(
                        streetField.getText(),
                        numberField.getText()
                );

                var order = new OrderDTO.Builder()
                        .setTotal(total)
                        .setTimestamp(Instant.now())
                        .setAddressDTO(addressDTO)
                        .setTelephones(Set.of(telephoneField.getText()))
                        .setIdProducts(ids)
                        .setUser(new UserDTO(new UserIdDTO(), " ")).build();

                OrderController.getInstance().persist(order);

                list1.setSelectedIndex(-1);
            }
        });


        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var selected = (ProductDTO) list1.getSelectedValue();

                if (selected != null && e.getClickCount() == 2) {
                    new OrderFrame(selected.getId());
                }
            }
        });


        setTitle("Products");
        setContentPane(mainPanel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct() {
        ProductDTO  productDTO = new ProductDTO(
                fieldProductName.getText(),
                Double.parseDouble(fieldPrice.getText()),
                (Category) comboBoxCategory.getSelectedItem(),
                userDTO.getUserId()

        );
       ProductController.getInstance().persist(productDTO);
        displayProducts();

        refresh();
        fieldProductName.setText("");
        fieldPrice.setText("");
        comboBoxCategory.getSelectedItem();
    }

    public void displayProducts() {
        var productDTOS = ProductController.getInstance().findByUser(userDTO.getUserId());
        model.clear();
        productDTOS.forEach(model::addElement);
    }

    private void refresh() {
        var products = ProductController.getInstance().findAll();
        model.clear();
        products.forEach(model::addElement);
    }
}
