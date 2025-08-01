/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.HoaDon;
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
public class HoaDonRepository {

    private Connection con = null;

    public HoaDonRepository() {
        con = DBConnect.getConnection();
    }

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = """
                     SELECT TOP (1000) [Id]
                           ,[TenNguoiNhan]
                           ,[Sdt]
                           ,[TongTien]
                           ,[NgayTao]
                           ,[TinhTrang]
                       FROM [DB_PRO1041_BanHang].[dbo].[HoaDon]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getInt(1));
                hoaDon.setTenNguoiNhan(rs.getString(2));
                hoaDon.setSdt(rs.getString(3));
                hoaDon.setTongTien(rs.getDouble(4));
                hoaDon.setNgayTao(rs.getString(5));
                hoaDon.setTinhTrang(rs.getInt(6));
                list.add(hoaDon);
            }
        } catch (Exception e) {
        }

        return list;
    }
    
    public int taoHoaDon(HoaDon hoaDon){
        int result=0;
        String sql ="""
                    INSERT INTO [dbo].[HoaDon]
                               ([TenNguoiNhan]
                               ,[Sdt]
                               ,[TongTien]
                    		   ,[NgayTao]
                    		   ,[TinhTrang])
                         VALUES
                               (?,?,?,?,?)
                    """;
        try (PreparedStatement ps= con.prepareStatement(sql)){
            ps.setObject(1, hoaDon.getTenNguoiNhan());
            ps.setObject(2, hoaDon.getSdt());
            ps.setObject(3, hoaDon.getTongTien());
            ps.setObject(4, hoaDon.getNgayTao());
            ps.setObject(5, hoaDon.getTinhTrang());
            result = ps.executeUpdate();
        } catch (Exception e) {
        }
        return result; //boolean -> (result > 0)
    }
}
