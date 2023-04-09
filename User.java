package users;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    File userFile = new File("user.txt");
    protected String username;
    protected String password;
    protected String role;
    protected boolean isLoggedIn = false;
    private ArrayList<String> privileges = new ArrayList<String>();

    // Default Constructor
    public User(){
        username = "";
        password = "";
        role = "";
        privileges.add("View");
    }
    // Primary Constructor
    public User(String username, String password, String role, ArrayList<String> privileges){
        this.username = username;
        this.password = password;
        this.role = role;
        this.privileges = privileges;
    }
    // Copy Constructor
    public User(User copy){
        this.username = copy.username;
        this.password = copy.password;
        this.role = copy.role;
        this.isLoggedIn = copy.isLoggedIn;
        this.privileges = copy.privileges;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setPrivileges(ArrayList<String> privileges) {
        this.privileges = privileges;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public ArrayList<String> getPrivileges() {
        return privileges;
    }

    public void storeUser(){
        boolean userExists = false;
        try {
        if (userFile.createNewFile()){
            System.out.println("File Created!");
        } else {
            // Check if user already exists in file
            FileReader reader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split("\t");
                if (userData[0].equals(getUsername()) && userData[2].equals(getRole())) {
                    JOptionPane.showMessageDialog(null, "Username is already taken. Please use a different one", "Duplicate User Name", JOptionPane.ERROR_MESSAGE);
                    userExists = true;
                    break;
                }
            }
            reader.close();
        }
        // If the user doesn't exist, add them to the file
        if (!userExists) {
            FileWriter writer = new FileWriter(userFile, true);
            writer.write(getUsername() + "\t" + getPassword() + "\t" + getRole() + "\n");
            writer.close();
        }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean Login(String username, String password){
        FileReader reader = null;
        boolean userChecks = false;
        final boolean adminCreds = Objects.equals(username, "admin") && Objects.equals(password, "@dm1n");
        try {
            if (userFile.createNewFile()){
                System.out.println("File Created!");
            }
            reader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split("\t");
                if ((Objects.equals(userData[0], username) && Objects.equals(userData[1], password))) {
                    setRole(userData[2]);
                    userChecks = true;
                    break;
                }
            }
            reader.close();
            if (adminCreds) {
                userChecks = true;
            }
        } catch (IOException e) {
            if (adminCreds){
                return true;
            }
            throw new RuntimeException(e);
        }

        return userChecks;
        }

        public void display(){
        System.out.println("Username = "+username);
        System.out.println("Password = "+password);
    }
}
