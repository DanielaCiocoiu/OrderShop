package client.gui;

import client.controller.UserController;
import lib.dto.Category;
import lib.dto.UserDTO;
import lib.dto.UserIdDTO;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton registerButton;
    private JPanel mainPanel;

    private List<String> userfields = new ArrayList<>();
    private JComboBox comboBoxCategory;
    private JList usersList;

    private DefaultListModel<UserDTO> model;


    public LoginFrame() {
        model = new DefaultListModel<>();
        usersList.setModel(model);

        usersList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    onMouseClickedForList(e);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        comboBoxCategory.addItem(Category.GROCERY);
        comboBoxCategory.addItem(Category.TEXTILE);
        comboBoxCategory.addItem(Category.BEAUTY);


        logInButton.addActionListener(ev -> login());
        registerButton.addActionListener(ev -> register());

        displayUsers();
        setContentPane(mainPanel);
        pack();
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("LoginFrame");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void onMouseClickedForList(MouseEvent e) throws RemoteException {
        boolean isItemSelected = usersList.getSelectedValue() != null;
        if (isItemSelected && e.getButton() == MouseEvent.BUTTON1 &&
                e.getClickCount() == 2) {
            UserDTO selected = (UserDTO) usersList.getSelectedValue();
            new ProductFrame(selected);
        }
        if (isItemSelected &&
                e.getButton() == MouseEvent.BUTTON3 &&
                e.getClickCount() == 2) {
            UserDTO selected = (UserDTO) usersList.getSelectedValue();
            boolean rez = UserController.getInstance()
                    .delete(selected);
            if (rez) { //update lista
                displayUsers();
            }
        }
    }

    private void register() {
        addUser();
    }

    private void login() {
        validCredentials();
    }

    private void addUserFields() {
        UserDTO userDTO = new UserDTO();
        userfields.add(userDTO.getUserId().getUserName());
    }

    protected void displayUsers() {
        var userDTOS = UserController.getInstance().findAll();
        model.clear();
        userDTOS.forEach(model::addElement); //sau p -> model.addElement(p)
    }

    public boolean validCredentials() {
        if (!usernameField.getText().equals("")) {
            try {
                UserDTO userDTO = UserController.getInstance()
                        .loginWithUsername(usernameField.getText(), new String(passwordField.getPassword()));

                JOptionPane.showMessageDialog(null, "Connected as " + usernameField.getText());
                displayUsers();
                new ProductFrame(userDTO);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Wrong username or password");
                passwordField.setText("");
            }
        }
        return false;
    }

    public void addUser() {
        try {
            if (validFields()) {
                UserIdDTO userIdDTO = new UserIdDTO();
                userIdDTO.setUserName(usernameField.getText());

                UserDTO userDto = new UserDTO();
                userDto.setUserId(userIdDTO);

                userDto.setPassword(new String(passwordField.getPassword()));

                if (!UserController.getInstance().create(userDto)) {
                    JOptionPane.showMessageDialog(null, "User created");
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "User allready exists");
        }
    }

    private boolean validFields() {
        if (usernameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter username");
            return false;
        }
        if (usernameField.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "Username must have atleast 3 characters");
            return false;
        }
        if (String.valueOf(passwordField.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Enter password");
            return false;
        }
        if (comboBoxCategory.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Select a category");
            return false;
        }
        return true;
    }

    private void resetFileds() {
        usernameField.setText("");
        passwordField.setText("");
    }


   /*       logInButton.addActionListener(ev -> {
            String username = textField1.getText();
            String password = new String(passwordField1.getPassword());
            UserDTO userDTO = new UserDTO(0, username, password);

            try {
                int id = UserController.getInstance().login(userDTO);

                JOptionPane.showMessageDialog(null, "Conectat ca " + id);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Username sau parola gresita");
                passwordField1.setText("");
            }
        });

        signInButton.addActionListener(ev -> {
            String username = textField1.getText();
            String password = new String(passwordField1.getPassword());
            UserDTO userDTO = new UserDTO(0, username, password);

            try {
                int id = UserController.getInstance().signin(userDTO);

                JOptionPane.showMessageDialog(null, "Intregistrat ca " + id);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Username folosit");
                passwordField1.setText("");
                textField1.setText("");
            }
        });
















        */
}
