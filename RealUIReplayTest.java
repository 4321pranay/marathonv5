import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RealUIReplayTest {
    
    private JFrame mainFrame;
    private JTextArea logArea;
    private JButton startRecordingButton;
    private JButton stopRecordingButton;
    private JButton replayButton;
    private JButton clearButton;
    
    private List<RecordedAction> recordedActions = new ArrayList<>();
    private boolean isRecording = false;
    private JFrame testAppFrame;
    
    // Store references to components for replay
    private Map<String, JComponent> componentMap = new HashMap<>();
    
    // Simulate Marathon's action recording
    private static class RecordedAction {
        String componentType;
        String componentName;
        String action;
        String value;
        long timestamp;
        
        public RecordedAction(String componentType, String componentName, String action, String value) {
            this.componentType = componentType;
            this.componentName = componentName;
            this.action = action;
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }
        
        @Override
        public String toString() {
            return String.format("[%s] %s.%s(%s) - %s", 
                new Date(timestamp), componentType, componentName, action, value);
        }
    }
    
    public RealUIReplayTest() {
        createMainUI();
        createTestApplication();
    }
    
    private void createMainUI() {
        mainFrame = new JFrame("Real UI Replay Test");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Control panel
        JPanel controlPanel = new JPanel();
        startRecordingButton = new JButton("Start Recording");
        stopRecordingButton = new JButton("Stop Recording");
        replayButton = new JButton("Replay Actions (Real UI)");
        clearButton = new JButton("Clear Log");
        
        stopRecordingButton.setEnabled(false);
        replayButton.setEnabled(false);
        
        startRecordingButton.addActionListener(e -> startRecording());
        stopRecordingButton.addActionListener(e -> stopRecording());
        replayButton.addActionListener(e -> replayActions());
        clearButton.addActionListener(e -> clearLog());
        
        controlPanel.add(startRecordingButton);
        controlPanel.add(stopRecordingButton);
        controlPanel.add(replayButton);
        controlPanel.add(clearButton);
        
        // Log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Real UI Replay Log"));
        
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainFrame.add(mainPanel);
    }
    
    private void createTestApplication() {
        testAppFrame = new JFrame("Test Swing Application (Real Replay)");
        testAppFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        testAppFrame.setSize(400, 300);
        testAppFrame.setLocation(100, 100);
        
        JPanel testPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        testPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create test components
        JButton button1 = new JButton("Click Me");
        JTextField textField = new JTextField("Enter text");
        JCheckBox checkBox = new JCheckBox("Check me");
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        JRadioButton radio1 = new JRadioButton("Radio 1");
        JRadioButton radio2 = new JRadioButton("Radio 2");
        
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        
        // Store components for replay
        componentMap.put("button1", button1);
        componentMap.put("textField", textField);
        componentMap.put("checkBox", checkBox);
        componentMap.put("comboBox", comboBox);
        componentMap.put("radio1", radio1);
        componentMap.put("radio2", radio2);
        
        // Add action listeners with recording
        button1.addActionListener(e -> recordAction("JButton", "button1", "click", ""));
        textField.addActionListener(e -> recordAction("JTextField", "textField", "setText", textField.getText()));
        checkBox.addActionListener(e -> recordAction("JCheckBox", "checkBox", "setSelected", String.valueOf(checkBox.isSelected())));
        comboBox.addActionListener(e -> recordAction("JComboBox", "comboBox", "setSelectedItem", comboBox.getSelectedItem().toString()));
        radio1.addActionListener(e -> recordAction("JRadioButton", "radio1", "setSelected", "true"));
        radio2.addActionListener(e -> recordAction("JRadioButton", "radio2", "setSelected", "true"));
        
        testPanel.add(button1);
        testPanel.add(textField);
        testPanel.add(checkBox);
        testPanel.add(comboBox);
        testPanel.add(radio1);
        testPanel.add(radio2);
        
        testAppFrame.add(testPanel);
    }
    
    private void startRecording() {
        isRecording = true;
        recordedActions.clear();
        startRecordingButton.setEnabled(false);
        stopRecordingButton.setEnabled(true);
        replayButton.setEnabled(false);
        
        logArea.append("=== Real UI Recording Started ===\n");
        logArea.append("Recording user interactions with Swing components...\n");
        System.out.println("Real UI recording started");
        
        // Show test application
        testAppFrame.setVisible(true);
    }
    
    private void stopRecording() {
        isRecording = false;
        startRecordingButton.setEnabled(true);
        stopRecordingButton.setEnabled(false);
        replayButton.setEnabled(true);
        
        logArea.append("=== Real UI Recording Stopped ===\n");
        logArea.append("Total actions recorded: " + recordedActions.size() + "\n");
        System.out.println("Real UI recording stopped. Actions: " + recordedActions.size());
        
        // Hide test application
        testAppFrame.setVisible(false);
    }
    
    private void recordAction(String componentType, String componentName, String action, String value) {
        if (isRecording) {
            RecordedAction recordedAction = new RecordedAction(componentType, componentName, action, value);
            recordedActions.add(recordedAction);
            logArea.append("RECORDED: " + recordedAction.toString() + "\n");
            System.out.println("Recorded: " + recordedAction.toString());
        }
    }
    
    private void replayActions() {
        if (recordedActions.isEmpty()) {
            logArea.append("No actions to replay\n");
            return;
        }
        
        logArea.append("=== Real UI Replay Started ===\n");
        System.out.println("Real UI replay started with " + recordedActions.size() + " actions");
        
        // Show test application for replay
        testAppFrame.setVisible(true);
        
        // Real UI replay with delays
        javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
            private int currentIndex = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < recordedActions.size()) {
                    RecordedAction action = recordedActions.get(currentIndex);
                    logArea.append("REPLAYING (Real UI): " + action.toString() + "\n");
                    System.out.println("Replaying (Real UI): " + action.toString());
                    
                    // Actually perform the UI action
                    performRealUIAction(action);
                    
                    currentIndex++;
                } else {
                    logArea.append("=== Real UI Replay Complete ===\n");
                    System.out.println("Real UI replay complete");
                    ((javax.swing.Timer) e.getSource()).stop();
                    testAppFrame.setVisible(false);
                }
            }
        });
        timer.start();
    }
    
    private void performRealUIAction(RecordedAction action) {
        JComponent component = componentMap.get(action.componentName);
        if (component == null) {
            logArea.append("ERROR: Component not found: " + action.componentName + "\n");
            return;
        }
        
        try {
            switch (action.componentType) {
                case "JButton":
                    if (action.action.equals("click")) {
                        // Simulate button click
                        logArea.append("  -> Actually clicking button\n");
                        ((JButton) component).doClick();
                    }
                    break;
                    
                case "JTextField":
                    if (action.action.equals("setText")) {
                        // Set text in text field
                        logArea.append("  -> Actually setting text: " + action.value + "\n");
                        ((JTextField) component).setText(action.value);
                    }
                    break;
                    
                case "JCheckBox":
                    if (action.action.equals("setSelected")) {
                        // Set checkbox selection
                        boolean selected = Boolean.parseBoolean(action.value);
                        logArea.append("  -> Actually setting checkbox: " + selected + "\n");
                        ((JCheckBox) component).setSelected(selected);
                    }
                    break;
                    
                case "JComboBox":
                    if (action.action.equals("setSelectedItem")) {
                        // Set combo box selection
                        logArea.append("  -> Actually setting combo box: " + action.value + "\n");
                        ((JComboBox) component).setSelectedItem(action.value);
                    }
                    break;
                    
                case "JRadioButton":
                    if (action.action.equals("setSelected")) {
                        // Set radio button selection
                        logArea.append("  -> Actually setting radio button: " + action.componentName + "\n");
                        ((JRadioButton) component).setSelected(true);
                    }
                    break;
                    
                default:
                    logArea.append("  -> Unknown component type: " + action.componentType + "\n");
                    break;
            }
        } catch (Exception e) {
            logArea.append("  -> ERROR during replay: " + e.getMessage() + "\n");
            System.err.println("Error during replay: " + e.getMessage());
        }
    }
    
    private void clearLog() {
        logArea.setText("");
        recordedActions.clear();
        replayButton.setEnabled(false);
    }
    
    public void show() {
        mainFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        System.out.println("Starting Real UI Replay Test...");
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            RealUIReplayTest test = new RealUIReplayTest();
            test.show();
            System.out.println("Real UI replay test application started");
        });
    }
} 