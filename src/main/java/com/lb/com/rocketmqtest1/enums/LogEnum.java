package com.lb.com.rocketmqtest1.enums;

/**
 * @author liub
 * @date 2020/5/19
 */
public enum LogEnum {

  DB("db"),

  REQUEST("requestLog"),

  RETURN("returnLog"),

  ESlOG("esLog"),

  SYNC_LOG("syncLog")
  ;


  private String category;


  LogEnum(String category) {
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
