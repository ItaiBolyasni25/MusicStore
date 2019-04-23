/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Model;

import com.mycompany.Interface.EntityModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriela
 */
@Entity
@Table(name = "survey_result")
@XmlRootElement
public class SurveyResult implements Serializable, EntityModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "survey_result_id")
    private Integer surveyResultId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "answer")
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_submitted")
    @Temporal(TemporalType.DATE)
    private Date dateSubmitted;
    @JoinColumn(name = "survey_id", referencedColumnName = "survey_id")
    @ManyToOne(optional = false)
    private Survey surveyId;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private User email;

    public SurveyResult() {
    }

    public SurveyResult(Integer surveyResultId) {
        this.surveyResultId = surveyResultId;
    }

    public SurveyResult(Integer surveyResultId, String answer, Date dateSubmitted) {
        this.surveyResultId = surveyResultId;
        this.answer = answer;
        this.dateSubmitted = dateSubmitted;
    }

    public Integer getSurveyResultId() {
        return surveyResultId;
    }

    public void setSurveyResultId(Integer surveyResultId) {
        this.surveyResultId = surveyResultId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Survey getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Survey surveyId) {
        this.surveyId = surveyId;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyResultId != null ? surveyResultId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyResult)) {
            return false;
        }
        SurveyResult other = (SurveyResult) object;
        if ((this.surveyResultId == null && other.surveyResultId != null) || (this.surveyResultId != null && !this.surveyResultId.equals(other.surveyResultId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.Model.SurveyResult[ surveyResultId=" + surveyResultId + " ]";
    }
    
}
