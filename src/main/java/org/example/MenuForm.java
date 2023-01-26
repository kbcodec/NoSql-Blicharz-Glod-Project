package org.example;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame{
    private JPanel MainPanel;
    private JButton listBtn;
    private JButton addBtn;
    private JButton registerBtn;
    private JButton logInButton;
    private JButton showBuildingsButton;

    public MenuForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        listBtn.addActionListener(e -> {
            openListForm();
        });

        addBtn.addActionListener(e -> accesDeniedInformation());
        registerBtn.addActionListener(e -> openRegisterForm());
        logInButton.addActionListener(e -> openLoginForm());
        showBuildingsButton.addActionListener(e -> openBuildingForm());
    }

    private void openBuildingForm() {
        this.setVisible(false);
        new ShowBuildingsForm("Zestawienie budynków", null).setVisible(true);
    }

    private void openRegisterForm() {
        this.setVisible(false);
        new RegisterForm("Rejestracja").setVisible(true);
    }

    private void openListForm() {
        this.setVisible(false);
        new ListForm("Wyświetl dane", null).setVisible(true);
    }

    private void openLoginForm() {
        this.setVisible(false);
        new LoginForm("Logowanie").setVisible(true);
    }

    private void accesDeniedInformation() {
        JOptionPane.showMessageDialog(this, "Zaloguj się aby uzyskać dostęp do tej opcji", "Dostęp nieprzyznany", JOptionPane.INFORMATION_MESSAGE);
    }
}
