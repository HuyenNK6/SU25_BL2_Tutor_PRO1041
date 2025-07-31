/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.KichThuoc;
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
public class KichThuocRepository {
     private Connection con = null;

    public KichThuocRepository() {
        con = DBConnect.getConnection();
    }

    public List<KichThuoc> getAll() {
        List<KichThuoc> list = new ArrayList<>();
        String sql = """
                     SELECT TOP (1000) [Id]
                           ,[Ma]
                           ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[KichThuoc]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc kichThuoc = new KichThuoc();
                kichThuoc.setId(rs.getInt(1));
                kichThuoc.setMa(rs.getString(2));
                kichThuoc.setTen(rs.getString(3));
                list.add(kichThuoc);
            }
        } catch (Exception e) {
        }

        return list;
    }
    public KichThuoc getByID(int id) {
        String sql = """
                     SELECT  [Id] ,[Ma] ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[KichThuoc]
                     WHERE id = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc kichThuoc = new KichThuoc();
                kichThuoc.setId(rs.getInt(1));
                kichThuoc.setMa(rs.getString(2));
                kichThuoc.setTen(rs.getString(3));
                return kichThuoc;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
