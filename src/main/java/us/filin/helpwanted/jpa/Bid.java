package us.filin.helpwanted.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity

public class Bid extends Persistent {
  
  @ManyToOne
  private Project project;
  
  @NotNull
  private double amount;
  
  @NotNull
  private double pricePerUnit;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private User bidder;
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "bidded", nullable = false)
  private Date dateTime;
  
  public Project getProject() {
    return project;
  }
  
  public void setProject(Project project) {
    this.project = project;
  }
  
  public double getAmount() {
    return amount;
  }
  
  public void setAmount(double amount) {
    this.amount = amount;
  }
  
  public double getPricePerUnit() {
    return pricePerUnit;
  }
  
  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }
  
  public User getBidder() {
    return bidder;
  }
  
  public Date getDateTime() {
    return dateTime;
  }
  
  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }
}
