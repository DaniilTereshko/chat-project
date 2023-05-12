package by.chat.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/ui/user/chats/*")
public class MessageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String path = request.getServletPath();
        if (request.getParameter("answerLogin")!=null){
            path = "/ui/user/message";
        }
        session.setAttribute("pathApp", path);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/api/message");
        dispatcher.forward(request,response);
    }
}
