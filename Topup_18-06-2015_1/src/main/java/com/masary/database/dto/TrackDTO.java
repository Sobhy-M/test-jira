/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.Date;

/**
 *
 * @author Melad
 */
public class TrackDTO {

    String trackDate;
    String tagId;
    String tagData;

    public TrackDTO(String trackDate, String tagId, String tagData) {
        this.trackDate = trackDate;
        this.tagId = tagId;
        this.tagData = tagData;
    }

    public String getTagData() {
        return tagData;
    }

    public void setTagData(String tagData) {
        this.tagData = tagData;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTrackDate() {
        return trackDate;
    }

    public void setTrackDate(String trackDate) {
        this.trackDate = trackDate;
    }
}
