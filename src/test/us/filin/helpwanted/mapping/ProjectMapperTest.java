package us.filin.helpwanted.mapping;

import org.junit.Test;
import org.mockito.Mock;
import us.filin.helpwanted.jpa.BidRequest;
import us.filin.helpwanted.jpa.Project;
import us.filin.helpwanted.jpa.User;
import us.filin.helpwanted.pojo.ProjectPOJO;

import java.util.*;

import static org.junit.Assert.*;

public class ProjectMapperTest {
  @Mock
  protected BidRequest bidRequestMock;
  
  @Mock
  protected User userMock;
  
  final protected UUID uuid = UUID.randomUUID();
  
  
  @Test
  public void testToPOJOs() throws Exception {
    final Project projectJPA = Project.builder()
      .description("description")
      .start(new Date())
      .finish(new Date())
      .owner(userMock)
      .bidRequest(bidRequestMock)
      .title("title")
      .visibilityStatus(Project.VisibilityStatus.CLOSED_MODERATOR)
      .build();
    
    projectJPA.setId(uuid.toString());
    
    final List<ProjectPOJO> projectPOJOs = ProjectMapper.INSTANCE.toPOJOs(Collections.singletonList(projectJPA));
    ProjectPOJO projectPOJO = projectPOJOs.get(0);
    assertEquals(uuid.toString(), projectJPA.getId());
    assertEquals(uuid, projectPOJO.getId());
  }
}