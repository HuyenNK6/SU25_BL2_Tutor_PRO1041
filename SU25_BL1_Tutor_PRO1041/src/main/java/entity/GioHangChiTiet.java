/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Huyen
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GioHangChiTiet {
    private int id;
    private int idChiTietSP;
    private String tenSanPham;
    private String tenMauSac;
    private String tenKichThuoc;
    private int soLuong;
    private double donGia;
}
