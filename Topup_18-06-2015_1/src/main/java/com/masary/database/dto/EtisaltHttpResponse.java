package com.masary.database.dto;

public class EtisaltHttpResponse
{
  private int code;
  private String englishMessage;
  private String arabicMessage;
  private String result;

  public String getArabicMessage()
  {
    return this.arabicMessage;
  }

  public void setArabicMessage(String arabicMessage) {
    this.arabicMessage = arabicMessage;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getEnglishMessage() {
    return this.englishMessage;
  }

  public void setEnglishMessage(String englishMessage) {
    this.englishMessage = englishMessage;
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}