package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.PersistenceListener;
import us.filin.helpwanted.jpa.User;

import javax.persistence.EntityManager;
import javax.ws.rs.core.SecurityContext;

public abstract class AbstractApiService {
  protected EntityManager em = PersistenceListener.createEntityManager();
  
  User user = null;
  
  protected void setupCurrentUser(SecurityContext securityContext) {
    String username = securityContext.getUserPrincipal().getName();
    
    user = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
      .setParameter("username", username)
      .getSingleResult();
  }
}
