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


package us.filin.helpwanted.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * BidModel
 */

public class BidModel  implements Serializable {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("quantity")
  private Integer quantity = 1;

  /**
   * Order Status
   */
  public enum UnitEnum {
    HOUR("hour"),
    
    WHOLE("whole");

    private String value;

    UnitEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UnitEnum fromValue(String text) {
      for (UnitEnum b : UnitEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("unit")
  private UnitEnum unit = null;

  @JsonProperty("pricePerUnit")
  private BigDecimal pricePerUnit = null;

  public BidModel id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BidModel quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
   **/
  @JsonProperty("quantity")
  @ApiModelProperty(value = "")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BidModel unit(UnitEnum unit) {
    this.unit = unit;
    return this;
  }

  /**
   * Order Status
   * @return unit
   **/
  @JsonProperty("unit")
  @ApiModelProperty(value = "Order Status")
  public UnitEnum getUnit() {
    return unit;
  }

  public void setUnit(UnitEnum unit) {
    this.unit = unit;
  }

  public BidModel pricePerUnit(BigDecimal pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
    return this;
  }

  /**
   * Get pricePerUnit
   * @return pricePerUnit
   **/
  @JsonProperty("pricePerUnit")
  @ApiModelProperty(value = "")
  public BigDecimal getPricePerUnit() {
    return pricePerUnit;
  }

  public void setPricePerUnit(BigDecimal pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BidModel bid = (BidModel) o;
    return Objects.equals(this.id, bid.id) &&
        Objects.equals(this.quantity, bid.quantity) &&
        Objects.equals(this.unit, bid.unit) &&
        Objects.equals(this.pricePerUnit, bid.pricePerUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quantity, unit, pricePerUnit);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BidModel {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("    pricePerUnit: ").append(toIndentedString(pricePerUnit)).append("\n");
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
