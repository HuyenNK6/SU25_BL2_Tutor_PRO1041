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
public class HoaDon {
    private int id;
    private String tenNguoiNhan;
    private String sdt;
    private double tongTien;
    private String ngayTao;
    private int tinhTrang;
    
}
