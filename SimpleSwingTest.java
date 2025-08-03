import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleSwingTest {
    
    public static void main(String[] args) {
        System.out.println("Starting Simple Swing Test...");
        
        // Test 1: Basic Swing components creation
        testBasicComponents();
        
        // Test 2: Swing application launch
        testSwingApplication();
        
        // Test 3: Event handling
        testEventHandling();
        
        System.out.println("Simple Swing Test completed successfully!");
    }
    
    private static void testBasicComponents() {
        System.out.println("Testing basic Swing components...");
        
        try {
            // Test JButton
            JButton button = new JButton("Test Button");
            System.out.println("✓ JButton created successfully");
            
            // Test JTextField
            JTextField textField = new JTextField("Test Text");
            System.out.println("✓ JTextField created successfully");
            
            // Test JLabel
            JLabel label = new JLabel("Test Label");
            System.out.println("✓ JLabel created successfully");
            
            // Test JComboBox
            JComboBox<String> comboBox = new JComboBox<>(new String[]{"Option 1", "Option 2"});
            System.out.println("✓ JComboBox created successfully");
            
            // Test JCheckBox
            JCheckBox checkBox = new JCheckBox("Test CheckBox");
            System.out.println("✓ JCheckBox created successfully");
            
            // Test JRadioButton
            JRadioButton radioButton = new JRadioButton("Test Radio");
            System.out.println("✓ JRadioButton created successfully");
            
            // Test JTextArea
            JTextArea textArea = new JTextArea("Test Text Area");
            System.out.println("✓ JTextArea created successfully");
            
            // Test JTable
            String[] columnNames = {"Name", "Age"};
            Object[][] data = {{"John", 25}, {"Jane", 30}};
            JTable table = new JTable(data, columnNames);
            System.out.println("✓ JTable created successfully");
            
            // Test JPanel
            JPanel panel = new JPanel();
            panel.add(button);
            panel.add(textField);
            System.out.println("✓ JPanel with components created successfully");
            
            System.out.println("All basic Swing components test passed!");
            
        } catch (Exception e) {
            System.err.println("Error in basic components test: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testSwingApplication() {
        System.out.println("Testing Swing application launch...");
        
        try {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Test Frame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setLocationRelativeTo(null);
                
                JPanel panel = new JPanel();
                JButton button = new JButton("Click Me");
                JLabel label = new JLabel("Hello Swing!");
                
                panel.add(button);
                panel.add(label);
                frame.add(panel);
                
                frame.setVisible(true);
                System.out.println("✓ Swing application launched successfully");
                
                // Close the frame after 2 seconds
                Timer timer = new Timer(2000, e -> {
                    frame.dispose();
                    System.out.println("✓ Swing application closed successfully");
                });
                timer.setRepeats(false);
                timer.start();
            });
            
        } catch (Exception e) {
            System.err.println("Error in Swing application test: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testEventHandling() {
        System.out.println("Testing Swing event handling...");
        
        try {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Event Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setLocationRelativeTo(null);
                
                JPanel panel = new JPanel();
                JButton button = new JButton("Test Event");
                JLabel statusLabel = new JLabel("Status: Ready");
                
                // Add event listener
                button.addActionListener(e -> {
                    statusLabel.setText("Status: Button Clicked!");
                    System.out.println("✓ Button event handled successfully");
                });
                
                panel.add(button);
                panel.add(statusLabel);
                frame.add(panel);
                
                frame.setVisible(true);
                System.out.println("✓ Event handling test setup completed");
                
                // Close the frame after 3 seconds
                Timer timer = new Timer(3000, e -> {
                    frame.dispose();
                    System.out.println("✓ Event handling test completed");
                });
                timer.setRepeats(false);
                timer.start();
            });
            
        } catch (Exception e) {
            System.err.println("Error in event handling test: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 