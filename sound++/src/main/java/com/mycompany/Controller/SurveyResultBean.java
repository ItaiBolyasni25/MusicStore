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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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

    public List<Survey> getSurveysNotAnswered() {
        //Get email of user in session
        String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        //Get survey results
        List<SurveyResult> surveysAnswered = dao.findAll(new SurveyResult());
        //Get all surveys
        List<Survey> surveys = dao.findAll(new Survey());
        //Create empty list of surveys to return
        List<Survey> notAnswered = new ArrayList<Survey>();
        //Loop through all surveys
        for (int j = 0; j < surveys.size(); j++) {
            Integer currentSurvey = surveys.get(j).getSurvey_id();
            List<SurveyResult> currentSurveyList = new ArrayList<SurveyResult>();
            //Loop through surveys answered 
            for (int i = 0; i < surveysAnswered.size(); i++) {
                if(surveysAnswered.get(i).getSurveyId().getSurvey_id().equals(currentSurvey)){
                    currentSurveyList.add(surveysAnswered.get(i));
                }
            }
            boolean userHasAnswered = false;
            //Loop through the answers of the current survey answer
            for(int i = 0; i < currentSurveyList.size(); i++){
                if(currentSurveyList.get(i).getEmail().getEmail().equals(email)){
                    userHasAnswered = true;
                }
            }
            //Add survey that user has not answered to yet
            if(!userHasAnswered){
                notAnswered.add(surveys.get(j));
            }
        }
        return notAnswered;
    }

    public String saveSurvey() {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userObj");
        Survey survey = (Survey) dao.find(new Survey(), "survey_id = " + survey_id + "").get(0);
        SurveyResult result = new SurveyResult();
        result.setEmail(user);
        result.setAnswer(answer);
        result.setDateSubmitted(new Date());
        result.setSurveyId(survey);
        dao.write(result);
        return "index.xhtml";
    }
}
