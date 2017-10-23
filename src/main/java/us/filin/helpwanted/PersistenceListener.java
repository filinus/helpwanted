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
import java.math.BigDecimal;
import java.util.Date;

@WebListener
public class PersistenceListener implements ServletContextListener {
  
  private static EntityManagerFactory entityManagerFactory;
  
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    entityManagerFactory = Persistence.createEntityManagerFactory("the-unit");
    
    generateSampleData();
  }
  
  
  private void generateSampleData() {
    EntityManager em = createEntityManager();
    em.getTransaction().begin();
  
    final int MS_IN_DAY = 1000*60*60*24;
    final Date baseDate = new Date();
    final Project.VisibilityStatus[] visibilityStatuses = Project.VisibilityStatus.values();
  
    User bidder=null;
    for (int i = 0; i < 500; i++) {
      User user = User.builder()
        .firstName("First "+i)
        .lastName("Last "+i)
        .username("username_"+i)
        .build();
      em.persist(user);
    
      Project project = Project.builder()
        .title("Title"+i)
        .description("Some Description "+i)
        .owner(user)
        .start(new Date(baseDate.getTime() + (i-250)*MS_IN_DAY))
        .finish(new Date(baseDate.getTime() + (i-200)*MS_IN_DAY))
        .visibilityStatus(visibilityStatuses[i % visibilityStatuses.length])
        .build();
    
      em.persist(project);
    
      if ((i%2) == 0) {
        bidder = user;
      } else {
        for(int j=0; j< i%4; j++) {
          int quanity = 1+j+i%10;
          double pricePerUnit = 20-i%10+j;
          double price = quanity*pricePerUnit;
          
          BidRequest bidRequest = BidRequest.builder()
            .bidded(new Date((project.getStart().getTime() + project.getFinish().getTime()+j*100) % 2))
            .projectId(project.getId())
            .userId(bidder.getId())
            .quantity(quanity)
            .pricePerUnit(new BigDecimal(pricePerUnit))
            .price(new BigDecimal(price))
            .build();
          em.persist(bidRequest);
          project.setBidRequest(bidRequest);
        }
        em.merge(project);
      }
    }
    em.getTransaction().commit();
    
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
