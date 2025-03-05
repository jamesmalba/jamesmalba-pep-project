package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    
    private MessageDAO messageDAO;
    private AccountService accountService;

    /**
     * no-args constructor for creating a new MessageService with a new MessageDAO and AccountService 
     * for checking valid account id.
     */
    public MessageService() {
        messageDAO = new MessageDAO();
        accountService = new AccountService();
    }   

    /**
     * Constructor for a AccountService when a messageDAO and accountService is provided.
     * @param messageDAO, accountService
     */
    public MessageService(MessageDAO messageDAO, AccountService accountService) {
        this.messageDAO = messageDAO;
        this.accountService = accountService;
    }

    /**
    * Inserts a new message using messageDAO
    * Checks if account id is valid, message text is not empty, and message text does not exceed 255 chars.
    *
    * @param message The message being added
    * @return The message object with new message id.
    */
    public Message addMessage(Message message) {
        if (!accountService.validAccountId(message.getPosted_by())) return null;
        if (message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255) return null;
        return messageDAO.insertMessage(message);
    }

    /**
    * Gets all messages using messageDAO
    *
    * @return The a list of all the messages
    */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
    * Gets a message based on the message ID.
    *
    * @param message_id The message id being looked for.
    * @return The message object from the message id.
    */
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    /**
    * Deletes a message using messageDAO.
    * Checks if there exists a message with the message id. Null if does not exist.
    *
    * @param message_id The message id being deleted.
    * @return The message object of the deleted message.
    */
    public Message deleteMessageById(int message_id) {
        if (messageDAO.getMessageById(message_id) == null) return null;
        Message deletedMessage = messageDAO.getMessageById(message_id);
        messageDAO.deleteMessageById(message_id);
        return deletedMessage;
    }   

    /**
    * Updates a message using messageDAO
    * Checks if message exists and new message not empty or greater than 255 chars.
    *
    * @param message_id, message_text The message being changed with id and new message.
    * @return The message object with updated message text.
    */
    public Message updateMessageById(int message_id, String message_text) {
        if (messageDAO.getMessageById(message_id) == null) return null;
        if (message_text == null || message_text.length() > 255) return null;
        messageDAO.updateMessageById(message_id, message_text);
        return messageDAO.getMessageById(message_id);
    }

    // 8. Get all messages by account ID
    /**
    * Gets all messages by account ID.
    *
    * @param account_id The account id used to get all messages from.
    * @return A list of all the messages based on account id. 
    */
    public List<Message> getAllMessagesByAccountId(int account_id) {
        return messageDAO.getAllMessagesByAccountId(account_id);
    }

}
