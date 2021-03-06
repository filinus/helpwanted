/*
 * Help Wanted API
 * HelpWanted REST API server implents a Market Place for Self-Employed.     Assume all projects can be done remotely/online. You do not need to worry about matching by location. The Buyer with the lowest bid automatically wins the bid when the deadline is reached. Lowest bid is displayed on the project page.  We have 50K registered Buyers.  On average, 100 projects are posted every day. On average, each project receives 50 bids. On the homepage, we need to show 100 most recent projects. Optionally you can support pagination. You are welcome to assume unspecified requirements to make it better for the customers.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: val@filin.us
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package us.filin.helpwanted.pojo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * ProjectPOJO
 */

public class ProjectPOJO   {
  private static final long serialVersionUID = 42L;

  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  public ProjectPOJO id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ProjectPOJO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @JsonProperty("title")
  @ApiModelProperty(example = "translate text from English to Spanish", value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProjectPOJO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @JsonProperty("description")
  @ApiModelProperty(example = "big big description", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectPOJO project = (ProjectPOJO) o;
    return Objects.equals(this.id, project.id) &&
        Objects.equals(this.title, project.title) &&
        Objects.equals(this.description, project.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectPOJO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

