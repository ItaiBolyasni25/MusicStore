/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Controller;

import com.mycompany.Model.Survey;
import com.mycompany.Model.SurveyResult;
import com.mycompany.Model.User;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gabriela
 */
@Named(value = "surveyResultBean")
@SessionScoped
public class SurveyResultBean implements Serializable {

    @Inject
    private DAO dao;
    
    private String answer;
    private String email;
    private Integer survey_id;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Integer survey_id) {
        this.survey_id = survey_id;
    }
    
    public void savesurvey(){
        User user = (User) dao.find(new User(), email);
        Survey survey = (Survey) dao.find(new Survey(), survey_id+"");
        SurveyResult result = new SurveyResult();
        result.setEmail(user);
        result.setAnswer(answer);
        result.setDateSubmitted(new Date());
        result.setSurveyId(survey);
        dao.write(result);
        
    }
}
