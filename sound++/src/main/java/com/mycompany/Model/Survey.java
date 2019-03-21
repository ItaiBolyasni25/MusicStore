package com.mycompany.Model;

import com.mycompany.Interface.EntityModel;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gabriela
 */
@Entity
@Table(name = "Survey")
public class Survey implements Serializable, EntityModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer survey_id;

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String created_by;
    private Date date_created;

    public Survey() {
    }

    public Survey(String question, String opt1, String opt2, String opt3, String opt4, String opt5, String createdby) {
        this.question = question;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
        this.option5 = opt5;
        this.created_by = createdby;
        this.date_created = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Integer getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Integer survey_id) {
        this.survey_id = survey_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public List<String> getOptions() {
        List<String> options = new ArrayList<String>();
        options.add(option1);
        options.add(option2);
        if(option3 != null && !option3.isEmpty()){
            options.add(option3);
        }
        if(option4 != null && !option4.isEmpty()){
            options.add(option4);
        }
        if(option5 != null && !option4.isEmpty()){
            options.add(option5);
        }
        return options;
    }
}
