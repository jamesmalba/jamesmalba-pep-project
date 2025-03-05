package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountService accountService;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountService = new AccountService();
    }   

    public MessageService(MessageDAO messageDAO, AccountService accountService) {
        this.messageDAO = messageDAO;
        this.accountService = accountService;
    }

    // 3. Insert message
    public Message addMessage(Message message) {
        if (!accountService.validAccountId(message.getPosted_by())) return null;
        if (message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255) return null;
        return messageDAO.insertMessage(message);
    }

    // 4. Get all messages
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    // 5. Get message by ID
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    // 6. Delete message by ID
    public Message deleteMessageById(int message_id) {
        if (messageDAO.getMessageById(message_id) == null) return null;
        Message deletedMessage = messageDAO.getMessageById(message_id);
        messageDAO.deleteMessageById(message_id);
        return deletedMessage;
    }   

    // 7. Update message text by ID
    public Message updateMessageById(int message_id, String message_text) {
        if (messageDAO.getMessageById(message_id) == null) return null;
        if (message_text == null || message_text.length() > 255) return null;
        messageDAO.updateMessageById(message_id, message_text);
        return messageDAO.getMessageById(message_id);
    }

    // 8. Get all messages by account ID
    public List<Message> getAllMessagesByAccountId(int account_id) {
        return messageDAO.getAllMessagesByAccountId(account_id);
    }

}
