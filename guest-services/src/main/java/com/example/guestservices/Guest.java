package com.example.guestservices;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
//@RequiredArgsConstructor
public class Guest {
  @Id
  @GeneratedValue
//          (strategy = GenerationType.AUTO)
  private Long id;
  private  String firstName;
  private  String lastName;

//  public Guest() {
//
//  }
//
  public  Guest(Long id, String firstName, String lastName){
    this.id=id;
    this.firstName=firstName;
    this.lastName=lastName;
  }

//  public long getId() {
//    return id;
//  }
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public void setFirstName(String firstName) {
//    this.firstName = firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }

  //  private String phoneNumber;
//  private String emailAddress;
//  private String address;
//  private String country;
//  private String state;

}

//@Entity
//@Table(name="GUEST")
//public class Guest {
//  @Id
//  @Column (name="GUEST_ID")
//  @GeneratedValue(strategy = GenerationType.AUTO)
//  private long id;
//  @Column(name="FIRST_NAME")
//  private String firstName;
//  @Column(name="LAST_NAME")
//  private String lastName;
//  @Column(name="EMAIL_ADDRESS")
//  private String emailAddress;
//  @Column(name="ADDRESS")
//  private String address;
//  @Column(name="COUNTRY")
//  private String country;
//  @Column(name="STATE")
//  private String state;
//  @Column(name="PHONE_NUMBER")
//  private String phoneNumber;
//
//  public Guest(){
//
//  }

//  public Guest(String firstName, String lastName ){
//    this.firstName=firstName;
//    this.lastName=lastName;
//
//  }
//  public long getId() {
//    return id;
//  }
//
//
//  public void setId(long id) {
//    this.id = id;
//  }
//
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public void setFirstName(String firstName) {
//    this.firstName = firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }
//
//  public String getEmailAddress() {
//    return emailAddress;
//  }
//
//  public void setEmailAddress(String emailAddress) {
//    this.emailAddress = emailAddress;
//  }
//
//  public String getAddress() {
//    return address;
//  }
//
//  public void setAddress(String address) {
//    this.address = address;
//  }
//
//  public String getCountry() {
//    return country;
//  }
//
//  public void setCountry(String country) {
//    this.country = country;
//  }
//
//  public String getState() {
//    return state;
//  }
//
//  public void setState(String state) {
//    this.state = state;
//  }
//
//  public String getPhoneNumber() {
//    return phoneNumber;
//  }
//
//  public void setPhoneNumber(String phoneNumber) {
//    this.phoneNumber = phoneNumber;
//  }
//}
