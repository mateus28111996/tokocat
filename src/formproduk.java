/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateus Anjas S
 */
import koneksi.koneksiDB;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class formproduk extends javax.swing.JFrame {
//membuat objek    
    private DefaultTableModel model;
    
    //deklarasi variabel
    String kdProduk, kdKategori,kategorii, nmProduk, ukuran1, satuan;
    int hrg_beli, hrg_jual, stok;
    /**
     * Creates new form formproduk
     */
    public formproduk() {
        initComponents();
         //membuat obyek
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tblProduk.setModel(model);
        model.addColumn("KODE CAT");
        model.addColumn("KODE KATEGORI");
        model.addColumn("NAMA_KATEGORI");
        model.addColumn("NAMA CAT");
        model.addColumn("UKURAN");
        model.addColumn("HARGA BELI");
        model.addColumn("HARGA JUAL");
        model.addColumn("STOK");
        model.addColumn("SATUAN");
        
        tampil_combo();
        //fungsi ambil data
        getDataProduk();
    }
        public void dataProduk(){   
        try{
            //tes koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
           
            //perintah sql untuk membaca data dari tabel produk
            String sql = "SELECT * FROM kategori WHERE kode_kategori = '"+ txtKdKategori.getSelectedItem() +"'";
            ResultSet res = stat.executeQuery(sql);
                        
            //baca data dan tampilkan pada text produk dan harga
            while(res.next()){
                //membuat obyek berjenis array
               nmkategori.setText(res.getString("nama_kategori"));
               
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     //fungsi membaca data kategori
    public void getDataProduk(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM produk";
            ResultSet res = stat.executeQuery(sql);
            
            //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[9];
                obj[0]=res.getString("kode_cat");
                obj[1]=res.getString("kode_kategori");
                obj[2]=res.getString("nama_kategori");
                obj[3]=res.getString("nama_cat");
                obj[4]=res.getString("ukuran");
                obj[5]=res.getString("harga_beli");
                obj[6]=res.getString("harga_jual");
                obj[7]=res.getString("stok");
                obj[8]=res.getString("satuan");
                model.addRow(obj);
            }
        }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void loadDataProduk(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        kdProduk = txtKdProduk.getText();
        kdKategori = (String) txtKdKategori.getSelectedItem();
        kategorii = nmkategori.getText();
        nmProduk = txtNmProduk.getText();
        ukuran1 = txtukuran.getText();
        hrg_beli = Integer.parseInt(txtHrgBeli.getText());
        hrg_jual = Integer.parseInt(txtHrgJual.getText());
        stok = Integer.parseInt(txtStok.getText());
        satuan = txtSatuan.getText(); 
    }
    
    public void dataSelect(){
        //deklarasi variabel
        int i = tblProduk.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        txtKdProduk.setText(""+model.getValueAt(i,0));
        txtKdKategori.setSelectedItem(""+model.getValueAt(i,1));
        nmkategori.setText(""+model.getValueAt(i,2));
        txtNmProduk.setText(""+model.getValueAt(i,3));
        txtukuran.setText(""+model.getValueAt(i,4));
        txtHrgBeli.setText(""+model.getValueAt(i,5));
        txtHrgJual.setText(""+model.getValueAt(i,6));
        txtStok.setText(""+model.getValueAt(i,7));
        txtSatuan.setText(""+model.getValueAt(i,8));
    }
    
    public void reset(){
        kdProduk = "";
        kdKategori = "";
        kategorii = "";
        nmProduk = "";
        ukuran1 = "";
        hrg_beli = 0;
        hrg_jual = 0;
        stok = 0;
        satuan = "";
        
        txtKdProduk.setText(kdProduk);
        txtKdKategori.setSelectedItem(kdKategori);
        nmkategori.setText(kategorii);
        txtNmProduk.setText(nmProduk);
        txtukuran.setText(ukuran1);
        txtHrgBeli.setText("");
        txtHrgJual.setText("");
        txtStok.setText("");
        txtSatuan.setText(satuan);
    }
     public void simpanDataProduk(){
         //panggil fungsi load data
        loadDataProduk();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO produk(kode_cat, kode_kategori, nama_kategori, nama_cat, ukuran, harga_beli, harga_jual, stok, satuan)"
                            + "VALUES('"+ kdProduk +"','"+ kdKategori 
                    
                    +"','"+ kategorii +"','" + nmProduk + "','" + ukuran1 +"','"+ hrg_beli +"','"+ hrg_jual +"','"+ stok +"', '"+ satuan +"')";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataProduk();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void rubahDataProduk(){
          //panggil fungsi load data
        loadDataProduk();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String sql  =   "UPDATE produk SET kode_kategori = '"+ kdKategori +"',"
                            + "nama_kategori  = '"+ kategorii +"',"        
                            + "nama_cat  = '"+ nmProduk +"',"
                            + "ukuran  = '"+ ukuran1 +"',"
                            + "harga_beli  = '"+ hrg_beli +"',"
                            + "harga_jual  = '"+ hrg_jual +"',"  
                            + "stok  = '"+ stok +"',"
                            + "satuan  = '"+ satuan +"'"
                            + "WHERE kode_cat = '" + kdProduk +"'";
            PreparedStatement p = (PreparedStatement) koneksiDB.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataProduk();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void hapusDataProduk(){
        //panggil fungsi ambil data
        loadDataProduk(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ kdProduk +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM produk WHERE kode_cat='"+ kdProduk +"'";
                PreparedStatement p =(PreparedStatement)koneksiDB.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataProduk();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "KENANGAN BERSAMANYA BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }
     public void tampil_combo()
    {
        try {
       Statement stat = (Statement) koneksiDB.getKoneksi().createStatement();
        String sql = "select kode_kategori from kategori order by kode_kategori asc";      
        ResultSet res = stat.executeQuery(sql);                               
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0] = res.getString(1);
            
            txtKdKategori.addItem((String) ob[0]);                                     
        }
        res.close(); stat.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduk = new javax.swing.JTable();
        cmdSimpan = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();
        cmdRubah = new javax.swing.JButton();
        cmdHapus = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        txtSatuan = new javax.swing.JTextField();
        txtHrgJual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHrgBeli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtukuran = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNmProduk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nmkategori = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtKdKategori = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtKdProduk = new javax.swing.JTextField();
        tblKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 102));
        jLabel1.setText("MASTER DATA PRODUK CAT");

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        tblProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduk);

        cmdSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdSimpan.setForeground(new java.awt.Color(0, 153, 102));
        cmdSimpan.setText("SIMPAN");
        cmdSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSimpanActionPerformed(evt);
            }
        });

        cmdReset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdReset.setForeground(new java.awt.Color(0, 153, 102));
        cmdReset.setText("RESET");
        cmdReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdResetActionPerformed(evt);
            }
        });

        cmdRubah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdRubah.setForeground(new java.awt.Color(0, 153, 102));
        cmdRubah.setText("RUBAH");
        cmdRubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRubahActionPerformed(evt);
            }
        });

        cmdHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmdHapus.setForeground(new java.awt.Color(0, 153, 102));
        cmdHapus.setText("HAPUS");
        cmdHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdHapusActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("STOK");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SATUAN");

        txtSatuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSatuanActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("HARGA JUAL");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("HARGA BELI");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("UKURAN");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NAMA CAT");

        nmkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmkategoriActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("NAMA KATEGORI");

        txtKdKategori.setForeground(new java.awt.Color(0, 153, 102));
        txtKdKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdKategoriActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("KODE KATEGORI");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("KODE CAT");

        tblKeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblKeluar.setForeground(new java.awt.Color(0, 153, 102));
        tblKeluar.setText("KELUAR");
        tblKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKdProduk)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtKdKategori, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtStok, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSatuan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHrgJual)
                                .addComponent(txtHrgBeli, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtukuran, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNmProduk, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nmkategori, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(cmdSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdReset)
                .addGap(18, 18, 18)
                .addComponent(cmdRubah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tblKeluar)
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKdProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKdKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nmkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNmProduk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHrgBeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHrgJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdHapus)
                    .addComponent(cmdRubah)
                    .addComponent(cmdReset)
                    .addComponent(cmdSimpan)
                    .addComponent(tblKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(694, 607));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdResetActionPerformed
        reset();
    }//GEN-LAST:event_cmdResetActionPerformed

    private void cmdRubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRubahActionPerformed
        rubahDataProduk();
    }//GEN-LAST:event_cmdRubahActionPerformed

    private void cmdHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHapusActionPerformed
        hapusDataProduk();
    }//GEN-LAST:event_cmdHapusActionPerformed

    private void tblProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdukMouseClicked
        dataSelect();
    }//GEN-LAST:event_tblProdukMouseClicked

    private void cmdSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSimpanActionPerformed
        simpanDataProduk();
    }//GEN-LAST:event_cmdSimpanActionPerformed

    private void nmkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmkategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmkategoriActionPerformed

    private void txtKdKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdKategoriActionPerformed
       dataProduk(); // TODO add your handling code here:
    }//GEN-LAST:event_txtKdKategoriActionPerformed

    private void txtSatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSatuanActionPerformed

    private void tblKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblKeluarActionPerformed
        this.dispose();
        new formutama().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_tblKeluarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formproduk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formproduk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdHapus;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdRubah;
    private javax.swing.JButton cmdSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nmkategori;
    private javax.swing.JButton tblKeluar;
    private javax.swing.JTable tblProduk;
    private javax.swing.JTextField txtHrgBeli;
    private javax.swing.JTextField txtHrgJual;
    private javax.swing.JComboBox<String> txtKdKategori;
    private javax.swing.JTextField txtKdProduk;
    private javax.swing.JTextField txtNmProduk;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtukuran;
    // End of variables declaration//GEN-END:variables
}
