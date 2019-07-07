package com.masary.integration.dto;

public class TopupDenominationDTO {

    private Long denominationId;
    private String subsrviceId;
    private Double denominationAmount;
    private String denominationName;
    private Double providerAmount;
    private Integer serverCode;

    public Long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(Long denominationId) {
        this.denominationId = denominationId;
    }

    public String getSubsrviceId() {
        return subsrviceId;
    }

    public void setSubsrviceId(String subsrviceId) {
        this.subsrviceId = subsrviceId;
    }

    public Double getDenominationAmount() {
        return denominationAmount;
    }

    public void setDenominationAmount(Double denominationAmount) {
        this.denominationAmount = denominationAmount;
    }

    public String getDenominationName() {
        return denominationName;
    }

    public void setDenominationName(String denominationName) {
        this.denominationName = denominationName;
    }

    public Double getProviderAmount() {
        return providerAmount;
    }

    public void setProviderAmount(Double providerAmount) {
        this.providerAmount = providerAmount;
    }

    public Integer getServerCode() {
        return serverCode;
    }

    public void setServerCode(Integer serverCode) {
        this.serverCode = serverCode;
    }

}
