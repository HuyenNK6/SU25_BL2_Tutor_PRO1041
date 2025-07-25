/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.DongSP;
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
public class DongSPRepository {

    private Connection con = null;

    public DongSPRepository() {
        con = DBConnect.getConnection();
    }

    public List<DongSP> getAll() {
        List<DongSP> list = new ArrayList<>();
        String sql = """
                     SELECT TOP (1000) [Id]
                           ,[Ma]
                           ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[DongSP]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DongSP dongSP = new DongSP();
                dongSP.setId(rs.getInt(1));
                dongSP.setMa(rs.getString(2));
                dongSP.setTen(rs.getString(3));
                list.add(dongSP);
            }
        } catch (Exception e) {
        }

        return list;
    }
    public DongSP getByID(int id) {
        String sql = """
                     SELECT [Id]
                           ,[Ma]
                           ,[Ten]
                       FROM [DB_PRO1041_BanHang].[dbo].[DongSP]
                     WHERE id = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DongSP dongSP = new DongSP();
                dongSP.setId(rs.getInt(1));
                dongSP.setMa(rs.getString(2));
                dongSP.setTen(rs.getString(3));
                return dongSP;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
