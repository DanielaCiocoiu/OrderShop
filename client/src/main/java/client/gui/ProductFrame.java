package client.gui;

import client.controller.OrderController;
import client.controller.ProductController;
import lib.dto.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFrame extends JFrame {
    int randSelectat;
    private JList jProductList;
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

        jProductList.setModel(model);
        jProductList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        findAllProducts();

        displayProductsByUserId();
        addProductButton.addActionListener(e -> addProduct());
        addOrderButton.addActionListener(e -> addOrder());


/*        jProductList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                  var selected = (ProductDTO) jProductList.getSelectedValue();

                    if (selected != null && e.getClickCount() == 2) {
                        new OrderFrame(selected.getId());
                    }
                }
            });*/

        jProductList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    onMouseClickedForListOpenOrderFrame(e);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jProductList.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                try {
                    jListDeleteKeyPressed(e);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setTitle("Products");
        setContentPane(mainPanel);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
        private void onMouseClickedForListOpenOrderFrame(MouseEvent e) throws RemoteException {
            boolean isItemSelected = jProductList.getSelectedValue() != null;
            if (isItemSelected && e.getButton() == MouseEvent.BUTTON1 &&
                    e.getClickCount() == 2) {
                ProductDTO selected = (ProductDTO) jProductList.getSelectedValue();
                new OrderFrame(selected.getId());
             }
        }



    private void jListDeleteKeyPressed(java.awt.event.KeyEvent keyEvent) throws RemoteException {

        if (keyEvent.getKeyCode() == 127) {
            boolean isItemSelected = jProductList.getSelectedValue() != null;
            if (randSelectat == -1) {
                JOptionPane.showMessageDialog(this, "Please, select contact!");
            } else if (JOptionPane.showConfirmDialog(this, "You really want to delete this user?", "Please confirm!", 0, 3) == 0) {
                if (isItemSelected) {
                    ProductDTO sel = (ProductDTO) jProductList.getSelectedValue();
                    ProductController.getInstance()
                            .delete(sel);
                }
                displayProductsByUserId();
            }
        }
    }

    private void addProduct() {
        UserIdDTO userIdDTO = new UserIdDTO();
        userIdDTO.setUserName(getName());

        UserDTO userDto = new UserDTO();
        userDto.setUserId(userIdDTO);

        ProductDTO productDTO = new ProductDTO(
                fieldProductName.getText(),
                Double.parseDouble(fieldPrice.getText()),
                (Category) comboBoxCategory.getSelectedItem(),
                userDTO
        );
        ProductController.getInstance().persist(productDTO);
        displayProductsByUserId();

        findAllProducts();
        fieldProductName.setText("");
        fieldPrice.setText("");
        comboBoxCategory.getSelectedItem();
    }

public void addOrder(){
/*
    UserIdDTO userIdDTO = new UserIdDTO();
    userIdDTO.setUserName(getName());

    UserDTO userDto = new UserDTO();
    userDto.setUserId(userIdDTO);
*/
        var products = (List<ProductDTO>) jProductList.getSelectedValuesList();
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
                  //  .setUser(userDTO)
                    .build();

            OrderController.getInstance().persist(order);
            jProductList.setSelectedIndex(-1);
        }
}
    public void displayProductsByUserId() {
        var productDTOS = ProductController.getInstance().findProductsByUser(userDTO.getUserId());
        model.clear();
        productDTOS.forEach(model::addElement);
    }

    private void findAllProducts() {
        var products = ProductController.getInstance().findAll();
        model.clear();
        products.forEach(model::addElement);
    }
}
