package client.gui;

import client.controller.UserController;
import lib.dto.UserDTO;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton signInButton;
    private JPanel mainPanel;

    public LoginFrame() {

        logInButton.addActionListener(ev -> {
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

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
