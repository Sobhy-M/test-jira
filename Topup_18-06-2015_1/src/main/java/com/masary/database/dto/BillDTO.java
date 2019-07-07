/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Melad
 */
public class BillDTO
{

    private String id;
    private String amount;
    private String month;
    private String billerName;

    public BillDTO(String id, String amount, String month, String billerName)
    {
        this.id = id;
        this.amount = amount;
        this.month = month;
        this.billerName = billerName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getBillerName()
    {
        return billerName;
    }

    public void setBillerName(String billerName)
    {
        this.billerName = billerName;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }
}
