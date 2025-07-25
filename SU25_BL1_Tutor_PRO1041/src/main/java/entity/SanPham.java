/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Huyen
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SanPham {
    private int id;
    private String ma;
    private String ten;
    private int idDongSP;
    private int idNSX;
    private int trangThai;
}
