import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingTestApp extends JFrame {
    
    private JButton testButton;
    private JLabel statusLabel;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JCheckBox checkBox;
    private JRadioButton radioButton1, radioButton2;
    private JTextArea textArea;
    private JTable table;
    
    public SwingTestApp() {
        setTitle("Marathon Swing Test Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create top panel with controls
        JPanel topPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        
        // Add various Swing components
        testButton = new JButton("Test Button");
        testButton.addActionListener(e -> {
            statusLabel.setText("Button clicked at: " + System.currentTimeMillis());
            textArea.append("Button clicked!\n");
        });
        
        textField = new JTextField("Enter text here");
        textField.addActionListener(e -> {
            statusLabel.setText("Text entered: " + textField.getText());
            textArea.append("Text entered: " + textField.getText() + "\n");
        });
        
        comboBox = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        comboBox.addActionListener(e -> {
            statusLabel.setText("Combo selected: " + comboBox.getSelectedItem());
            textArea.append("Combo selected: " + comboBox.getSelectedItem() + "\n");
        });
        
        checkBox = new JCheckBox("Check Box");
        checkBox.addActionListener(e -> {
            statusLabel.setText("Checkbox: " + (checkBox.isSelected() ? "checked" : "unchecked"));
            textArea.append("Checkbox: " + (checkBox.isSelected() ? "checked" : "unchecked") + "\n");
        });
        
        radioButton1 = new JRadioButton("Radio 1");
        radioButton2 = new JRadioButton("Radio 2");
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        
        radioButton1.addActionListener(e -> {
            statusLabel.setText("Radio 1 selected");
            textArea.append("Radio 1 selected\n");
        });
        
        radioButton2.addActionListener(e -> {
            statusLabel.setText("Radio 2 selected");
            textArea.append("Radio 2 selected\n");
        });
        
        topPanel.add(testButton);
        topPanel.add(textField);
        topPanel.add(comboBox);
        topPanel.add(checkBox);
        topPanel.add(radioButton1);
        topPanel.add(radioButton2);
        
        // Create center panel with text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log"));
        
        // Create table
        String[] columnNames = {"Name", "Age", "City"};
        Object[][] data = {
            {"John", 25, "New York"},
            {"Jane", 30, "Los Angeles"},
            {"Bob", 35, "Chicago"}
        };
        table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Table"));
        
        // Create bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Status: Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        
        // Add panels to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(scrollPane);
        centerPanel.add(tableScrollPane);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Add window listener to log window events
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                textArea.append("Window opened\n");
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                textArea.append("Window closing\n");
            }
        });
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show the application
        SwingUtilities.invokeLater(() -> {
            SwingTestApp app = new SwingTestApp();
            app.setVisible(true);
            System.out.println("SwingTestApp started successfully");
        });
    }
} 