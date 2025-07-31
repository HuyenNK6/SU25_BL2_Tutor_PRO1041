/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import entity.ChiTietSP;
import entity.DongSP;
import entity.KichThuoc;
import entity.MauSac;
import entity.NSX;
import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import repository.ChiTietSPRepository;
import repository.DongSPRepository;
import repository.KichThuocRepository;
import repository.MauSacRepository;
import repository.NSXRepository;
import repository.SanPhamRepository;

/**
 *
 * @author Huyen
 */
public class SanPhamJPanel extends javax.swing.JPanel {
//TAB SẢN PHẨM

    List<SanPham> lstSanPhams = new ArrayList<>();
    SanPhamRepository sanPhamRepo = new SanPhamRepository();
    List<DongSP> lstDongSPs = new ArrayList<>();
    DongSPRepository dongSPRepo = new DongSPRepository();
    List<NSX> lstNSXs = new ArrayList<>();
    NSXRepository nsxRepo = new NSXRepository();
    //TAB CHI TIẾT SẢN PHẨM
    List<ChiTietSP> lstChiTietSPs = new ArrayList<>();
    ChiTietSPRepository chiTietRepo = new ChiTietSPRepository();
    List<MauSac> lstMauSacs = new ArrayList<>();
    MauSacRepository mauSacRepo = new MauSacRepository();
    List<KichThuoc> lstKichThuocs = new ArrayList<>();
    KichThuocRepository kichThuocRepo = new KichThuocRepository();

    /**
     * Creates new form SanPhamJPanel
     */
    public SanPhamJPanel() {
        initComponents();
        fillToTableSanPham();
        fillToComboBoxSanPham();
        fillToComboBoxChiTietSanPham();
        fillToTableChiTietSanPham();
    }
//TAB SẢN PHẨM

