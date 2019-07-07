package com.masary.integration.dto;


public class KafrElShiekhWaterBillsSuggetionsResponse {

    private double firstBillsAmount;
    private int firstBillsCount;

    private double secondbillsAmount;
    private int secondbillsCount;

    public double getFirstBillsAmount() {
        return firstBillsAmount;
    }

    public void setFirstBillsAmount(double firstBillsAmount) {
        this.firstBillsAmount = firstBillsAmount;
    }

    public int getFirstBillsCount() {
        return firstBillsCount;
    }

    public void setFirstBillsCount(int firstBillsCount) {
        this.firstBillsCount = firstBillsCount;
    }

    public double getSecondbillsAmount() {
        return secondbillsAmount;
    }

    public void setSecondbillsAmount(double secondbillsAmount) {
        this.secondbillsAmount = secondbillsAmount;
    }

    public int getSecondbillsCount() {
        return secondbillsCount;
    }

    public void setSecondbillsCount(int secondbillsCount) {
        this.secondbillsCount = secondbillsCount;
    }

}
