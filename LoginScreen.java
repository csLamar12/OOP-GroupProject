package users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CountDownLatch;

public class LoginScreen {

    public static User createFrame() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a window, set the window dimensions
        JFrame frame = new JFrame("Ticket System");
        frame.setSize(400, 350);
        frame.setMaximumSize(new Dimension(400, 350));
        frame.setMinimumSize(new Dimension(400, 350));

        // WordArt Label
        JLabel wordArtLabel = new JLabel("<html><center><span style='font-size:35px;color:#00cc00;font-family:Impact;text-shadow: 15px 15px 10px #ff0000;'>J</span></center></html>");
        JLabel wordArtLabel1 = new JLabel("<html><center><span style='font-size:35px;color:#cc0000;font-family:Impact;text-shadow: 15px 15px 10px #ff0000;'>C</span></center></html>");
        JLabel wordArtLabel2 = new JLabel("<html><center><span style='font-size:35px;color:#cccc00;font-family:Impact;text-shadow: 15px 15px 10px #ff0000;'>F</span></center></html>");
        JLabel wordArtLabel3 = new JLabel("<html><center><span style='font-size:25px;color:#000000;font-family:Impact;text-shadow: 15px 15px 10px #ff0000;'><br>Ticket Management System</span></center></html>");
        wordArtLabel.setBounds(170, 0, 300, 100);
        wordArtLabel1.setBounds(190, 0, 300, 100);
        wordArtLabel2.setBounds(220, 0, 300, 100);
        wordArtLabel3.setBounds(60, 30, 300, 120);
        frame.add(wordArtLabel);
        frame.add(wordArtLabel1);
        frame.add(wordArtLabel2);
        frame.add(wordArtLabel3);

//        ImageIcon img = new ImageIcon("Neuronetix_Logo.png");
//        JLabel imgL = new JLabel(img);
//        imgL.setBounds(10, 150, 280, 280);
//        frame.add(imgL);

        // Username Label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(75, 160, 80, 30);
        frame.add(usernameLabel);

        // Password Label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(75, 190, 80, 30);
        frame.add(passwordLabel);

        // Username text field
        JTextField usernameF = new JTextField();
        usernameF.setBounds(155, 160, 150, 30);
        usernameF.requestFocusInWindow();
        frame.add(usernameF);


        // Password text field
        JPasswordField passwordF = new JPasswordField();
        passwordF.setBounds(155, 190, 150, 30);
        frame.add(passwordF);

        // Login Button
        JButton login = new JButton("Login");
        login.setBounds(170, 230, 120, 30);
        login.setBackground(Color.WHITE);
        frame.add(login);

        // Add a listener to both the password and username fields
        // Click the login button when enter is pressed in either of those fields
        passwordF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login.doClick();
                }
            }
        });

        usernameF.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login.doClick();
                }
            }
        });

        CountDownLatch latch = new CountDownLatch(1);

        User user = new User();

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user.setUsername(usernameF.getText());
                user.setPassword(new String(passwordF.getPassword()));

                if (user.Login(user.username, user.password)){
                    JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    user.isLoggedIn = true;
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    user.isLoggedIn = false;
                }
                user.display();
                latch.countDown();
            }
        });

        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
}
