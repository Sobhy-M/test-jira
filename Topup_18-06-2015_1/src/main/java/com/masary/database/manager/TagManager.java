/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.manager;

import com.masary.database.dto.TrackDTO;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melad
 */
public class TagManager extends BaseManger {

    private static TagManager instance;

    public static TagManager getInstance() {
        if (instance == null) {
            instance = new TagManager();
        }
        return instance;
    }

    public String addTag(String custId, String tagId, String tagName, String tagAddress, String otherData) throws SQLException {

        Connection conn = getConnection();
        String sql = "insert into  tags (UUID,NAME,ADDRESS,OTHER,id_customer) values ('" + tagId + "','" + tagName + "','" + tagAddress + "','" + otherData + "'," + custId + ")";
        PreparedStatement stmt;
        stmt = conn.prepareStatement(sql);
        stmt.execute();
        conn.commit();
        closeStatement(stmt);
        closeConnection(conn);
        return "Done";
    }

    public String trackAgent(String custId, String tagId) throws SQLException {
        Connection conn = getConnection();
        String sql = "insert into  tracking (Tag_Id,id_customer) values ('" + tagId + "'," + custId + ")";
        PreparedStatement stmt;
        stmt = conn.prepareStatement(sql);
        stmt.execute();
        conn.commit();
        closeStatement(stmt);
        closeConnection(conn);
        return "Done";
    }

    public List<TrackDTO> getTrack(String customerId) {
        Connection conn = getConnection();
        String sql = "select tk.Tag_Id,to_char(tk.INSERT_TIME,'yyyy-mm-dd hh24-mi-ss') ,tg.NAME||' '||tg.ADDRESS||' '|| tg.OTHER from  tracking tk, Tags tg where tk.Tag_Id=tg.uuid and  tk.id_customer="
                + customerId + "order by tk.insert_time";
        List<TrackDTO> list = new ArrayList<TrackDTO>();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            TrackDTO track;
            while (rs.next()) {
                track = new TrackDTO(rs.getString(2), rs.getString(1), rs.getString(3));
                list.add(track);
            }
        } catch (Exception ex) {
        }
        return list;
    }
}
