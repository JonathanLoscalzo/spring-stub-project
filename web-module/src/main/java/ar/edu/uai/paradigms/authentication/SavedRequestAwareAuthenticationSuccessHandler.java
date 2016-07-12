package ar.edu.uai.paradigms.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Federico on 24/03/2015.
 */
public class SavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SimpleAuthenticationProvider.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        List<GrantedAuthority> permisos = (List<GrantedAuthority>)authentication.getAuthorities();

        Cookie credenciales = new Cookie("credenciales",permisos.get(0).getAuthority());
        response.addCookie(credenciales);
        response.getWriter().write(getMenus(permisos.get(0).getAuthority()));

        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() ||
                (targetUrlParam != null &&
                        StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }

        clearAuthenticationAttributes(request);
    }

    // no estoy orgulloso de esto
    String getMenus(String authority)
    {
        String menus="";
        switch(authority){
            case "ROLE_ADMIN" :
                    menus = "productos:/products;"+
                            "proveedores:/suppliers;"+
                            "clientes:/clients;"+
                            "usuarios:/users;"+
                            "estadisticas:/statistics/show;"+
                            "alarmas:/alarms/show;"+
                            "tests:/integration_tests/show";
                    break;
            case "ROLE_EMPLOYEE" :
                    menus =  "usuarios:/users";
                    break; 
        }

        return menus;
    }

}