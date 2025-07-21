/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnect;

/**
 *
 * @author Huyen
 */
public class NhanVienRepository {

    private Connection con = null;

    public NhanVienRepository() {
        con = DBConnect.getConnection();
    }

    public Boolean checkLogin(String maNV, String matKhau) {
        Boolean result = null;
        String sql = """
                     SELECT * FROM [dbo].[NhanVien]
                     WHERE Ma = ? AND MatKhau = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maNV);
            ps.setObject(2, matKhau);
            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                //thực hiện cv => true
//            }
//            result = rs.next() ? true : false;
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
