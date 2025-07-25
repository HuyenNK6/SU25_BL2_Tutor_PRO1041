/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.NSX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBConnect;

/**
 *
 * @author Huyen
 */
public class NSXRepository {
     private Connection con = null;

    public NSXRepository() {
        con = DBConnect.getConnection();
    }

    public List<NSX> getAll() {
        List<NSX> list = new ArrayList<>();
        String sql = """
                     SELECT TOP (1000) [Id]
                           ,[Ma]
                           ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[NSX]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NSX nsx = new NSX();
                nsx.setId(rs.getInt(1));
                nsx.setMa(rs.getString(2));
                nsx.setTen(rs.getString(3));
                list.add(nsx);
            }
        } catch (Exception e) {
        }

        return list;
    }
    public NSX getByID(int id) {
        String sql = """
                     SELECT  [Id]
                           ,[Ma]
                           ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[NSX]
                     WHERE id = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NSX nsx = new NSX();
                nsx.setId(rs.getInt(1));
                nsx.setMa(rs.getString(2));
                nsx.setTen(rs.getString(3));
                return nsx;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
