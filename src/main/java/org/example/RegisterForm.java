package org.example;

import org.example.collections.user.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterForm extends JFrame {
    private JPanel MainPanel;
    private JTextField loginTextField;
    private JPasswordField passwordPasswordField;
    private JButton registerBtn;
    private JPasswordField confirmPasswordField;
    private JTextField emailTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;

    public RegisterForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuForm("Menu").setVisible(true);
            }
        });

        registerBtn.addActionListener(e -> createUser());

    }

    void createUser() {
        String firstNameFromTf = firstNameTextField.getText();
        String lastNameFromTf = lastNameTextField.getText();
        String emailFromTf = emailTextField.getText();
        String loginFromTf = loginTextField.getText();
        char[] passwordFromTf = passwordPasswordField.getPassword();
        char[] passwordConfirmed = confirmPasswordField.getPassword();

        if (isUservalid(firstNameFromTf, lastNameFromTf, loginFromTf, String.valueOf(passwordFromTf), String.valueOf(passwordConfirmed), emailFromTf)) {

                Users checkingUser = new Users().checkUser(loginFromTf, emailFromTf);
                if (checkingUser == null) {
                    Users newUser = new Users(loginFromTf, emailFromTf, String.valueOf(passwordFromTf), firstNameFromTf, lastNameFromTf);
                    newUser.addUser();
                    JOptionPane.showMessageDialog(this,
                            "Udało się utworzyć użytkownika, teraz możesz się zalogować",
                            "Rejestracja pomyślna",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new LoginForm("Logowanie").setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Użytkownik o podanym email/loginie już istnieje, spróbuj ponownie.",
                            "Niepowodzenie",
                            JOptionPane.ERROR_MESSAGE);
                    checkingUser = null;
                }

        };
    };


    private boolean isUservalid(String firstName, String lastName, String login, String password, String confirmPassword, String email) {
        if(isFieldLongEnough(firstName, lastName, login)) {
            if(isEmailValid(email)) {
                if(isPasswordValid(password)) {
                    if(arePasswordMatches(password, confirmPassword)) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Podane hasła nie są takie same, spróbuj ponownie.",
                                "Niepowodzenie",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Hasło ma nieprawidłowy format (<8-20> znaków\n-jedna wielka litera\n-jeden znak specjalny\n-jedna cyfra\nspróbuj ponownie.",
                            "Niepowodzenie",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Adres email ma nieprawidłowy format, spróbuj ponownie.",
                        "Niepowodzenie",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Długość każdego pola musi wnosić conajmniej 5 znaków, spróbuj ponownie.",
                    "Niepowodzenie",
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }


    private boolean isEmailValid (String email) {
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


    private boolean arePasswordMatches(String password, String confirmPassword) {
        return Objects.equals(password, confirmPassword);
    }


    private boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern  pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }


    private boolean isFieldLongEnough(String firstName, String lastName, String login) {
        return firstName.length() >= 5 && lastName.length() >= 5 && login.length() >= 5;
    }
}
