import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Platform;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.marathon.javadriver.JavaDriver;
import net.sourceforge.marathon.javadriver.JavaProfile;
import net.sourceforge.marathon.javadriver.JavaProfile.LaunchMode;
import net.sourceforge.marathon.runtime.api.Constants;

public class MarathonSwingTest {
    
    private JavaDriver driver;
    private Process swingAppProcess;
    
    @BeforeClass
    public void setUp() {
        try {
            // Set Marathon framework to Swing
            System.setProperty(Constants.PROP_PROJECT_FRAMEWORK, Constants.FRAMEWORK_SWING);
            
            // Create Java profile for Swing application
            JavaProfile profile = new JavaProfile(LaunchMode.COMMAND_LINE);
            
            // Set the command to run our Swing test app
            profile.setCommand("java SwingTestApp");
            profile.setWorkingDirectory(System.getProperty("user.dir"));
            
            // Create desired capabilities
            DesiredCapabilities caps = new DesiredCapabilities("java", "1.5", Platform.ANY);
            
            // Create Java driver
            driver = new JavaDriver(profile, caps, caps);
            
            // Wait a bit for the application to start
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.err.println("Error setting up Marathon Swing test: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSwingApplicationLaunch() {
        try {
            // Check if the application window is present
            List<WebElement> windows = driver.findElements(By.cssSelector("window"));
            Assert.assertTrue(windows.size() > 0, "Swing application window should be present");
            
            System.out.println("Swing application launched successfully");
            
        } catch (Exception e) {
            System.err.println("Error testing Swing application launch: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSwingComponents() {
        try {
            // Test button interaction
            List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
            Assert.assertTrue(buttons.size() > 0, "Should find at least one button");
            
            // Test text field
            List<WebElement> textFields = driver.findElements(By.cssSelector("text-field"));
            Assert.assertTrue(textFields.size() > 0, "Should find at least one text field");
            
            // Test combo box
            List<WebElement> comboBoxes = driver.findElements(By.cssSelector("combo-box"));
            Assert.assertTrue(comboBoxes.size() > 0, "Should find at least one combo box");
            
            // Test check box
            List<WebElement> checkBoxes = driver.findElements(By.cssSelector("check-box"));
            Assert.assertTrue(checkBoxes.size() > 0, "Should find at least one check box");
            
            // Test radio buttons
            List<WebElement> radioButtons = driver.findElements(By.cssSelector("radio-button"));
            Assert.assertTrue(radioButtons.size() > 0, "Should find at least one radio button");
            
            // Test table
            List<WebElement> tables = driver.findElements(By.cssSelector("table"));
            Assert.assertTrue(tables.size() > 0, "Should find at least one table");
            
            System.out.println("All Swing components found successfully");
            
        } catch (Exception e) {
            System.err.println("Error testing Swing components: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSwingInteractions() {
        try {
            // Find and click the test button
            List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
            if (buttons.size() > 0) {
                buttons.get(0).click();
                System.out.println("Button clicked successfully");
            }
            
            // Find and interact with text field
            List<WebElement> textFields = driver.findElements(By.cssSelector("text-field"));
            if (textFields.size() > 0) {
                textFields.get(0).clear();
                textFields.get(0).sendKeys("Test input");
                System.out.println("Text field interaction successful");
            }
            
            // Find and interact with combo box
            List<WebElement> comboBoxes = driver.findElements(By.cssSelector("combo-box"));
            if (comboBoxes.size() > 0) {
                comboBoxes.get(0).click();
                System.out.println("Combo box interaction successful");
            }
            
            // Find and interact with check box
            List<WebElement> checkBoxes = driver.findElements(By.cssSelector("check-box"));
            if (checkBoxes.size() > 0) {
                checkBoxes.get(0).click();
                System.out.println("Check box interaction successful");
            }
            
        } catch (Exception e) {
            System.err.println("Error testing Swing interactions: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Marathon driver closed successfully");
            }
        } catch (Exception e) {
            System.err.println("Error closing Marathon driver: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Marathon Swing Test - Starting...");
        
        // Run the test
        MarathonSwingTest test = new MarathonSwingTest();
        try {
            test.setUp();
            test.testSwingApplicationLaunch();
            test.testSwingComponents();
            test.testSwingInteractions();
            test.tearDown();
            System.out.println("Marathon Swing Test - Completed successfully!");
        } catch (Exception e) {
            System.err.println("Marathon Swing Test - Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 