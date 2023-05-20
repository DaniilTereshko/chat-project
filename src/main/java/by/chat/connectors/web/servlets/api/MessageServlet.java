package by.chat.connectors.web.servlets.api;

import by.chat.core.dto.MessageCreateDTO;
import by.chat.core.dto.MessageDTO;
import by.chat.core.dto.UserDTO;
import by.chat.services.factory.MessageServiceFactory;
import by.chat.services.api.IMessageService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {
    private static final String USER_PARAM_OBJECT = "user";
    private static final String TO_USER_PARAM_ID = "toUser";
    private static final String MESSAGE_PARAM = "message";
    private static final String ALL_MESSAGES_FOR_USER_PARAM = "messages";
    private static final String MESSAGES_DELET_PARAM = "messageId";
    private static final String REFERER_HEADER = "Referer";
    private static final String LOGIN_ERROR = "loginError";
    private IMessageService messageService;
    private IUserService userService;


    public MessageServlet() {
       this.messageService= MessageServiceFactory.getInstance();
       this.userService= UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        UserDTO user = (UserDTO) session.getAttribute(USER_PARAM_OBJECT);
        List<MessageDTO> messages = messageService.usersMessages(user.getId());
        StringBuilder builder = new StringBuilder();
        for (MessageDTO message:messages){
            builder.append(message.getInfo()+" "+message.getDate()+"<br/><br/>");
            builder.append(message.getMessage()+"<br/><br/><br/>");
        }
        writer.write(builder.toString());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String referer = req.getHeader(REFERER_HEADER);
        UserDTO user = (UserDTO) session.getAttribute(USER_PARAM_OBJECT);
        String deleteParam = req.getParameter(MESSAGES_DELET_PARAM);
        String nameParam = req.getParameter(TO_USER_PARAM_ID);
        String messageParam = req.getParameter(MESSAGE_PARAM);
        UserDTO recipient = userService.get(nameParam);
        if (referer != null) {
            int index = referer.indexOf("?");
            referer = index >= 0 ? referer.substring(0, index) : referer;
        }
        if (deleteParam != null) {
            Integer ID = Integer.parseInt(deleteParam);
            messageService.delet(ID);
        } else {
            if (recipient == null) {
                referer += "?" + LOGIN_ERROR + "=error";
            } else {
                if (messageParam.isEmpty()) {
                    throw new IllegalArgumentException("error");
                }else {
                String newMessage = changeOfTransfers(messageParam);
                messageService.save(new MessageCreateDTO(newMessage, user.getId(), recipient.getId()));
                }
            }

        }
        if (referer!=null) {
            resp.sendRedirect(referer);
        }else {
            PrintWriter writer = resp.getWriter();
            writer.write("Отправка произошла успешно!");
        }
    }

    public String changeOfTransfers(String text){
        String oldBlank = "\n";
        String newBlank = "<br/>";
        return text.replace(oldBlank, newBlank);
    }
}
