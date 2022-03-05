/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filter;

import br.com.entidades.User;
import br.com.util.jpaUtil.JPAUtil;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 99039833
 */
@WebFilter(urlPatterns = {"/*"})
public class FilterAutenticacao implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        User usuarioSessao = (User) session.getAttribute("usuarioSessao");
        String url = req.getRequestURI();
        if (usuarioSessao == null && !url.toLowerCase().contains("login.xthml".toLowerCase()) && !url.contains("icon")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faces/login.xhtml");
            dispatcher.forward(request, response);
            return;
        }
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        JPAUtil.getEntityManager();
    }

    @Override
    public void destroy() {

    }

}
