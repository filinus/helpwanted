package us.filin.helpwanted;

import us.filin.helpwanted.jpa.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PersistenceListener implements ServletContextListener {
  
  private static EntityManagerFactory entityManagerFactory;
  
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    entityManagerFactory = Persistence.createEntityManagerFactory("the-unit");
    
    //SOME SAMPLE DATA
    EntityManager em = createEntityManager();
    em.getTransaction().begin();
    for (int i = 0; i < 500; i++) {
      Project project = new Project();
      project.setDescription("description "+i);
      em.persist(project);
    }
    em.getTransaction().commit();
    em.close();
  }
  
  public void contextDestroyed(ServletContextEvent sce) {
    entityManagerFactory.close();
  }
  
  public static EntityManager createEntityManager() {
    if (entityManagerFactory == null) {
      throw new IllegalStateException("Context is not initialized yet.");
    }
    
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.setFlushMode(FlushModeType.AUTO);
    return entityManager;
  }
}
