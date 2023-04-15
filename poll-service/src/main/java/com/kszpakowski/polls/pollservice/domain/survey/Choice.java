package com.kszpakowski.polls.pollservice.domain.survey;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Choice {

  @Id @GeneratedValue private UUID id;

  @Column(name = "text")
  private String text;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Question question;

  // TODO choice should have position to keep order in survey question

  public static Choice withText(Question question, String text) {
    var choice = new Choice();
    choice.question = question;
    choice.text = text;
    return choice;
  }

  public String getId() {
    return this.id.toString();
  }

  public String getText() {
    return this.text;
  }
}
