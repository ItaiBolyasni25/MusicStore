package com.mycompany.Controller;

import com.mycompany.Model.Survey;
import com.mycompany.Persistence.DAO;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Gabriela
 */
@Named(value = "surveyBean")
@SessionScoped
public class SurveyBean implements Serializable {

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;

    @Inject
    private DAO dao;

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

    public String addSurvey() {
        String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        Survey survey = new Survey(question, option1, option2, option3, option4, option5, email);
        dao.write(survey);
        System.out.println(email);
        System.out.println(survey.getQuestion());
        return "survey.xhtml";
    }
    
    public List<Survey> getPastSurveys(){
        return dao.findAll(new Survey());
    }
    
    public List<Survey> getSurveysperuser(){
        String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        return dao.findWithJoins(new Survey(), "JOIN survey_result s ON t.survey_id = s.survey_id", "WHERE NOT s.user_id = " + username);

    }
}
