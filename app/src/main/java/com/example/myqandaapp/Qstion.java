package com.example.myqandaapp;

import java.util.List;

public class Qstion {
    private String qustionText;
    private List<String> choises;
    private String correctAnswer;
    private String photoPath ;

  public String getPhotoPath() {
    return photoPath;
  }

  public void setPhotoPath(String photoPath) {
    this.photoPath = photoPath;
  }

  public String getQustionText() {
    return qustionText;
  }

  public void setQustionText(String qustionText) {
    this.qustionText = qustionText;
  }

  public List<String> getChoises() {
    return choises;
  }

  public void setChoises(List<String> choises) {
    this.choises = choises;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }
}
