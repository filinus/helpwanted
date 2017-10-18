package us.filin.helpwanted.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bids")
public class Bid extends Persistent {
  
  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "project_id", nullable=false, updatable=false)
  private Project project;
  
  @OneToOne(optional = false, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "bidder_id", nullable=false, updatable=false)
  private User bidder;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "bidded", nullable = false)
  private Date bidded;
  
  @Column(nullable = false, updatable = false)
  private double quantity;
  
  @Column(nullable = false, updatable = false)
  private double pricePerUnit;
  
  @Column(nullable = false, updatable = false)
  private double price;
  
  public Project getProject() {
    return project;
  }
  
  public void setProject(Project project) {
    this.project = project;
  }
  
  public User getBidder() {
    return bidder;
  }
  
  public void setBidder(User bidder) {
    this.bidder = bidder;
  }
  
  public Date getBidded() {
    return bidded;
  }
  
  public void setBidded(Date dateTime) {
    this.bidded = dateTime;
  }
  
  public double getQuantity() {
    return quantity;
  }
  
  public void setQuantity(double amount) {
    this.quantity = amount;
  }
  
  public double getPricePerUnit() {
    return pricePerUnit;
  }
  
  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
}
