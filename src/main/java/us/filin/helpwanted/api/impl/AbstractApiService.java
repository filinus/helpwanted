package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.ApiServiceInContext;
import us.filin.helpwanted.jpa.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

public abstract class AbstractApiService implements ApiServiceInContext {
  @Context
  protected ServletContext servletContext;

  private EntityManager em;
  
  User user = null;
  
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }
  
  protected EntityManager em() {
    if (em == null) {
      EntityManagerFactory entityManagerFactory = (EntityManagerFactory) servletContext.getAttribute("entityManagerFactory");
      em = entityManagerFactory.createEntityManager();
    }
    return em;
  }
  
  
  protected void setupCurrentUser(SecurityContext securityContext) {
    String username = securityContext.getUserPrincipal().getName();
    
    user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
      .setParameter("username", username)
      .getSingleResult();
  }
}
