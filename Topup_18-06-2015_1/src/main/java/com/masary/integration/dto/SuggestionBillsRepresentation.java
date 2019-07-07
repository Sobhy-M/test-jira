/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author AYA
 */
public class SuggestionBillsRepresentation {

    private double fristSuggestionbillsAmount;
    private int fristSuggestionbillsCount;

    private double secondSuggestionbillsAmount;
    private int secondSuggestionbillsCount;

    public double getFristSuggestionbillsAmount() {
        return fristSuggestionbillsAmount;
    }

    public void setFristSuggestionbillsAmount(double fristSuggestionbillsAmount) {
        this.fristSuggestionbillsAmount = fristSuggestionbillsAmount;
    }

    public int getFristSuggestionbillsCount() {
        return fristSuggestionbillsCount;
    }

    public void setFristSuggestionbillsCount(int fristSuggestionbillsCount) {
        this.fristSuggestionbillsCount = fristSuggestionbillsCount;
    }

    public double getSecondSuggestionbillsAmount() {
        return secondSuggestionbillsAmount;
    }

    public void setSecondSuggestionbillsAmount(double secondSuggestionbillsAmount) {
        this.secondSuggestionbillsAmount = secondSuggestionbillsAmount;
    }

    public int getSecondSuggestionbillsCount() {
        return secondSuggestionbillsCount;
    }

    public void setSecondSuggestionbillsCount(int secondSuggestionbillsCount) {
        this.secondSuggestionbillsCount = secondSuggestionbillsCount;
    }
}
