package org.example;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame{
    private JPanel MainPanel;
    private JButton listBtn;
    private JButton editBtn;
    private JButton addBtn;
    private JButton deleteBtn;

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
    }

    private void openListForm() {
        this.setVisible(false);
        new ListForm("Wyświetl dane").setVisible(true);
    }
}
