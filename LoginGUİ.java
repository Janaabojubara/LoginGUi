import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGui {

    public static void main(String[] args) {
        showWelcomeScreen();
    }

    static final String VALID_USERNAME = "Cafe";
    static final String VALID_PASSWORD = "Iau.uni0@";
    static final String DEVICE_PASSWORD = "1111";
    static final String PHONE_NUMBER = "5344317911";

    static final Color darkBrown = new Color(62, 39, 35);
    static final Color lightBrown = new Color(160, 120, 90);
    static final Font fontLarge = new Font("Arial", Font.BOLD, 36);
    static final Font fontMedium = new Font("Arial", Font.PLAIN, 28);

    static void styleInputField(JTextField field) {
        field.setFont(fontMedium);
        field.setBackground(lightBrown);
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(darkBrown, 2));
    }

    static JButton styledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(fontLarge);
        button.setBackground(lightBrown);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        return button;
    }

    static void showWelcomeScreen() {
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(darkBrown);

        JLabel welcomeLabel = new JLabel("Welcome to our Café", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 64));
        welcomeLabel.setForeground(Color.WHITE);

        JButton pressButton = styledButton("PRESS");

        panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(pressButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        pressButton.addActionListener(e -> {
            frame.dispose();
            showLoginScreen();
        });
    }

    static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 20, 20));
        panel.setBackground(new Color(78, 52, 46));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(fontMedium);
        userLabel.setForeground(Color.WHITE);

        JTextField userField = new JTextField();
        styleInputField(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(fontMedium);
        passLabel.setForeground(Color.WHITE);

        JPasswordField passField = new JPasswordField();
        styleInputField(passField);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Arial", Font.PLAIN, 22));
        showPassword.setForeground(Color.WHITE);
        showPassword.setBackground(new Color(78, 52, 46));
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('•');
            }
        });

        JButton loginButton = styledButton("Continue");

        JButton forgotButton = new JButton("Forgot Password?");
        forgotButton.setFont(new Font("Arial", Font.PLAIN, 22));
        forgotButton.setForeground(Color.WHITE);
        forgotButton.setContentAreaFilled(false);
        forgotButton.setBorderPainted(false);

        panel.add(userLabel); panel.add(userField);
        panel.add(passLabel); panel.add(passField);
        panel.add(showPassword); panel.add(new JLabel());
        panel.add(forgotButton); panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(frame, "Password must have:\n- 8+ characters\n- uppercase\n- lowercase\n- digit\n- special character", "Password Error", JOptionPane.WARNING_MESSAGE);
            } else if (username.equalsIgnoreCase(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                frame.dispose();
                showDevicePasswordScreen(false);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        forgotButton.addActionListener(e -> {
            frame.dispose();
            showPhoneInputScreen();
        });
    }

    static void showPhoneInputScreen() {
        JFrame frame = new JFrame("Phone Verification");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(93, 64, 55));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));

        JLabel label = new JLabel("Enter your phone number:", SwingConstants.CENTER);
        label.setFont(fontMedium);
        label.setForeground(Color.WHITE);

        JTextField phoneField = new JTextField();
        styleInputField(phoneField);

        JButton continueButton = styledButton("Continue");

        panel.add(label, BorderLayout.NORTH);
        panel.add(phoneField, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        continueButton.addActionListener(e -> {
            String phone = phoneField.getText().trim();
            if (phone.equals(PHONE_NUMBER)) {
                frame.dispose();
                showDevicePasswordScreen(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Phone number not recognized.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    static void showDevicePasswordScreen(boolean isFromForgot) {
        JFrame frame = new JFrame("Device Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(93, 64, 55));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));

        JLabel label = new JLabel("Enter your device password:", SwingConstants.CENTER);
        label.setFont(fontMedium);
        label.setForeground(Color.WHITE);

        JPasswordField passwordField = new JPasswordField();
        styleInputField(passwordField);

        JButton submitButton = styledButton("Submit");

        panel.add(label, BorderLayout.NORTH);
        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);

        submitButton.addActionListener(e -> {
            String entered = new String(passwordField.getPassword()).trim();
            if (entered.equals(DEVICE_PASSWORD)) {
                frame.dispose();
                showFinalScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong device password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    static void showFinalScreen() {
        JFrame frame = new JFrame("Welcome!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Welcome to our cafe", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 48));
        label.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setBackground(darkBrown);
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".[A-Z].") &&
               password.matches(".[a-z].") &&
               password.matches(".\\d.") &&
               password.matches(".[!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }
}
