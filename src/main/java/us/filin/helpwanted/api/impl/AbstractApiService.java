package us.filin.helpwanted.api.impl;

import us.filin.helpwanted.api.*;
import us.filin.helpwanted.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.UUID;

import static us.filin.helpwanted.jpa.Project.VisibilityStatus.VISIBLE;

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
  
  
  protected void setupCurrentUser(SecurityContext securityContext) throws ApiException {
    String username = securityContext.getUserPrincipal().getName();
    user = findUser(username);
    if (user == null) {
      throw new ApiException(401, "Unautorized");
    }
  }
  
  protected User findUser(String username) {
    User user = em().createQuery(
      "SELECT u " +
      "FROM User u "+
      "WHERE u.username = :username",
      User.class)
      .setParameter("username", username)
      .getSingleResult();
    return user;
  }
  
  
  protected Project getProject(UUID projectId) throws NotFoundException {
    Project project = em().createQuery(
      "SELECT p "+
        "FROM Project p " +
        "LEFT JOIN FETCH BidRequest b " +
        "LEFT JOIN FETCH User u " +
        "WHERE p.id = :project_id " +
        "AND p.visibilityStatus = " + VISIBLE.getDeclaringClass().getCanonicalName()+'.'+VISIBLE.name()
      ,
      Project.class)
      .setParameter("project_id", projectId.toString().toUpperCase())
      .getSingleResult();
    
    if (project == null) {
      throw new NotFoundException(404, "project "+projectId+" not found or is not available");
    }
    return project;
  }
  
  protected Project getNotUserProject(UUID projectId) throws NotFoundException {
    Project project = getProject(projectId);
    
    if (project.getOwner().getId().equals(user.getId())) {
      throw new NotFoundException(404, "you must not bid on own projects");
    }
    return project;
  }
  
  
}
