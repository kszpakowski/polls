package com.kszpakowski.polls.pollservice.domain.survey;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue
    private UUID id;


    //    @NotBlank
    @Column(name = "title")
    private String title;

    public static Survey withTitle(String title) {
        var survey = new Survey();
        survey.title = title;
        return survey;
    }

    public String getId() {
        return id.toString();
    }

    public String getTitle() {
        return title;
    }


}
