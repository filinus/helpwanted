package us.filin.helpwanted;

import us.filin.helpwanted.jpa.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class PersistenceListener implements ServletContextListener {
  
  private static EntityManagerFactory entityManagerFactory;
  
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    entityManagerFactory = Persistence.createEntityManagerFactory("the-unit");
    
    //SOME SAMPLE DATA
    EntityManager em = createEntityManager();
    em.getTransaction().begin();
    
    final int MS_IN_DAY = 1000*60*60*24;
    final Date baseDate = new Date();
    final Project.VisibiltyStatus[] visibiltyStatuses = Project.VisibiltyStatus.values();
    
    User bidder=null;
    for (int i = 0; i < 500; i++) {
      User user = new User();
      user.setFirstName("First "+i);
      user.setLastName("Last "+i);
      user.setUsername("username_"+i);
      em.persist(user);
  
  
      Project project = new Project();
      project.setTitle("Title"+i);
      project.setDescription("Some Description "+i);
      project.setOwner(user);
      project.setStart(new Date(baseDate.getTime() + (i-250)*MS_IN_DAY));
      project.setFinish(new Date(baseDate.getTime() + (i-200)*MS_IN_DAY));
      project.setVisibilityStatus(visibiltyStatuses[i % visibiltyStatuses.length]);
  
      em.persist(project);
  
      if ((i%2) == 0) {
        bidder = user;
      } else {
        Bid bid = new Bid();
        bid.setBidded(new Date((project.getStart().getTime()+project.getFinish().getTime())%2));
        bid.setBidder(bidder);
        bid.setQuantity(1+i%10);
        bid.setPricePerUnit(20-i%10);
        bid.setPrice(bid.getPrice()*bid.getPricePerUnit());
        bid.setProject(project);
        em.persist(bid);
        project.setBid(bid);
        em.merge(project);
      }
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
