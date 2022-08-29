package com.revature.yolp.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.models.User;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.InvalidRequestException;
import com.revature.yolp.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    public UserServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /* New user request from Postman */
            /* mapper obj convert JSON request and store into into a NewUserRequest.class */
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("signup")) {
                User createdUser = userService.register(request);

                resp.setStatus(200); // CREATED
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(createdUser.getId()));
            } else {
                System.out.println("NO");
            }
        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            resp.setStatus(404); // BAD REQUEST
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {git
        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            if (principal.getRole().equals("ADMIN")) {
                String username = req.getParameter("username");

                resp.setContentType("application/json");
                if (username != null) {
                    resp.getWriter().write(mapper.writeValueAsString(userService.getUserByUsername(username)));
                } else {
                    List<User> userList = userService.getAllUsers();
                    resp.getWriter().write(mapper.writeValueAsString(userList));
                }
            } else {
                resp.setStatus(403); // FORBIDDEN
            }
        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }
    }
}
