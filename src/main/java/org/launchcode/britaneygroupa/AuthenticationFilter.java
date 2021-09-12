package org.launchcode.britaneygroupa;

import org.launchcode.britaneygroupa.controllers.AuthenticationController;
import org.launchcode.britaneygroupa.models.User;
import org.launchcode.britaneygroupa.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> blacklist = Arrays.asList("/products");

    private static boolean isBlackListed(String path) {
        for (String pathRoot : blacklist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null || !isBlackListed(request.getRequestURI())) {
            return true;
        }
        System.out.println("Not Granted : " + request.getRequestURI());

        // The user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }

}
