package by.chat.filters;

import by.chat.core.dto.Role;
import by.chat.core.dto.UserDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/api/admin/*", "/ui/admin/*"})
public class AdminSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if((session != null) && (user != null)){
            if(user.getRole() == Role.ADMIN){
                filterChain.doFilter(servletRequest, servletResponse);
            } else{
                resp.sendRedirect(contextPath + "/ui/");
            }
        }else {
            resp.sendRedirect(contextPath + "/ui/signIn");
        }
    }
}
