import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class RecordingTest {
    
    private JFrame testFrame;
    private JButton recordButton;
    private JButton replayButton;
    private JTextArea logArea;
    private List<String> recordedActions = new ArrayList<>();
    private boolean isRecording = false;
    
    public RecordingTest() {
        createUI();
    }
    
    private void createUI() {
        testFrame = new JFrame("Marathon Recording Test");
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(600, 400);
        testFrame.setLocationRelativeTo(null);
        
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create control panel
        JPanel controlPanel = new JPanel();
        recordButton = new JButton("Start Recording");
        replayButton = new JButton("Replay Actions");
        replayButton.setEnabled(false);
        
        recordButton.addActionListener(e -> toggleRecording());
        replayButton.addActionListener(e -> replayActions());
        
        controlPanel.add(recordButton);
        controlPanel.add(replayButton);
        
        // Create test components panel
        JPanel testPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        testPanel.setBorder(BorderFactory.createTitledBorder("Test Components"));
        
        JButton testButton1 = new JButton("Button 1");
        JButton testButton2 = new JButton("Button 2");
        JTextField testField = new JTextField("Test Input");
        JCheckBox testCheckBox = new JCheckBox("Test CheckBox");
        JComboBox<String> testCombo = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        JLabel testLabel = new JLabel("Test Label");
        
        // Add action listeners to record interactions
        testButton1.addActionListener(e -> recordAction("Button 1 clicked"));
        testButton2.addActionListener(e -> recordAction("Button 2 clicked"));
        testField.addActionListener(e -> recordAction("Text field: " + testField.getText()));
        testCheckBox.addActionListener(e -> recordAction("CheckBox: " + (testCheckBox.isSelected() ? "checked" : "unchecked")));
        testCombo.addActionListener(e -> recordAction("ComboBox: " + testCombo.getSelectedItem()));
        
        testPanel.add(testButton1);
        testPanel.add(testButton2);
        testPanel.add(testField);
        testPanel.add(testCheckBox);
        testPanel.add(testCombo);
        testPanel.add(testLabel);
        
        // Create log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Recording Log"));
        
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(testPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        testFrame.add(mainPanel);
    }
    
    private void toggleRecording() {
        if (!isRecording) {
            startRecording();
        } else {
            stopRecording();
        }
    }
    
    private void startRecording() {
        isRecording = true;
        recordedActions.clear();
        recordButton.setText("Stop Recording");
        logArea.append("=== Recording Started ===\n");
        System.out.println("Recording started");
    }
    
    private void stopRecording() {
        isRecording = false;
        recordButton.setText("Start Recording");
        replayButton.setEnabled(true);
        logArea.append("=== Recording Stopped ===\n");
        logArea.append("Recorded " + recordedActions.size() + " actions\n");
        System.out.println("Recording stopped. Total actions: " + recordedActions.size());
    }
    
    private void recordAction(String action) {
        if (isRecording) {
            String timestamp = new Date().toString();
            String recordedAction = timestamp + " - " + action;
            recordedActions.add(recordedAction);
            logArea.append(recordedAction + "\n");
            System.out.println("Recorded: " + action);
        }
    }
    
    private void replayActions() {
        if (recordedActions.isEmpty()) {
            logArea.append("No actions to replay\n");
            return;
        }
        
        logArea.append("=== Replaying Actions ===\n");
        System.out.println("Replaying " + recordedActions.size() + " actions");
        
        // Simulate replay with delays
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            private int currentIndex = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < recordedActions.size()) {
                    String action = recordedActions.get(currentIndex);
                    logArea.append("Replaying: " + action + "\n");
                    System.out.println("Replaying: " + action);
                    currentIndex++;
                } else {
                    logArea.append("=== Replay Complete ===\n");
                    System.out.println("Replay complete");
                    ((javax.swing.Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    public void show() {
        testFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        System.out.println("Starting Marathon Recording Test...");
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            RecordingTest test = new RecordingTest();
            test.show();
            System.out.println("Recording test application started");
        });
    }
} 