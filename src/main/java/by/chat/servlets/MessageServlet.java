package by.chat.servlets;

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
import java.util.Map;


@WebServlet(urlPatterns = "/api/message")
public class MessageServlet extends HttpServlet {
    private IMessageService messageService;
    private IUserService userService;


    public MessageServlet() {
       this.messageService= MessageServiceFactory.getInstance();
       this.userService= UserServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        req.setAttribute("messages",messageService.get(user.getId()));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ui/message.jspx");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        Map<String,String[]> parametrMap = req.getParameterMap();
        String name;
        if (parametrMap.get("toUser") == null || parametrMap.get("toUser")[0] == null){
            throw new IllegalArgumentException("error");
        }
        name = parametrMap.get("toUser")[0];
        UserDTO recipient = null;
        for (UserDTO users:userService.get()){
            if (users.getLogin().equals(name)){
                recipient = users;
                break;
            }
        }
        if (recipient == null){
            throw new IllegalArgumentException("error");
        }
        String message;
        if (parametrMap.get("message") == null || parametrMap.get("message")[0] == null){
            throw new IllegalArgumentException("error");
        }
        message =parametrMap.get("message")[0];
        messageService.save(new MessageDTO(message,user.getId(),recipient.getId()));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ui/message.jspx");
        dispatcher.forward(req,resp);

    }
}
