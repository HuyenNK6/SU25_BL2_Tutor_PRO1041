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
//@Getter
//@Setter
public class NSX {

    private int id;
    private String ma;
    private String ten;

    @Override
    public String toString() {
        return ten;
    }
}
