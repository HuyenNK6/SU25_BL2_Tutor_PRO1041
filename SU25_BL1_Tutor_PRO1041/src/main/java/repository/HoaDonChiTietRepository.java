/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.GioHangChiTiet;
import entity.HoaDon;
import entity.HoaDonChiTiet;
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
public class HoaDonChiTietRepository {

    private Connection con = null;

    public HoaDonChiTietRepository() {
        con = DBConnect.getConnection();
    }

    public List<GioHangChiTiet> getAllByIDHoaDon(int idHoaDon) {
        List<GioHangChiTiet> list = new ArrayList<>();
        String sql = """
                     SELECT dbo.HoaDonChiTiet.Id, dbo.ChiTietSP.Id AS IDCTSP, 
                     dbo.SanPham.Ten AS TenSanPham, dbo.MauSac.Ten AS MauSac, dbo.KichThuoc.Ten AS KichThuoc,
                     dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.DonGia
                     FROM     dbo.HoaDonChiTiet INNER JOIN
                                       dbo.ChiTietSP ON dbo.HoaDonChiTiet.IdChiTietSP = dbo.ChiTietSP.Id INNER JOIN
                                       dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id INNER JOIN
                                       dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.Id INNER JOIN
                                       dbo.KichThuoc ON dbo.ChiTietSP.IdKichThuoc = dbo.KichThuoc.Id
                     WHERE dbo.HoaDonChiTiet.IdHoaDon = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GioHangChiTiet ghct = new GioHangChiTiet();
                ghct.setId(rs.getInt(1));
                ghct.setIdChiTietSP(rs.getInt(2));
                ghct.setTenSanPham(rs.getString(3));
                ghct.setTenMauSac(rs.getString(4));
                ghct.setTenKichThuoc(rs.getString(5));
                ghct.setSoLuong(rs.getInt(6));
                ghct.setDonGia(rs.getDouble(7));
                list.add(ghct);
            }
        } catch (Exception e) {
        }

        return list;
    }
     public int themHoaDonChiTiet(HoaDonChiTiet hdct){
        int result=0;
        String sql ="""
                INSERT INTO [dbo].[HoaDonChiTiet]
                               ([IdHoaDon]
                               ,[IdChiTietSP]
                               ,[SoLuong]
                               ,[DonGia])
                         VALUES
                               (?,?,?,?)
                    """;
        try (PreparedStatement ps= con.prepareStatement(sql)){
            ps.setObject(1, hdct.getIdHoaDon());
            ps.setObject(2, hdct.getIdChiTietSP());
            ps.setObject(3, hdct.getSoLuong());
            ps.setObject(4, hdct.getDonGia());
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result; //boolean -> (result > 0)
    }
}
