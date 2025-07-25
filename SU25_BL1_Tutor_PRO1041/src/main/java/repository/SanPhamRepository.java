/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.SanPham;
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
public class SanPhamRepository {

    private Connection con = null;

    public SanPhamRepository() {
        con = DBConnect.getConnection();
    }

    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = """
                     SELECT TOP (1000) [Id]
                           ,[Ma]
                           ,[Ten]
                           ,[IdDongSP]
                           ,[IdNSX]
                           ,[TrangThai]
                       FROM [DB_PRO1041_BanHang].[dbo].[SanPham]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setMa(rs.getString(2));
                sp.setTen(rs.getString(3));
                sp.setIdDongSP(rs.getInt(4));
                sp.setIdNSX(rs.getInt(5));
                sp.setTrangThai(rs.getInt(6));
                list.add(sp);
            }
        } catch (Exception e) {
        }

        return list;
    }

    public int themSanPham(SanPham sanPham){
        int result=0;
        String sql ="""
                    INSERT INTO [dbo].[SanPham]
                               ([Ma]
                               ,[Ten]
                               ,[IdDongSP]
                               ,[IdNSX]
                               ,[TrangThai])
                         VALUES
                               (?,?,?,?,?)
                    """;
        try (PreparedStatement ps= con.prepareStatement(sql)){
            ps.setObject(1, sanPham.getMa());
            ps.setObject(2, sanPham.getTen());
            ps.setObject(3, sanPham.getIdDongSP());
            ps.setObject(4, sanPham.getIdNSX());
            ps.setObject(5, sanPham.getTrangThai());
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result; //boolean -> (result > 0)
    }
    public int suaSanPham(SanPham sanPham){
        int result=0;
        String sql ="""
                    UPDATE [dbo].[SanPham]
                                  SET [Ma] = ?
                                     ,[Ten] = ?
                                     ,[IdDongSP] = ?
                                     ,[IdNSX] = ?
                                     ,[TrangThai] = ?
                                WHERE id = ?
                    """;
        try (PreparedStatement ps= con.prepareStatement(sql)){
            ps.setObject(1, sanPham.getMa());
            ps.setObject(2, sanPham.getTen());
            ps.setObject(3, sanPham.getIdDongSP());
            ps.setObject(4, sanPham.getIdNSX());
            ps.setObject(5, sanPham.getTrangThai());
            ps.setObject(6, sanPham.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result; //boolean -> (result > 0)
    }
}
