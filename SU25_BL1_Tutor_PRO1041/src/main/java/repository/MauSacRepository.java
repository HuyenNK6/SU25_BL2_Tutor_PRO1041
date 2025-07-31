/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.MauSac;
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
public class MauSacRepository {
    private Connection con = null;

    public MauSacRepository() {
        con = DBConnect.getConnection();
    }

    public List<MauSac> getAll() {
        List<MauSac> list = new ArrayList<>();
        String sql = """
                     SELECT TOP (1000) [Id]
                           ,[Ma]
                           ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[MauSac]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt(1));
                mauSac.setMa(rs.getString(2));
                mauSac.setTen(rs.getString(3));
                list.add(mauSac);
            }
        } catch (Exception e) {
        }

        return list;
    }
    public MauSac getByID(int id) {
        String sql = """
                     SELECT [Id] ,[Ma] ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[MauSac]
                     WHERE id = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt(1));
                mauSac.setMa(rs.getString(2));
                mauSac.setTen(rs.getString(3));
                return mauSac;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
