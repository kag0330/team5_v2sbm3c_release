package dev.mvc.member;

public enum MemberRole {
  MASTER("MASTER"),
  ADMIN("ADMIN"),
  USER("USER"),
  BUSINESS("BUSINESS"),
  DELETE("DELETE");
  
  private final String role;
  
  MemberRole(String role){
    this.role = role;
  }
  
  public String value() {
    return role;
  }
}