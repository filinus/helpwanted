package us.filin.helpwanted.api;


import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.security.Principal;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
        String auth = containerRequest.getHeaderString("authorization");
        final String kindaUser = "username_1";
        
        containerRequest.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return kindaUser;
                    }
                };
            }
    
            @Override
            public boolean isUserInRole(String role) {
                return false;
            }
    
            @Override
            public boolean isSecure() {
                return true;
            }
    
            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
    }

    public void destroy() {}

    public void init(FilterConfig filterConfig) throws ServletException {}
}