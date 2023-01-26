package org.example;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginForm extends JFrame {
    private JPanel MainPanel;
    private JButton registerBtn;
    private JButton logInBtn;
    private JTextField loginTextField;
    private JPasswordField passwordPasswordField;
    private Users loggedUser;

    public LoginForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuLoggedForm("Menu", loggedUser).setVisible(true);
            }
        });

        registerBtn.addActionListener(e -> openRegisterForm());
        logInBtn.addActionListener(e -> logInUser());

    }

    void openRegisterForm() {
        this.dispose();
        new RegisterForm("Rejestracja").setVisible(true);
    }

    void logInUser() {
        String loginFromTf = loginTextField.getText();
        char[] passwordFromPf = passwordPasswordField.getPassword();

        loggedUser = new Users().readUser(loginFromTf);
        if(loggedUser != null && BCrypt.verifyer().verify(passwordFromPf, loggedUser.getPassword()).verified)
        {
            JOptionPane.showMessageDialog(MainPanel, "Logowanie powiodło się", "Dostęp przyznany", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new MenuLoggedForm("Menu", loggedUser).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(MainPanel, "Nieprawidłowe dane", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
        }
    }
}
