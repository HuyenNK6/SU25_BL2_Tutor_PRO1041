/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.ChiTietSP;
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
public class ChiTietSPRepository {
       private Connection con = null;

    public ChiTietSPRepository() {
        con = DBConnect.getConnection();
    }

    public List<ChiTietSP> getAll() {
        List<ChiTietSP> list = new ArrayList<>();
        String sql = """
                     SELECT dbo.ChiTietSP.*, dbo.SanPham.Ten AS TenSanPham, dbo.MauSac.Ten AS TenMauSac, dbo.KichThuoc.Ten AS TenKichThuoc
                     FROM     dbo.ChiTietSP INNER JOIN
                              dbo.KichThuoc ON dbo.ChiTietSP.IdKichThuoc = dbo.KichThuoc.Id INNER JOIN
                              dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.Id INNER JOIN
                              dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSP ctsp = new ChiTietSP();
                ctsp.setId(rs.getInt(1));
                ctsp.setIdSP(rs.getInt(2));
                ctsp.setIdMauSac(rs.getInt(3));
                ctsp.setIdKichThuoc(rs.getInt(4));
                ctsp.setNamBH(rs.getInt(5));
                ctsp.setMoTa(rs.getString(6));
                ctsp.setSoLuongTon(rs.getInt(7));
                ctsp.setGiaNhap(rs.getDouble(8));
                ctsp.setGiaBan(rs.getDouble(9));
                ctsp.setTenSanPham(rs.getString(10));
                ctsp.setTenMauSac(rs.getString(11));
                ctsp.setTenKichThuoc(rs.getString(12));
                list.add(ctsp);
            }
        } catch (Exception e) {
        }

        return list;
    }
    public List<ChiTietSP> getByIDSP(int idSP) {
        List<ChiTietSP> list = new ArrayList<>();
        String sql = """
                     SELECT dbo.ChiTietSP.*, dbo.SanPham.Ten AS TenSanPham, dbo.MauSac.Ten AS TenMauSac, dbo.KichThuoc.Ten AS TenKichThuoc
                     FROM     dbo.ChiTietSP INNER JOIN
                              dbo.KichThuoc ON dbo.ChiTietSP.IdKichThuoc = dbo.KichThuoc.Id INNER JOIN
                              dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.Id INNER JOIN
                              dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id
                     WHERE IdSP = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSP ctsp = new ChiTietSP();
                ctsp.setId(rs.getInt(1));
                ctsp.setIdSP(rs.getInt(2));
                ctsp.setIdMauSac(rs.getInt(3));
                ctsp.setIdKichThuoc(rs.getInt(4));
                ctsp.setNamBH(rs.getInt(5));
                ctsp.setMoTa(rs.getString(6));
                ctsp.setSoLuongTon(rs.getInt(7));
                ctsp.setGiaNhap(rs.getDouble(8));
                ctsp.setGiaBan(rs.getDouble(9));
                ctsp.setTenSanPham(rs.getString(10));
                ctsp.setTenMauSac(rs.getString(11));
                ctsp.setTenKichThuoc(rs.getString(12));
                list.add(ctsp);
            }
        } catch (Exception e) {
        }

        return list;
    }
    public int themChiTietSanPham(ChiTietSP chiTietSP){
        int result=0;
        String sql ="""
                   INSERT INTO [dbo].[ChiTietSP]
                                          ([IdSP]
                                          ,[IdMauSac]
                                          ,[IdKichThuoc]
                                          ,[NamBH]
                                          ,[MoTa]
                                          ,[SoLuongTon]
                                          ,[GiaNhap]
                                          ,[GiaBan])
                                    VALUES
                                          (?,?,?,?,?,?,?,?)
                    """;
        try (PreparedStatement ps= con.prepareStatement(sql)){
            ps.setObject(1, chiTietSP.getIdSP());
            ps.setObject(2, chiTietSP.getIdMauSac());
            ps.setObject(3, chiTietSP.getIdKichThuoc());
            ps.setObject(4, chiTietSP.getNamBH());
            ps.setObject(5, chiTietSP.getMoTa());
            ps.setObject(6, chiTietSP.getSoLuongTon());
            ps.setObject(7, chiTietSP.getGiaNhap());
            ps.setObject(8, chiTietSP.getGiaBan());
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result; //boolean -> (result > 0)
    }
}
