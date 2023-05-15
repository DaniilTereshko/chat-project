package by.chat.servlets.ui.user;

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


@WebServlet(urlPatterns = "/ui/user/message")
public class UiMessageServlet extends HttpServlet {
    private static final String LOGIN_ERROR = "loginError";
    private static final String ANSWER_LOGIN = "answerLogin";
    private IMessageService messageService;
    private IUserService userService;
    public UiMessageServlet() {
        this.messageService= MessageServiceFactory.getInstance();
        this.userService= UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String answerId = req.getParameter(ANSWER_LOGIN);
        String loginError = req.getParameter(LOGIN_ERROR);
        String answerLogin;
        if (answerId != null){
            Integer ID = Integer.parseInt(answerId);
            if (userService.get(ID) != null) {
                answerLogin = userService.get(ID).getLogin();
                req.setAttribute(ANSWER_LOGIN,answerLogin);
            }
        }
        if (loginError!=null){
            req.setAttribute(LOGIN_ERROR,"Пользователя с таким логином не существует");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ui/user/message.jspx");
        dispatcher.forward(req,resp);

    }
}
