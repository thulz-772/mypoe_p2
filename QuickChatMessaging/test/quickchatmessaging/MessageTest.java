package quickchatmessaging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    @BeforeEach
    public void setUp() {
        // Clear the list before each test so tests don't interfere
        QuickChatMessaging.sentMessages.clear();
    }
    
    @Test
    public void testCheckMessageID_Valid() {
        // Test: 10 digit ID should return true
        Message msg = new Message("+27821234567", "Hello");
        msg.messageID = "1234567890";
        assertTrue(msg.checkMessageID(), "10 digit message ID should be valid");
    }
    
    @Test
    public void testCheckMessageID_Invalid() {
        // Test: 11 digit ID should return false
        Message msg = new Message("+27821234567", "Hello");
        msg.messageID = "12345678901";
        assertFalse(msg.checkMessageID(), "11 digit message ID should be invalid");
    }
    
    @Test
    public void testCheckMessageLength_Valid() {
        // Test: Message 250 chars or less should return "Message ready to send."
        Message msg = new Message("+27821234567", "This is a short message");
        String result = msg.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void testCheckMessageLength_TooLong() {
        // Test: Message over 250 chars should return error message
        String longMessage = "a".repeat(251);
        Message msg = new Message("+27821234567", longMessage);
        String result = msg.checkMessageLength();
        assertEquals("Please enter a message of less than 250 characters.", result);
    }
    
    @Test
    public void testReturnTotalMessagess() {
        // Test: Should return correct count after adding messages
        Message msg1 = new Message("+27821234567", "Test1");
        Message msg2 = new Message("+27821234567", "Test2");
        
        msg1.sentMessage(1); // Add to sentMessages
        msg2.sentMessage(1); // Add to sentMessages
        
        assertEquals(2, msg1.returnTotalMessagess(), "Should return 2 messages");
    }
    
    @Test
    public void testReturnTotalMessagess_Empty() {
        // Test: Should return 0 when no messages sent
        Message msg = new Message("+27821234567", "Test");
        assertEquals(0, msg.returnTotalMessagess(), "Should return 0 messages");
    }
}