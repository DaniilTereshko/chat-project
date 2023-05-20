package by.chat.connectors.web.servlets.ui.user;

import by.chat.core.dto.MessageDTO;
import by.chat.core.dto.UserDTO;
import by.chat.services.api.IMessageService;
import by.chat.services.api.IUserService;
import by.chat.services.factory.MessageServiceFactory;
import by.chat.services.factory.UserServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/ui/user/chats")
public class UiChatsServlet extends HttpServlet {
    private static final String USER_PARAM_OBJECT = "user";
    private static final String ALL_MESSAGES_FOR_USER_PARAM = "messages";
    private IMessageService messageService;

    public UiChatsServlet() {
        this.messageService= MessageServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute(USER_PARAM_OBJECT);
        List<MessageDTO> messages = messageService.usersMessages(user.getId());
        req.setAttribute(ALL_MESSAGES_FOR_USER_PARAM,messages);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ui/user/chats.jspx");
        dispatcher.forward(req,resp);
    }
}
