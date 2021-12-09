package client.gui;

import client.controller.UserController;
import lib.dto.Category;
import lib.dto.UserDTO;
import lib.dto.UserIdDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton registerButton;
    private JPanel mainPanel;

    private final String [] category = {"GROCERY", "TEXTILE", "BEAUTY"};
    private final String [] fieldLabel = {"username", "password"};

    private List<String> userfields = new ArrayList<>();
    private JComboBox comboBoxCategory;
    private List<JLabel> fieldsLabels;
    private JLabel allFieldsLabel;


    public LoginFrame() {

        comboBoxCategory.addItem(Category.GROCERY);
        comboBoxCategory.addItem(Category.TEXTILE);
        comboBoxCategory.addItem(Category.BEAUTY);


        logInButton.addActionListener(ev -> login());
        registerButton.addActionListener(ev -> register());


        setContentPane(mainPanel);
        pack();
        setSize(700, 500);
        setLocationRelativeTo(null);
        setTitle("LoginFrame");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void register(){ addUser(); }

    private void login(){ validCredentials(); }

    private void addUserFields() {
        UserDTO userDTO = new UserDTO();
        userfields.add(userDTO.getUserId().getUserName());
        userfields.add(userDTO.getCategory().toString());
    }

    public boolean validCredentials() {
        if (!usernameField.getText().equals("")) {
            try {
                UserDTO userDto = UserController.getInstance()
                        .loginWithUsername(usernameField.getText(), new String(passwordField.getPassword()));

                JOptionPane.showMessageDialog(null, "Connected as " + usernameField.getText());
                new ProductFrame();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Wrong username or password");
                passwordField.setText("");
            }
        }
        return false;
    }

    public void addUser(){
        try {
            if (validFields()) {

                Category category = (Category) comboBoxCategory.getSelectedItem();

                UserIdDTO userIdDTO = new UserIdDTO();
                userIdDTO.setUserName(usernameField.getText());

                UserDTO userDto = new UserDTO();
                userDto.setUserId(userIdDTO);

                userDto.setPassword(new String(passwordField.getPassword()));
                userDto.setCategory(category);

                if (!UserController.getInstance().create(userDto)) {
                    JOptionPane.showMessageDialog(null, "User created");
                    resetFileds();
                }
            }
        }catch(NoSuchElementException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "User allready exists");
        }
    }


/*    private Category getCategory(){
        return radioButtons.stream()
                .filter(AbstractButton::isSelected)
                .map(e -> Category.valueOf(e.getActionCommand()))
                .findFirst()
                .orElseThrow();
    }*/


    private boolean validFields(){
        if(usernameField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Enter username");
            return false;
        }
        if(usernameField.getText().length() < 3){
            JOptionPane.showMessageDialog(null, "Username must have atleast 3 characters");
            return false;
        }
        if(String.valueOf(passwordField.getPassword()).equals("")){
            JOptionPane.showMessageDialog(null, "Enter password");
            return false;
        }
        if(comboBoxCategory.getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "Select a category");
            return false;
        }
        return true;
    }

/*    private Optional<JRadioButton> radioButtonIsSelected(){
        return radioButtons.stream()
                .filter( s -> s.isSelected())
                .findAny();

    }*/


    private void resetFileds(){
        usernameField.setText("");
        passwordField.setText("");
    }



/*    private void initAllFieldsLabels(){
        fieldsLabels = new ArrayList<>();
        for(int i = 0; i < 5; i++ ){
            allFieldsLabel = new JLabel(fieldLabel[i]);
            allFieldsLabel.setBounds(50, 70 + i*60, 140, 30);
            fieldsLabels.add(allFieldsLabel);
            mainPanel.add(allFieldsLabel);
        }
    } ------------------- */
/*
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
















        */
}
