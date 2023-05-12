package by.chat.servlets.ui.user;

import by.chat.core.dto.UserDTO;
import by.chat.services.api.IMessageService;
import by.chat.services.factory.MessageServiceFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(urlPatterns = "/ui/user/chats")
public class UiChatsServlet extends HttpServlet {
private final IMessageService messageService;
    private static final String USER_PARAM_OBJECT = "user";
    private static final String ALL_MESSAGES_FOR_USER_PARAM = "messages";
    public UiChatsServlet() {
        this.messageService = MessageServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ui/user/chats.jspx");
        dispatcher.forward(req,resp);
    }
}
