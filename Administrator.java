package users;

import javax.swing.*;
import java.awt.*;

public class Administrator extends User {
    public static void Admin_View(){
        JFrame frame = new JFrame("JCF Ticket Management");
        frame.setSize(800, 750);

        // Create Menu
        JMenuBar mb = new JMenuBar();
        JMenu userMenu = new JMenu("Edit");
        JMenu userSubMenu = new JMenu("Users");
        JMenuItem create = new JMenuItem("Create User");
        JMenuItem retrieve = new JMenuItem("Retrieve User");
        JMenuItem update = new JMenuItem("Update User");
        JMenuItem delete = new JMenuItem("Delete User");
        frame.setJMenuBar(mb);
        mb.add(userMenu);
        userMenu.add(userSubMenu);
        userSubMenu.add(create);
        userSubMenu.add(retrieve);
        userSubMenu.add(update);
        userSubMenu.add(delete);

        frame.setBackground(Color.GREEN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