    private void fillToTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblSanPhams.getModel();
        model.setRowCount(0);
        lstSanPhams = sanPhamRepo.getAll();
        for (SanPham sp : lstSanPhams) {
            Object[] rowData = {
                sp.getId(),
                sp.getMa(),
                sp.getTen(),
                sp.getIdDongSP(),
                sp.getIdNSX(),
                sp.getTrangThai()
            };
            model.addRow(rowData);
        }
    }

    private void fillToComboBoxSanPham() {
        DefaultComboBoxModel modelDongSP = (DefaultComboBoxModel) cboDongSP.getModel();
        DefaultComboBoxModel modelNSX = (DefaultComboBoxModel) cboNSX.getModel();
        modelDongSP.removeAllElements();
        modelNSX.removeAllElements();
        lstDongSPs = dongSPRepo.getAll();
        lstNSXs = nsxRepo.getAll();
        for (DongSP dsp : lstDongSPs) {
            modelDongSP.addElement(dsp);
        }
        for (NSX nsx : lstNSXs) {
            modelNSX.addElement(nsx);
        }

    }

    private void setFormSanPham(int indexRow) {
        SanPham sp = lstSanPhams.get(indexRow);
        txtId.setText(String.valueOf(sp.getId()));
        txtMa.setText(sp.getMa());
        txtTen.setText(sp.getTen());
        //radio button
        rdoDangHD.setSelected(sp.getTrangThai() == 1);
        rdoNgungHD.setSelected(sp.getTrangThai() == 0);
        //combobox
        DongSP dsp = dongSPRepo.getByID(sp.getIdDongSP());
        cboDongSP.setSelectedItem(dsp);
        NSX nsx = nsxRepo.getByID(sp.getIdNSX());
        cboNSX.setSelectedItem(nsx);
    }

    private SanPham getFormSanPham() {
        SanPham sp = new SanPham();
        sp.setId(Integer.parseInt(txtId.getText()));
        sp.setMa(txtMa.getText());
        sp.setTen(txtTen.getText());
        DongSP dsp = (DongSP) cboDongSP.getSelectedItem();
        sp.setIdDongSP(dsp.getId());
        NSX nsx = (NSX) cboNSX.getSelectedItem();
        sp.setIdNSX(nsx.getId());
        sp.setTrangThai(rdoDangHD.isSelected() ? 1 : 0);
        return sp;
    }
    //TAB CHI TIẾT SẢN PHẨM

    private void fillToComboBoxChiTietSanPham() {
        DefaultComboBoxModel modelSP = (DefaultComboBoxModel) cboSanPham.getModel();
        DefaultComboBoxModel modelMauSac = (DefaultComboBoxModel) cboMauSac.getModel();
        DefaultComboBoxModel modelKichThuoc = (DefaultComboBoxModel) cboKichThuoc.getModel();
        modelSP.removeAllElements();
        modelMauSac.removeAllElements();
        modelKichThuoc.removeAllElements();
        lstSanPhams = sanPhamRepo.getAll();
        lstMauSacs = mauSacRepo.getAll();
        lstKichThuocs = kichThuocRepo.getAll();
        for (SanPham sp : lstSanPhams) {
            modelSP.addElement(sp);
        }
        for (MauSac ms : lstMauSacs) {
            modelMauSac.addElement(ms);
        }
        for (KichThuoc kt : lstKichThuocs) {
            modelKichThuoc.addElement(kt);
        }

    }

    private void fillToTableChiTietSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblChiTietSPs.getModel();
        model.setRowCount(0);
        SanPham sp= (SanPham) cboSanPham.getSelectedItem();
        lstChiTietSPs = chiTietRepo.getByIDSP(sp.getId());
        for (ChiTietSP ctsp : lstChiTietSPs) {
            Object[] rowData = {
                ctsp.getId(),
                ctsp.getTenSanPham(),
                ctsp.getTenMauSac(),
                ctsp.getTenKichThuoc(),
                ctsp.getNamBH(),
                ctsp.getMoTa(),
                ctsp.getSoLuongTon(),
                ctsp.getGiaNhap(),
                ctsp.getGiaBan()
            };
            model.addRow(rowData);
        }
    }

     private void setFormChiTietSanPham(int indexRow){
         ChiTietSP chiTietSP= lstChiTietSPs.get(indexRow);
         txtIDSPCT.setText(String.valueOf(chiTietSP.getId()));
         //Sản phẩm bỏ qua ko cần set
         txtNamBH.setText(String.valueOf(chiTietSP.getNamBH()));
         txtMoTa.setText(chiTietSP.getMoTa());
         txtSoLuong.setText(String.valueOf(chiTietSP.getSoLuongTon()));
         txtGiaNhap.setText(String.valueOf(chiTietSP.getGiaNhap()));
         txtGiaBan.setText(String.valueOf(chiTietSP.getGiaBan()));
         //combobox
         MauSac ms= mauSacRepo.getByID(chiTietSP.getIdMauSac());
         cboMauSac.setSelectedItem(ms);
         KichThuoc kt= kichThuocRepo.getByID(chiTietSP.getIdKichThuoc());
         cboKichThuoc.setSelectedItem(kt);
     }
      private ChiTietSP getFormChiTietSanPham(){
          ChiTietSP chiTietSP= new ChiTietSP();
          chiTietSP.setId(Integer.parseInt(txtIDSPCT.getText()));
          //combobox
          SanPham sp = (SanPham) cboSanPham.getSelectedItem();
          chiTietSP.setIdSP(sp.getId());
          MauSac ms = (MauSac) cboMauSac.getSelectedItem();
          chiTietSP.setIdMauSac(ms.getId());
          KichThuoc kt = (KichThuoc) cboKichThuoc.getSelectedItem();
          chiTietSP.setIdKichThuoc(kt.getId());
          chiTietSP.setNamBH(Integer.parseInt(txtNamBH.getText()));
          chiTietSP.setMoTa(txtMoTa.getText());
          chiTietSP.setSoLuongTon(Integer.parseInt(txtSoLuong.getText()));
          chiTietSP.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
          chiTietSP.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
          
          return chiTietSP;
      }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tabSanPham = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhams = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        cboDongSP = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboNSX = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdoDangHD = new javax.swing.JRadioButton();
        rdoNgungHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietSPs = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtIDSPCT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        cboKichThuoc = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtNamBH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        btnThemChiTietSP = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        tblSanPhams.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã", "Tên", "Dòng SP", "NSX", "Trạng thái", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSanPhams.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhams);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel2.setText("Mã");

        jLabel3.setText("Tên");

        cboDongSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Dòng SP");

        jLabel5.setText("NSX");

        cboNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("ID");

        jLabel7.setText("Trạng thái");

        buttonGroup1.add(rdoDangHD);
        rdoDangHD.setSelected(true);
        rdoDangHD.setText("Đang hoạt động");

        buttonGroup1.add(rdoNgungHD);
        rdoNgungHD.setText("Ngừng hoạt động");
        rdoNgungHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgungHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTen)
                    .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboDongSP, 0, 182, Short.MAX_VALUE)
                            .addComponent(cboNSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNgungHD)
                            .addComponent(rdoDangHD, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoDangHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNgungHD))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnThem)
                .addGap(47, 47, 47)
                .addComponent(btnSua)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabSanPham.addTab("Sản phẩm", jPanel1);

        tblChiTietSPs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID SPCT", "Tên Sản phẩm", "Màu sắc", "Kích thước", "Năm BH", "Mô tả", "Số lượng tồn", "Giá nhập", "Giá bán"
            }
        ));
        tblChiTietSPs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietSPsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietSPs);

        jLabel8.setText("ID SPCT");

        jLabel9.setText("Sản phẩm");

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        jLabel10.setText("Màu sắc");

        jLabel11.setText("Kích thước");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Năm BH");

        txtNamBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamBHActionPerformed(evt);
            }
        });

        jLabel13.setText("Mô tả");

        jLabel14.setText("Số lượng tồn");

        jLabel15.setText("Giá nhập");

        jLabel16.setText("Giá bán");

        btnThemChiTietSP.setText("Thêm");
        btnThemChiTietSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChiTietSPActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");

        jButton3.setText("Xóa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(btnThemChiTietSP))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cboSanPham, 0, 157, Short.MAX_VALUE)
                                .addComponent(txtIDSPCT)
                                .addComponent(txtNamBH))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addComponent(txtMoTa, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel10))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboMauSac, 0, 201, Short.MAX_VALUE)
                            .addComponent(cboKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGiaNhap)
                            .addComponent(txtGiaBan)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtIDSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(cboKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemChiTietSP)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabSanPham.addTab("Chi tiết sản phẩm", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        tabSanPham.addTab("Thuộc tính", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabSanPham)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabSanPham)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNgungHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgungHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNgungHDActionPerformed

    private void tblSanPhamsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamsMouseClicked
        // TODO add your handling code here:
        int indexRow = tblSanPhams.getSelectedRow();
        this.setFormSanPham(indexRow);
        //khi click đúp vào dòng trong bảng SP-> bảng SPCT với thông tương ứng
        if(evt.getClickCount()==2){
            SanPham sp= lstSanPhams.get(indexRow);
            tabSanPham.setSelectedIndex(1);//chuyển sang tab ChiTietSP
            cboSanPham.setSelectedItem(sp);
            this.fillToTableChiTietSanPham();
        }
    }//GEN-LAST:event_tblSanPhamsMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        SanPham sp = this.getFormSanPham();
        sanPhamRepo.themSanPham(sp);
        this.fillToTableSanPham();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        SanPham sp = this.getFormSanPham();
        sanPhamRepo.suaSanPham(sp);
        this.fillToTableSanPham();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtNamBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamBHActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
         SanPham sp= (SanPham) cboSanPham.getSelectedItem();
         if(sp!=null){
             this.fillToTableChiTietSanPham();
         }
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void tblChiTietSPsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietSPsMouseClicked
        // TODO add your handling code here:
        int indexRow= tblChiTietSPs.getSelectedRow();
        this.setFormChiTietSanPham(indexRow);
    }//GEN-LAST:event_tblChiTietSPsMouseClicked

    private void btnThemChiTietSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChiTietSPActionPerformed
        // TODO add your handling code here:
        ChiTietSP chiTietSP= this.getFormChiTietSanPham();
        System.out.println(chiTietSP.toString());
        int result = chiTietRepo.themChiTietSanPham(chiTietSP);
        System.out.println("result = "+ result);
        fillToTableChiTietSanPham();
    }//GEN-LAST:event_btnThemChiTietSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemChiTietSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDongSP;
    private javax.swing.JComboBox<String> cboKichThuoc;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNSX;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoDangHD;
    private javax.swing.JRadioButton rdoNgungHD;
    private javax.swing.JTabbedPane tabSanPham;
    private javax.swing.JTable tblChiTietSPs;
    private javax.swing.JTable tblSanPhams;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtIDSPCT;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
