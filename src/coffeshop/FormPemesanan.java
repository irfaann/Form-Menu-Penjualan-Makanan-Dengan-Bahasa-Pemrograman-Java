/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeshop;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FormPemesanan extends javax.swing.JFrame {

    /**
     * Creates new form FormPemesanan
     */
    int hrg_satuan_rbpiscok = 15000;
    int hrg_satuan_rbcokeju = 20000;
    int hrg_satuan_rbgt = 20000;
    int hrg_satuan_kentang = 10000;
    int hrg_satuan_ayambalado = 25000;
    int hrg_satuan_ayambbq = 25000;
    int hrg_satuan_ayambp = 25000;
    int hrg_satuan_cp = 18000;
    int hrg_satuan_mc = 20000;
    int hrg_satuan_vlatte = 18000;
    int hrg_satuan_cm = 20000;
    int hrg_satuan_amr = 15000;
    int hrg_satuan_rob = 15000;
    int hrg_satuan_jos = 15000;
    int hrg_satuan_nyus = 15000;
    int hrg_satuan_tarik= 13000;
    int hrg_satuan_jtea = 10000;
    int hrg_satuan_matcha = 20000;
    int hrg_satuan_air = 5000;
    
    
    
    DefaultTableModel tabel1;
    DefaultTableModel tabel2;
    
    //int x=0;
    //int y=0;
    DBConnection dbConn = new DBConnection();
    Connection con = dbConn.getConnection();

    public FormPemesanan() throws SQLException {
        initComponents();
        Totalharga.setEditable(false);
        tabel1 = new DefaultTableModel();
        tabel.setModel(tabel1);
        tabel2 = new DefaultTableModel();
        tabeltotal.setModel(tabel2);
        //menulis di header
        tabel1.addColumn("no");
        tabel1.addColumn("Id pesanan"); tabel1.addColumn("no. meja");
        tabel1.addColumn("Barang"); tabel1.addColumn("Qty"); 
        tabel1.addColumn("Harga");
        
        
        tabel2.addColumn("Id pesanan"); tabel2.addColumn("Total Harga");
        no.setEditable(false);
        Totalharga.setEditable(false);
        Hitung.setEnabled(false);
        Hapuspesanan.setEnabled(false);
        //Simpanpesanan.setEnabled(false);
        Updatepesanan.setEnabled(false);
        
        tampilkanDiTabel();
        tampilkanDiTabel2();
    }
    public ArrayList[] getOrderList() throws SQLException{      
        String queryCount = "SELECT COUNT(*) as c FROM pesanan";
        Statement st;
        ResultSet rsCount, rs;       
        st = con.createStatement();        
        rsCount = st.executeQuery(queryCount);
        int sizeTable=0;

        while(rsCount.next()){
            sizeTable = rsCount.getInt("c");
        }       
        ArrayList[] orderList = new ArrayList[sizeTable];       
        String query = "SELECT * FROM pesanan";
        rs = st.executeQuery(query);

       int x=0;
        while(rs.next()){
            orderList[x] = new ArrayList<>();
            orderList[x].add(rs.getInt("no"));
            orderList[x].add(rs.getInt("id_pemesanan"));
            orderList[x].add(rs.getInt("no_meja"));
            orderList[x].add(rs.getString("barang"));
            orderList[x].add(rs.getInt("qty"));
            orderList[x].add(rs.getInt("harga"));

            x++;
        }
        return orderList;
    }
    
    public ArrayList[] getTotalList() throws SQLException{      
        String queryCount = "SELECT COUNT(*) as c FROM total";
        Statement st;
        ResultSet rsCount, rs;       
        st = con.createStatement();        
        rsCount = st.executeQuery(queryCount);
        int sizeTable=0;

        while(rsCount.next()){
            sizeTable = rsCount.getInt("c");
        }       
        ArrayList[] totalList = new ArrayList[sizeTable];       
        String query = "SELECT * FROM total";
        rs = st.executeQuery(query);

       int y=0;
        while(rs.next()){
            totalList[y] = new ArrayList<>();
            totalList[y].add(rs.getInt("id_pemesanan"));
            totalList[y].add(rs.getInt("total_harga"));
            

            y++;
        }
        return totalList;
    }

    
    public void tampilkanDiTabel() throws SQLException{
        ArrayList[] list = getOrderList();
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        
        Object[] row = new Object[6];
        for(int i= 0; i<list.length; i++){
            row[0] = list[i].get(0);
            row[1] = list[i].get(1); row[2]=list[i].get(2);
            row[3] = list[i].get(3); row[4]=list[i].get(4);
            row[5] = list[i].get(5);
            
            model.addRow(row);
        }
    }
    public void tampilkanDiTabel2() throws SQLException{
        ArrayList[] list = getTotalList();
        DefaultTableModel model = (DefaultTableModel) tabeltotal.getModel();
        
        Object[] row = new Object[4];
        for(int i= 0; i<list.length; i++){
            row[0] = list[i].get(0);
            row[1] = list[i].get(1); 
            
            model.addRow(row);
        }
    }
    public void kosongkanTextField(){
        //copas isi tambahLain
        no.setText("");
        Totalharga.setText("");
        Kembalian.setText("");
        Bayar.setText("");
        nomeja.setText("");
        id_pemesanan.setText("");
        RBpisangcoklat.setSelected(false);
        RBcoklatkeju.setSelected(false);
        RBgreentea.setSelected(false);
        kentang.setSelected(false);
        NAbalado.setSelected(false);
        NAbarbeque.setSelected(false);
        NAblackpaper.setSelected(false);
        Cappuchino.setSelected(false);
        Moccachino.setSelected(false);
        Caramel.setSelected(false);
        VLatte.setSelected(false);
        Arabika.setSelected(false);
        Robusta.setSelected(false);
        Kopijos.setSelected(false);
        Kopinyus.setSelected(false);
        Tehtarik.setSelected(false);
        Matcha.setSelected(false);
        Jasminetea.setSelected(false);
        Airmineral.setSelected(false);
        txtRBpisangcoklat.setText("");
        txtRBcoklatkeju.setText("");
        txtRBgreentea.setText("");
        txtkentang.setText("");
        txtNAbalado.setText("");
        txtNAbarbeque.setText("");
        txtNAblackpaper.setText("");
        txtCappuchino.setText("");
        txtMoccachino.setText("");
        txtCaramel.setText("");
        txtVLatte.setText("");
        txtArabika.setText("");
        txtRobusta.setText("");
        txtKopijos.setText("");
        txtKopinyus.setText("");
        txtMatcha.setText("");
        txtTehtarik.setText("");
        txtJasminetea.setText("");
        txtAirmineral.setText("");
        
        
    }
    public void kosongkanTabel(){
        DefaultTableModel model = (DefaultTableModel)this.tabel.getModel();
        DefaultTableModel model2 = (DefaultTableModel)this.tabeltotal.getModel();
        model.setRowCount(0);
        model2.setRowCount(0);
    }
    public void terpilih(int index) throws SQLException{
        
        //untuk mengisi text field dg informasi baris tabel yg dipilih
        //digunakan saat memilih salah satu baris di tabel
        ArrayList[] list = getOrderList();
        no.setText((String) list[index].get(0).toString());
        id_pemesanan.setText((String) list[index].get(1).toString());
        nomeja.setText((String) list[index].get(2).toString());
        if(list[index].get(3).equals("Cappuchino")){
            Cappuchino.setSelected(true);
            txtCappuchino.setText((String) list[index].get(4).toString());
        }else{
            Cappuchino.setSelected(false);
            txtCappuchino.setText("");
        }
        if(list[index].get(3).equals("Roti Bakar Coklat Keju")){
            RBcoklatkeju.setSelected(true);
            txtRBcoklatkeju.setText((String) list[index].get(4).toString());
        }else{
            RBcoklatkeju.setSelected(false);
            txtRBcoklatkeju.setText("");
        }
        if(list[index].get(3).equals("Roti Bakar Pisang Coklat")){
            RBpisangcoklat.setSelected(true);
            txtRBpisangcoklat.setText((String) list[index].get(4).toString());
        }else{
            RBpisangcoklat.setSelected(false);
            txtRBpisangcoklat.setText("");
        }
        if(list[index].get(3).equals("Roti Bakar Greentea")){
            RBgreentea.setSelected(true);
            txtRBgreentea.setText((String) list[index].get(4).toString());
        }else{
            RBgreentea.setSelected(false);
            txtRBgreentea.setText("");
        }
        if(list[index].get(3).equals("Kentang Goreng")){
            kentang.setSelected(true);
            txtkentang.setText((String) list[index].get(4).toString());
        }else{
            kentang.setSelected(false);
            txtkentang.setText("");
        }
        if(list[index].get(3).equals("Nasi Ayam Balado")){
            NAbalado.setSelected(true);
            txtNAbalado.setText((String) list[index].get(4).toString());
        }else{
            NAbalado.setSelected(false);txtNAbalado.setText("");
        }
        if(list[index].get(3).equals("Nasi Ayam Barbeque")){
            NAbarbeque.setSelected(true);
            txtNAbarbeque.setText((String) list[index].get(4).toString());
        }else{
            NAbarbeque.setSelected(false); txtNAbarbeque.setText("");
        }
        if(list[index].get(3).equals("Nasi Ayam Blackpaper")){
            NAblackpaper.setSelected(true);
            txtNAblackpaper.setText((String) list[index].get(4).toString());
        }else{
            NAblackpaper.setSelected(false);txtNAblackpaper.setText("");
        }
        if(list[index].get(3).equals("Cappuchino")){
            Cappuchino.setSelected(true);
            txtCappuchino.setText((String) list[index].get(4).toString());
        }else{
            Cappuchino.setSelected(false);txtCappuchino.setText("");
        }
        if(list[index].get(3).equals("Moccachino")){
            Moccachino.setSelected(true);
            txtMoccachino.setText((String) list[index].get(4).toString());
        }else{
            Moccachino.setSelected(false);txtMoccachino.setText("");
        }
        if(list[index].get(3).equals("Caramel")){
            Caramel.setSelected(true);
            txtCaramel.setText((String) list[index].get(4).toString());
        }else{
            Caramel.setSelected(false);txtCaramel.setText("");
        }
        if(list[index].get(3).equals("Vanilla Latte")){
            VLatte.setSelected(true);
            txtVLatte.setText((String) list[index].get(4).toString());
        }else{
            VLatte.setSelected(false);txtVLatte.setText("");
        }
        if(list[index].get(3).equals("Arabika")){
            Arabika.setSelected(true);
            txtArabika.setText((String) list[index].get(4).toString());
        }else{
            Arabika.setSelected(false);txtArabika.setText("");
        }
        if(list[index].get(3).equals("Robusta")){
            Robusta.setSelected(true);
            txtRobusta.setText((String) list[index].get(4).toString());
        }else{
            Robusta.setSelected(false);txtRobusta.setText("");
        }
        if(list[index].get(3).equals("Kopi Jos")){
            Kopijos.setSelected(true);
            txtKopijos.setText((String) list[index].get(4).toString());
        }else{
            Kopijos.setSelected(false);txtKopijos.setText("");
        }
        if(list[index].get(3).equals("Kopi Nyus")){
            Kopinyus.setSelected(true);
            txtKopinyus.setText((String) list[index].get(4).toString());
        }else{
            Kopinyus.setSelected(false);txtKopinyus.setText("");
        }
        if(list[index].get(3).equals("Teh Tarik")){
            Tehtarik.setSelected(true);
            txtTehtarik.setText((String) list[index].get(4).toString());
        }else{
            Tehtarik.setSelected(false);txtTehtarik.setText("");
        }
        if(list[index].get(3).equals("Jasmine Tea")){
            Jasminetea.setSelected(true);
            txtJasminetea.setText((String) list[index].get(4).toString());
        }else{
            Jasminetea.setSelected(false);txtJasminetea.setText("");
        }
        if(list[index].get(3).equals("Matcha")){
            Matcha.setSelected(true);
            txtMatcha.setText((String) list[index].get(4).toString());
        }else{
            Matcha.setSelected(false);txtMatcha.setText("");
        }
        if(list[index].get(3).equals("Air Mineral")){
            Airmineral.setSelected(true);
            txtAirmineral.setText((String) list[index].get(4).toString());
        }else{
            Airmineral.setSelected(false);txtAirmineral.setText("");
        }
        
        //Totalharga.setText((String) list[index].get(2).toString());
       

    }
    public int tampiltotal() throws SQLException {
        String satu = id_pemesanan.getText();
        String queryCount = "SELECT SUM(harga) as c FROM pesanan WHERE id_pemesanan = "+satu;
            Statement st;
            ResultSet rsCount, rs;       
            st = con.createStatement();        
            rsCount = st.executeQuery(queryCount);
            int totall =0;
            while(rsCount.next()){
            totall = rsCount.getInt("c");
        } 
           
            return totall;
    }
    public void updatetotal2() throws SQLException {
    String updateQuery = null;
        PreparedStatement ps = null;
        
        updateQuery = "UPDATE total SET total_harga=? "
                + "WHERE id_pemesanan=?";
       
           int i = tampiltotal();
            String k =String.valueOf(i);
            ps = con.prepareStatement(updateQuery);
                ps.setString(1, k);
                ps.setString(2, id_pemesanan.getText());
                ps.executeUpdate();
    }
    public void updatetotal() throws SQLException {
        String updateQuery = null;
        PreparedStatement ps = null;
        
        updateQuery = "INSERT INTO total(id_pemesanan,total_harga)"
                + "values(?,?)";
       
           int i = tampiltotal();
            String k =String.valueOf(i);
            ps = con.prepareStatement(updateQuery);
                ps.setString(2, k);
                ps.setString(1, id_pemesanan.getText());
                ps.executeUpdate();
       
            
           
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField12 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jTextField17 = new javax.swing.JTextField();
        jCheckBox10 = new javax.swing.JCheckBox();
        jTextField18 = new javax.swing.JTextField();
        jCheckBox11 = new javax.swing.JCheckBox();
        jTextField19 = new javax.swing.JTextField();
        jCheckBox12 = new javax.swing.JCheckBox();
        jTextField20 = new javax.swing.JTextField();
        jCheckBox13 = new javax.swing.JCheckBox();
        jTextField21 = new javax.swing.JTextField();
        jCheckBox14 = new javax.swing.JCheckBox();
        jTextField22 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomeja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRBpisangcoklat = new javax.swing.JTextField();
        txtRBcoklatkeju = new javax.swing.JTextField();
        txtRBgreentea = new javax.swing.JTextField();
        txtkentang = new javax.swing.JTextField();
        txtNAbalado = new javax.swing.JTextField();
        txtNAbarbeque = new javax.swing.JTextField();
        txtNAblackpaper = new javax.swing.JTextField();
        RBpisangcoklat = new javax.swing.JCheckBox();
        RBcoklatkeju = new javax.swing.JCheckBox();
        RBgreentea = new javax.swing.JCheckBox();
        kentang = new javax.swing.JCheckBox();
        NAbalado = new javax.swing.JCheckBox();
        NAbarbeque = new javax.swing.JCheckBox();
        NAblackpaper = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Cappuchino = new javax.swing.JCheckBox();
        Moccachino = new javax.swing.JCheckBox();
        Caramel = new javax.swing.JCheckBox();
        VLatte = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        txtCappuchino = new javax.swing.JTextField();
        txtMoccachino = new javax.swing.JTextField();
        txtCaramel = new javax.swing.JTextField();
        txtVLatte = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Robusta = new javax.swing.JCheckBox();
        Kopijos = new javax.swing.JCheckBox();
        Kopinyus = new javax.swing.JCheckBox();
        txtArabika = new javax.swing.JTextField();
        txtRobusta = new javax.swing.JTextField();
        txtKopijos = new javax.swing.JTextField();
        txtKopinyus = new javax.swing.JTextField();
        Arabika = new javax.swing.JCheckBox();
        Jasminetea = new javax.swing.JCheckBox();
        Matcha = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        txtTehtarik = new javax.swing.JTextField();
        txtJasminetea = new javax.swing.JTextField();
        txtMatcha = new javax.swing.JTextField();
        Tehtarik = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        Simpanpesanan = new javax.swing.JButton();
        Tambahlain = new javax.swing.JButton();
        Hapuspesanan = new javax.swing.JButton();
        Updatepesanan = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeltotal = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Totalharga = new javax.swing.JTextField();
        Bayar = new javax.swing.JTextField();
        Kembalian = new javax.swing.JTextField();
        Hitung = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        Airmineral = new javax.swing.JCheckBox();
        txtAirmineral = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        id_pemesanan = new javax.swing.JTextField();
        Tampiltotalharga = new javax.swing.JButton();
        no = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cetak = new javax.swing.JButton();

        jTextField12.setText("jTextField9");

        jRadioButton1.setText("jRadioButton1");

        jCheckBox8.setText("Roti Bakar Pisang Coklat");

        jCheckBox9.setText("Roti Bakar Coklat Keju");

        jTextField17.setText("jTextField9");

        jCheckBox10.setText("Roti Bakar Green Tea");

        jTextField18.setText("jTextField9");

        jCheckBox11.setText("Kentang Goreng");

        jTextField19.setText("jTextField9");

        jCheckBox12.setText("Nasi Ayam Balado");

        jTextField20.setText("jTextField9");

        jCheckBox13.setText("Nasi Ayam Barbeque");

        jTextField21.setText("jTextField9");

        jCheckBox14.setText("Nasi Ayam Blackpaper");

        jTextField22.setText("jTextField9");

        jLabel6.setText("Dessert :");

        jTextField23.setText("jTextField9");

        jLabel7.setText("Makanan : ");

        jLabel8.setText("Main Food :");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel1.setText("Form Pemesanan");

        jLabel2.setText("Nomer Meja :");

        nomeja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomejaActionPerformed(evt);
            }
        });

        jLabel3.setText("Makanan : ");

        txtRBpisangcoklat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRBpisangcoklatActionPerformed(evt);
            }
        });

        txtNAbalado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNAbaladoActionPerformed(evt);
            }
        });

        RBpisangcoklat.setText("Roti Bakar Pisang Coklat");
        RBpisangcoklat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBpisangcoklatActionPerformed(evt);
            }
        });

        RBcoklatkeju.setText("Roti Bakar Coklat Keju");
        RBcoklatkeju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBcoklatkejuActionPerformed(evt);
            }
        });

        RBgreentea.setText("Roti Bakar Green Tea");
        RBgreentea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBgreenteaActionPerformed(evt);
            }
        });

        kentang.setText("Kentang Goreng");
        kentang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kentangActionPerformed(evt);
            }
        });

        NAbalado.setText("Nasi Ayam Balado");
        NAbalado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAbaladoActionPerformed(evt);
            }
        });

        NAbarbeque.setText("Nasi Ayam Barbeque");
        NAbarbeque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAbarbequeActionPerformed(evt);
            }
        });

        NAblackpaper.setText("Nasi Ayam Blackpaper");
        NAblackpaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAblackpaperActionPerformed(evt);
            }
        });

        jLabel4.setText("Dessert :");

        jLabel5.setText("Main Course :");

        Cappuchino.setText("Cappuchino");
        Cappuchino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CappuchinoActionPerformed(evt);
            }
        });

        Moccachino.setText("Moccachino");
        Moccachino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoccachinoActionPerformed(evt);
            }
        });

        Caramel.setText("Caramel");
        Caramel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaramelActionPerformed(evt);
            }
        });

        VLatte.setText("Vanilla Latte");
        VLatte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VLatteActionPerformed(evt);
            }
        });

        jLabel9.setText("Coffe :");

        jLabel11.setText("Minuman :");

        Robusta.setText("Robusta");
        Robusta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RobustaActionPerformed(evt);
            }
        });

        Kopijos.setText("Es Kopi Jos");
        Kopijos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KopijosActionPerformed(evt);
            }
        });

        Kopinyus.setText("Es Kopi Nyus");
        Kopinyus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KopinyusActionPerformed(evt);
            }
        });

        Arabika.setText("Arabika");
        Arabika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArabikaActionPerformed(evt);
            }
        });

        Jasminetea.setText("Jasmine Tea");
        Jasminetea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JasmineteaActionPerformed(evt);
            }
        });

        Matcha.setText("Matcha");
        Matcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MatchaActionPerformed(evt);
            }
        });

        jLabel10.setText("Non-Coffe :");

        Tehtarik.setText("Teh Tarik");
        Tehtarik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TehtarikActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "no", "Id Pesanan", "No. Meja", "Pesanan", "Qty", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        Simpanpesanan.setText("SIMPAN PESANAN");
        Simpanpesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanpesananActionPerformed(evt);
            }
        });

        Tambahlain.setText("TAMBAH LAIN");
        Tambahlain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahlainActionPerformed(evt);
            }
        });

        Hapuspesanan.setText("HAPUS PESANAN");
        Hapuspesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapuspesananActionPerformed(evt);
            }
        });

        Updatepesanan.setText("UPDATE PESANAN");
        Updatepesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatepesananActionPerformed(evt);
            }
        });

        keluar.setText("KELUAR");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        tabeltotal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "No. Meja", "Total Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabeltotal);

        jLabel12.setText("Total Harga :");

        jLabel13.setText("Bayar :");

        jLabel14.setText("Kembalian :");

        Totalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalhargaActionPerformed(evt);
            }
        });

        Hitung.setText("HITUNG");
        Hitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HitungActionPerformed(evt);
            }
        });

        Airmineral.setText("Air Mineral");
        Airmineral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AirmineralActionPerformed(evt);
            }
        });

        jLabel15.setText("Id Pemesanan :");

        id_pemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_pemesananActionPerformed(evt);
            }
        });

        Tampiltotalharga.setText("TAMPIL TOTAL HARGA");
        Tampiltotalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TampiltotalhargaActionPerformed(evt);
            }
        });

        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noActionPerformed(evt);
            }
        });

        jLabel16.setText("No :");

        cetak.setText("CETAK D. PENJUALAN");
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(Totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(RBcoklatkeju)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(RBpisangcoklat)
                                                    .addComponent(kentang)
                                                    .addComponent(RBgreentea))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtkentang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtRBcoklatkeju, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtRBpisangcoklat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtRBgreentea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(NAbalado)
                                                    .addComponent(NAbarbeque)
                                                    .addComponent(NAblackpaper)
                                                    .addComponent(jLabel5))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNAbarbeque, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNAbalado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNAblackpaper, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel15)
                                        .addGap(4, 4, 4)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(id_pemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nomeja, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(keluar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel11))
                                                .addGap(103, 103, 103))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Moccachino)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(Kopinyus)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(txtKopinyus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(Kopijos)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtKopijos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(Robusta)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtRobusta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(Arabika)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtArabika, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(Cappuchino)
                                                                    .addComponent(VLatte)
                                                                    .addComponent(Caramel))
                                                                .addGap(22, 22, 22)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(txtVLatte, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtMoccachino, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtCappuchino, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtCaramel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(Tambahlain))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(Tehtarik)
                                                            .addComponent(Jasminetea)
                                                            .addComponent(Matcha))
                                                        .addGap(8, 8, 8)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtJasminetea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtTehtarik, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtMatcha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(Airmineral)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtAirmineral, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(2, 2, 2))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel14)
                                        .addComponent(Hitung))
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(70, 70, 70)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(Simpanpesanan)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(Updatepesanan))
                                        .addComponent(Hapuspesanan)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(Tampiltotalharga)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keluar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nomeja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_pemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Cappuchino)
                                    .addComponent(txtCappuchino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Tehtarik))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Moccachino)
                                    .addComponent(txtMoccachino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Jasminetea))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Caramel)
                                    .addComponent(txtCaramel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Matcha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(VLatte)
                                    .addComponent(txtVLatte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtArabika, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Arabika))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Robusta)
                                    .addComponent(txtRobusta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Kopijos)
                                    .addComponent(txtKopijos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Kopinyus)
                                    .addComponent(txtKopinyus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 29, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTehtarik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtJasminetea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMatcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Airmineral)
                                    .addComponent(txtAirmineral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Tambahlain)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RBpisangcoklat)
                            .addComponent(txtRBpisangcoklat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RBcoklatkeju)
                            .addComponent(txtRBcoklatkeju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RBgreentea)
                            .addComponent(txtRBgreentea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kentang)
                            .addComponent(txtkentang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNAbalado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NAbalado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNAbarbeque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NAbarbeque))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNAblackpaper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NAblackpaper))
                        .addGap(28, 28, 28)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(Totalharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Simpanpesanan, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Updatepesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Hitung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(Kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Hapuspesanan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tampiltotalharga)
                            .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomejaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomejaActionPerformed

    private void txtRBpisangcoklatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRBpisangcoklatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRBpisangcoklatActionPerformed

    private void txtNAbaladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNAbaladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNAbaladoActionPerformed

    private void ArabikaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArabikaActionPerformed
        // TODO add your handling code here:
        if(Arabika.isSelected()==true){
            txtArabika.setEditable(true);
        }else{
            txtArabika.setEditable(false);
            txtArabika.setText("");
        }
    }//GEN-LAST:event_ArabikaActionPerformed

    private void RBpisangcoklatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBpisangcoklatActionPerformed
        // TODO add your handling code here:
        if(RBpisangcoklat.isSelected()==true){
        txtRBpisangcoklat.setEditable(true);
        }else{txtRBpisangcoklat.setEditable(false);
        txtRBpisangcoklat.setText("");}
    }//GEN-LAST:event_RBpisangcoklatActionPerformed

    private void RBcoklatkejuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBcoklatkejuActionPerformed
        // TODO add your handling code here:
        if(RBcoklatkeju.isSelected()==true){
            txtRBcoklatkeju.setEditable(true);
        }else{
            txtRBcoklatkeju.setEditable(false);
            txtRBcoklatkeju.setText("");
        }
    }//GEN-LAST:event_RBcoklatkejuActionPerformed

    private void RBgreenteaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBgreenteaActionPerformed
        // TODO add your handling code here:
        if(RBgreentea.isSelected()==true){
            txtRBgreentea.setEditable(true);
        }else{
            txtRBgreentea.setEditable(false);
            txtRBgreentea.setText("");
        }
    }//GEN-LAST:event_RBgreenteaActionPerformed

    private void kentangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kentangActionPerformed
        // TODO add your handling code here:
        if(kentang.isSelected()==true){
            txtkentang.setEditable(true);
        }else{
            txtkentang.setEditable(false);
            txtkentang.setText("");
        }
    }//GEN-LAST:event_kentangActionPerformed

    private void NAbaladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAbaladoActionPerformed
        // TODO add your handling code here:
        if(NAbalado.isSelected()==true){
            txtNAbalado.setEditable(true);
        }else{
            txtNAbalado.setEditable(false);
            txtNAbalado.setText("");
        }
    }//GEN-LAST:event_NAbaladoActionPerformed

    private void NAbarbequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAbarbequeActionPerformed
        // TODO add your handling code here:
        if(NAbarbeque.isSelected()==true){
            txtNAbarbeque.setEditable(true);
        }else{
            txtNAbarbeque.setEditable(false);
            txtNAbarbeque.setText("");
        }
    }//GEN-LAST:event_NAbarbequeActionPerformed

    private void NAblackpaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAblackpaperActionPerformed
        // TODO add your handling code here:
        if(NAblackpaper.isSelected()==true){
            txtNAblackpaper.setEditable(true);
        }else{
            txtNAblackpaper.setEditable(false);
            txtNAblackpaper.setText("");
        }
    }//GEN-LAST:event_NAblackpaperActionPerformed

    private void CappuchinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CappuchinoActionPerformed
        // TODO add your handling code here:
        if(Cappuchino.isSelected()==true){
            txtCappuchino.setEditable(true);
        }else{
            txtCappuchino.setEditable(false);
            txtCappuchino.setText("");
        }
    }//GEN-LAST:event_CappuchinoActionPerformed

    private void MoccachinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoccachinoActionPerformed
        // TODO add your handling code here:
        if(Moccachino.isSelected()==true){
            txtMoccachino.setEditable(true);
        }else{
            txtMoccachino.setEditable(false);
            txtMoccachino.setText("");
        }
    }//GEN-LAST:event_MoccachinoActionPerformed

    private void CaramelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaramelActionPerformed
        // TODO add your handling code here:
        if(Caramel.isSelected()==true){
            txtCaramel.setEditable(true);
        }else{
            txtCaramel.setEditable(false);
            txtCaramel.setText("");
        }
    }//GEN-LAST:event_CaramelActionPerformed

    private void VLatteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VLatteActionPerformed
        // TODO add your handling code here:
        if(VLatte.isSelected()==true){
            txtVLatte.setEditable(true);
        }else{
            txtVLatte.setEditable(false);
            txtVLatte.setText("");
        }
    }//GEN-LAST:event_VLatteActionPerformed

    private void RobustaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RobustaActionPerformed
        // TODO add your handling code here:
        if(Robusta.isSelected()==true){
            txtRobusta.setEditable(true);
        }else{
            txtRobusta.setEditable(true);
            txtRobusta.setText("");
        }
    }//GEN-LAST:event_RobustaActionPerformed

    private void KopijosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KopijosActionPerformed
        // TODO add your handling code here:
        if(Kopijos.isSelected()==true){
            txtKopijos.setEditable(true);
        }else{
            txtKopijos.setEditable(false);
            txtKopijos.setText("");
        }
    }//GEN-LAST:event_KopijosActionPerformed

    private void KopinyusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KopinyusActionPerformed
        // TODO add your handling code here:
        if(Kopinyus.isSelected()==true){
            txtKopinyus.setEditable(true);
        }else{
            txtKopinyus.setEditable(false);
            txtKopinyus.setText("");
        }
    }//GEN-LAST:event_KopinyusActionPerformed

    private void TehtarikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TehtarikActionPerformed
        // TODO add your handling code here:
        if(Tehtarik.isSelected()==true){
            txtTehtarik.setEditable(true);
        }else{
            txtTehtarik.setEditable(false);
            txtTehtarik.setText("");
        }
    }//GEN-LAST:event_TehtarikActionPerformed

    private void JasmineteaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JasmineteaActionPerformed
        // TODO add your handling code here:
        if(Jasminetea.isSelected()==true){
            txtJasminetea.setEditable(true);
        }else{
            txtJasminetea.setEditable(false);
            txtJasminetea.setText("");
        }
    }//GEN-LAST:event_JasmineteaActionPerformed

    private void MatchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MatchaActionPerformed
        // TODO add your handling code here:
        if(Matcha.isSelected()==true){
            txtMatcha.setEditable(true);
        }else{
            txtMatcha.setEditable(false);
            txtMatcha.setText("");
        }
    }//GEN-LAST:event_MatchaActionPerformed

    private void TotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalhargaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_TotalhargaActionPerformed

    private void AirmineralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AirmineralActionPerformed
        // TODO add your handling code here:
        if(Airmineral.isSelected()==true){
            txtAirmineral.setEditable(true);
        }else{
            txtAirmineral.setEditable(false);
            txtAirmineral.setText("");
        }
    }//GEN-LAST:event_AirmineralActionPerformed

    private void HitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HitungActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;
        String strtotal, strbayar;
        strtotal = Totalharga.getText();
        strbayar = Bayar.getText();
        total = Integer.parseInt(strtotal);
        bayar = Integer.parseInt(strbayar);
        kembalian = bayar - total;
        Kembalian.setText(String.valueOf(kembalian));
    }//GEN-LAST:event_HitungActionPerformed

    private void SimpanpesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanpesananActionPerformed
        // TODO add your handling code here:
        Hitung.setEnabled(true);
        //Simpanpesanan.setEnabled(false);
        Updatepesanan.setEnabled(false);
        Hapuspesanan.setEnabled(false);
        
        int total = 0;
            int id;
            int nmj;
            String brg;//convert string to double
            int hrg;
            int qty;
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO pesanan(id_pemesanan,no_meja,barang,qty,harga)"
                    + "values(?,?,?,?,?) ");
            
            //PreparedStatement ttl = con.prepareStatement("INSERT INTO total(id_pemesanan,total_harga,bayar,kembalian)"
                   // + "values(?,?,?,?) ");
           
            if(RBpisangcoklat.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Roti Bakar Pisang Coklat";//convert string to double
                hrg = hrg_satuan_rbpiscok;
                qty = Integer.parseInt(txtRBpisangcoklat.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtRBpisangcoklat.getText());
                String rbb =String.valueOf(m.harga_qty());
                ps.setString(5, rbb);
                ps.executeUpdate();
                
            }
            if(RBcoklatkeju.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Roti Bakar Coklat Keju";
                hrg = hrg_satuan_rbcokeju;
                qty = Integer.parseInt(txtRBcoklatkeju.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtRBcoklatkeju.getText());
                String rck = String.valueOf(m.harga_qty());
                ps.setString(5, rck);
                ps.executeUpdate();
                
            }
            if(RBgreentea.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Roti Bakar Greentea";
                hrg = hrg_satuan_rbgt;
                qty = Integer.parseInt(txtRBgreentea.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtRBgreentea.getText());
                String rbb =String.valueOf(m.harga_qty());
                ps.setString(5, rbb);
                ps.executeUpdate();
                
            }
            if(kentang.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Kentang Goreng";
                hrg = hrg_satuan_kentang;
                qty = Integer.parseInt(txtkentang.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                total = total + m.harga_qty();
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtkentang.getText());
                String rbb =String.valueOf(m.harga_qty());
                ps.setString(5, rbb);
                ps.executeUpdate();
                
            }
            if(NAbalado.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Nasi Ayam Balado";
                hrg = hrg_satuan_ayambalado;
                qty = Integer.parseInt(txtNAbalado.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
 
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtNAbalado.getText());
                
                String rbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, rbb);
                ps.executeUpdate();
                
            }
            if(NAbarbeque.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Nasi Ayam Barbeque";
                hrg = hrg_satuan_ayambbq;
                qty = Integer.parseInt(txtNAbarbeque.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
        
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtNAbarbeque.getText());
                String rbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, rbb);
                ps.executeUpdate();
                
            }
            if(NAblackpaper.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Nasi Ayam Blackpaper";
                hrg = hrg_satuan_ayambp;
                qty = Integer.parseInt(txtNAblackpaper.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
           
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtNAblackpaper.getText());
                String rbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, rbb);
                ps.executeUpdate();
             
            }
            if(Cappuchino.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Cappuchino";
                hrg = hrg_satuan_cp;
                qty = Integer.parseInt(txtCappuchino.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
             
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtCappuchino.getText());
                String cpp =String.valueOf(m.hrg_satuan);
                ps.setString(5, cpp);
                ps.executeUpdate();
            }
            if(Moccachino.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Moccachino";
                hrg = hrg_satuan_mc;
                qty = Integer.parseInt(txtMoccachino.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
         
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtMoccachino.getText());
                String cpp =String.valueOf(m.hrg_satuan);
                ps.setString(5, cpp);
                ps.executeUpdate();
          
            }
        if(Caramel.isSelected()){
            id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Caramel";
                hrg = hrg_satuan_cm;
                qty = Integer.parseInt(txtCaramel.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                
            ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtCaramel.getText());
                String cpp =String.valueOf(m.hrg_satuan);
                ps.setString(5, cpp);
                ps.executeUpdate();
            
        }
        if(VLatte.isSelected()){
            id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Vanilla Latte";
                hrg = hrg_satuan_vlatte;
                qty = Integer.parseInt(txtVLatte.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                
            ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtVLatte.getText());
                String cpp =String.valueOf(m.hrg_satuan);
                ps.setString(5, cpp);
                ps.executeUpdate();
            
        }
            if(Arabika.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Arabika";
                hrg = hrg_satuan_amr;
                qty = Integer.parseInt(txtArabika.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtArabika.getText());
                String arbb =String.valueOf(m.harga_qty());
                ps.setString(5, arbb);
                ps.executeUpdate();
            }
            if(Robusta.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Robusta";
                hrg = hrg_satuan_rob;
                qty = Integer.parseInt(txtRobusta.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtRobusta.getText());
                String arbb =String.valueOf(m.harga_qty());
                ps.setString(5, arbb);
                ps.executeUpdate();
                
            }
            if(Kopijos.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Kopi Jos";
                hrg = hrg_satuan_jos;
                qty = Integer.parseInt(txtKopijos.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                total = total + m.harga_qty();
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, "Kopi Jos");
                ps.setString(4, txtKopijos.getText());
                String arb = txtKopijos.getText();
                int jml_arb = Integer.parseInt(arb);
                int hrg_arb = jml_arb * hrg_satuan_jos;
                String arbb =String.valueOf(hrg_arb);
                ps.setString(5, arbb);
                ps.executeUpdate();
            }
            if(Kopinyus.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Kopi Nyus";
                hrg = hrg_satuan_nyus;
                qty = Integer.parseInt(txtKopinyus.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtKopinyus.getText());
                String arbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, arbb);
                ps.executeUpdate();
            }
            if(Tehtarik.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Teh Tarik";
                hrg = hrg_satuan_tarik;
                qty = Integer.parseInt(txtTehtarik.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                total = total + m.harga_qty();
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtTehtarik.getText());
                String arbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, arbb);
                ps.executeUpdate();

            }
            if(Jasminetea.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Jasmine Tea";
                hrg = hrg_satuan_jtea;
                qty = Integer.parseInt(txtJasminetea.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtJasminetea.getText());
                String arbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, arbb);
                ps.executeUpdate();

            }
            if(Matcha.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Matcha";
                hrg = hrg_satuan_matcha;
                qty = Integer.parseInt(txtMatcha.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);
                total = total + m.harga_qty();
                
                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtMatcha.getText());
                String arbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, arbb);
                ps.executeUpdate();

            }
            if(Airmineral.isSelected()){
                id = Integer.parseInt(id_pemesanan.getText());
                nmj = Integer.parseInt(nomeja.getText());
                brg = "Air Mineral";
                hrg = hrg_satuan_air;
                qty = Integer.parseInt(txtAirmineral.getText());
                Pemesan m = new Pemesan(id,nmj,brg,hrg,qty);

                ps.setString(1, id_pemesanan.getText());
                ps.setString(2, nomeja.getText());
                ps.setString(3, brg);
                ps.setString(4, txtAirmineral.getText());
                String arbb =String.valueOf(m.hrg_satuan);
                ps.setString(5, arbb);
                ps.executeUpdate();
            }
            Totalharga.setText(""+tampiltotal());
            updatetotal();
JOptionPane.showMessageDialog(null, "Data tersimpan");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan");
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Data beluminput");
        }
        try {
            //kosongkanTextField();
            kosongkanTabel();
            tampilkanDiTabel();
            tampilkanDiTabel2();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SimpanpesananActionPerformed

    private void id_pemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_pemesananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_pemesananActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // TODO add your handling code here:
        int index = tabel.getSelectedRow();
        try {
            terpilih(index);
            Totalharga.setText("");
        Hapuspesanan.setEnabled(true);
        Simpanpesanan.setEnabled(false);
        Updatepesanan.setEnabled(true);
        
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
        //proses1.setEnabled(true);
        //hapus.setEnabled(true);
    }//GEN-LAST:event_tabelMouseClicked

    private void TambahlainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahlainActionPerformed
        // TODO add your handling code here:
        kosongkanTextField();
        Hitung.setEnabled(false);
        Simpanpesanan.setEnabled(true);
        Updatepesanan.setEnabled(false);
        
    }//GEN-LAST:event_TambahlainActionPerformed

    private void UpdatepesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatepesananActionPerformed
        // TODO add your handling code here:
        String updateQuery = null;
        PreparedStatement ps = null;
        
        updateQuery = "UPDATE pesanan SET barang=?, qty=?, harga=? "
                + "WHERE no=?";
       try {
            int a,b;
            String c;
            ps = con.prepareStatement(updateQuery);
            if(RBpisangcoklat.isSelected()){
                a = Integer.parseInt(txtRBpisangcoklat.getText());
                b = a*hrg_satuan_rbpiscok;
                c = String.valueOf(b);
                ps.setString(1, "Roti Bakar Pisang Coklat");
                ps.setString(2, txtRBpisangcoklat.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            if(RBcoklatkeju.isSelected()){
                a = Integer.parseInt(txtRBcoklatkeju.getText());
                b = a*hrg_satuan_rbcokeju;
                c = String.valueOf(b);
                ps.setString(1, "Roti Bakar Pisang Keju");
                ps.setString(2, txtRBcoklatkeju.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            if(RBgreentea.isSelected()){
                a = Integer.parseInt(txtRBgreentea.getText());
                b = a*hrg_satuan_rbgt;
                c = String.valueOf(b);
                ps.setString(1, "Roti Bakar Greentea");
                ps.setString(2, txtRBgreentea.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
                
            }
            if(kentang.isSelected()){
               a = Integer.parseInt(txtkentang.getText());
                b = a*hrg_satuan_kentang;
                c = String.valueOf(b);
                ps.setString(1, "Kentang Goreng");
                ps.setString(2, txtkentang.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
                
            }
            if(NAbalado.isSelected()){
                a = Integer.parseInt(txtNAbalado.getText());
                b = a*hrg_satuan_ayambalado;
                c = String.valueOf(b);
                ps.setString(1, "Nasi Ayam Balado");
                ps.setString(2, txtNAbalado.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
                
            }
            if(NAbarbeque.isSelected()){
                a = Integer.parseInt(txtNAbarbeque.getText());
                b = a*hrg_satuan_ayambbq;
                c = String.valueOf(b);
                ps.setString(1, "Nasi Ayam Barbeque");
                ps.setString(2, txtNAbarbeque.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
                
            }
            if(NAblackpaper.isSelected()){
                a = Integer.parseInt(txtNAblackpaper.getText());
                b = a*hrg_satuan_ayambp;
                c = String.valueOf(b);
                ps.setString(1, "Nasi Ayam Blackpaper");
                ps.setString(2, txtNAblackpaper.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
             
            }
            if(Cappuchino.isSelected()){
                a = Integer.parseInt(txtCappuchino.getText());
                b = a*hrg_satuan_cp;
                c = String.valueOf(b);
                ps.setString(1, "Cappuchino");
                ps.setString(2, txtCappuchino.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            if(Moccachino.isSelected()){
                a = Integer.parseInt(txtMoccachino.getText());
                b = a*hrg_satuan_mc;
                c = String.valueOf(b);
                ps.setString(1, "Moccachino");
                ps.setString(2, txtMoccachino.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
          
            }
        if(Caramel.isSelected()){
            a = Integer.parseInt(txtCaramel.getText());
                b = a*hrg_satuan_cm;
                c = String.valueOf(b);
                ps.setString(1, "Caramel");
                ps.setString(2, txtCaramel.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            
        }
        if(VLatte.isSelected()){
            a = Integer.parseInt(txtVLatte.getText());
                b = a*hrg_satuan_vlatte;
                c = String.valueOf(b);
                ps.setString(1, "Vanilla Latte");
                ps.setString(2, txtVLatte.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            
        }
            if(Arabika.isSelected()){
                a = Integer.parseInt(txtArabika.getText());
                b = a*hrg_satuan_amr;
                c = String.valueOf(b);
                ps.setString(1, "Arabika");
                ps.setString(2, txtArabika.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            if(Robusta.isSelected()){
                a = Integer.parseInt(txtRobusta.getText());
                b = a*hrg_satuan_rob;
                c = String.valueOf(b);
                ps.setString(1, "Robusta");
                ps.setString(2, txtRobusta.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
                
            }
            if(Kopijos.isSelected()){
                a = Integer.parseInt(txtKopijos.getText());
                b = a*hrg_satuan_jos;
                c = String.valueOf(b);
                ps.setString(1, "Kopi Jos");
                ps.setString(2, txtKopijos.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            if(Kopinyus.isSelected()){
                a = Integer.parseInt(txtKopinyus.getText());
                b = a*hrg_satuan_nyus;
                c = String.valueOf(b);
                ps.setString(1, "Kopi Nyus");
                ps.setString(2, txtKopinyus.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            if(Tehtarik.isSelected()){
                a = Integer.parseInt(txtTehtarik.getText());
                b = a*hrg_satuan_tarik;
                c = String.valueOf(b);
                ps.setString(1, "Teh Tarik");
                ps.setString(2, txtTehtarik.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();

            }
            if(Jasminetea.isSelected()){
                a = Integer.parseInt(txtJasminetea.getText());
                b = a*hrg_satuan_jtea;
                c = String.valueOf(b);
                ps.setString(1, "Jasmine Tea");
                ps.setString(2, txtJasminetea.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();

            }
            if(Matcha.isSelected()){
                a = Integer.parseInt(txtMatcha.getText());
                b = a*hrg_satuan_matcha;
                c = String.valueOf(b);
                ps.setString(1, "Matcha");
                ps.setString(2, txtMatcha.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();

            }
            if(Airmineral.isSelected()){
                a = Integer.parseInt(txtAirmineral.getText());
                b = a*hrg_satuan_air;
                c = String.valueOf(b);
                ps.setString(1, "Air Mineral");
                ps.setString(2, txtAirmineral.getText());
                ps.setString(3, c);
                ps.setString(4, no.getText());
                ps.executeUpdate();
            }
            updatetotal2();
            
            
            
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data tidak berhasil diupdate");
        }     
        try {
            kosongkanTextField();
            kosongkanTabel();
            tampilkanDiTabel();            tampilkanDiTabel2();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }//GEN-LAST:event_UpdatepesananActionPerformed

    private void HapuspesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapuspesananActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM pesanan WHERE no = ?");
            String pesanan = no.getText();
            ps.setString(1, pesanan);
            ps.executeUpdate();
            
            String satu = id_pemesanan.getText();
            String queryCount = "SELECT SUM(harga) as c FROM pesanan WHERE id_pemesanan =  "+satu;
            Statement st;
            ResultSet rsCount, rs;       
            st = con.createStatement();        
            rsCount = st.executeQuery(queryCount);
            int totall =0;
            while(rsCount.next()){
                totall = rsCount.getInt("c");
            } 
            
            if(totall != NULL){
                updatetotal();
            }else{
                
            }
            Hapuspesanan.setEnabled(false);
            Updatepesanan.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Pesanan berhasil dihapus");
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Pesanan tidak berhasil dihapus");
}
          
        finally{
            try {
                kosongkanTextField();
                kosongkanTabel();
                tampilkanDiTabel();                tampilkanDiTabel2();

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }//GEN-LAST:event_HapuspesananActionPerformed

    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noActionPerformed

    private void TampiltotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TampiltotalhargaActionPerformed
        // TODO add your handling code here:
        Hitung.setEnabled(true);
        try{
            String satu = id_pemesanan.getText();
            String queryCount = "SELECT SUM(harga) as c FROM pesanan WHERE id_pemesanan = "+satu;
            Statement st;
            ResultSet rsCount, rs;
            st = con.createStatement();
            rsCount = st.executeQuery(queryCount);
            int totall =0;
            while(rsCount.next()){
                totall = rsCount.getInt("c");
            }
            Totalharga.setText(""+totall);
        } catch (SQLException ex) {
            Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Pilih Pesanan di tabel terlebih dahulu");
        }

    }//GEN-LAST:event_TampiltotalhargaActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_keluarActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Data Penjualan");
        //add import MessageFormat
        MessageFormat footer = new MessageFormat("Page{0, number, integer}");
        try{
            tabel.print(JTable.PrintMode.NORMAL, header, footer);
        }catch(java.awt.print.PrinterException e){
            System.err.format("Cannot print %s", e.getMessage());
        }

    }//GEN-LAST:event_cetakActionPerformed

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
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormPemesanan().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Airmineral;
    private javax.swing.JCheckBox Arabika;
    private javax.swing.JTextField Bayar;
    private javax.swing.JCheckBox Cappuchino;
    private javax.swing.JCheckBox Caramel;
    private javax.swing.JButton Hapuspesanan;
    private javax.swing.JButton Hitung;
    private javax.swing.JCheckBox Jasminetea;
    private javax.swing.JTextField Kembalian;
    private javax.swing.JCheckBox Kopijos;
    private javax.swing.JCheckBox Kopinyus;
    private javax.swing.JCheckBox Matcha;
    private javax.swing.JCheckBox Moccachino;
    private javax.swing.JCheckBox NAbalado;
    private javax.swing.JCheckBox NAbarbeque;
    private javax.swing.JCheckBox NAblackpaper;
    private javax.swing.JCheckBox RBcoklatkeju;
    private javax.swing.JCheckBox RBgreentea;
    private javax.swing.JCheckBox RBpisangcoklat;
    private javax.swing.JCheckBox Robusta;
    private javax.swing.JButton Simpanpesanan;
    private javax.swing.JButton Tambahlain;
    private javax.swing.JButton Tampiltotalharga;
    private javax.swing.JCheckBox Tehtarik;
    private javax.swing.JTextField Totalharga;
    private javax.swing.JButton Updatepesanan;
    private javax.swing.JCheckBox VLatte;
    private javax.swing.JButton cetak;
    private javax.swing.JTextField id_pemesanan;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JButton keluar;
    private javax.swing.JCheckBox kentang;
    private javax.swing.JTextField no;
    private javax.swing.JTextField nomeja;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabeltotal;
    private javax.swing.JTextField txtAirmineral;
    private javax.swing.JTextField txtArabika;
    private javax.swing.JTextField txtCappuchino;
    private javax.swing.JTextField txtCaramel;
    private javax.swing.JTextField txtJasminetea;
    private javax.swing.JTextField txtKopijos;
    private javax.swing.JTextField txtKopinyus;
    private javax.swing.JTextField txtMatcha;
    private javax.swing.JTextField txtMoccachino;
    private javax.swing.JTextField txtNAbalado;
    private javax.swing.JTextField txtNAbarbeque;
    private javax.swing.JTextField txtNAblackpaper;
    private javax.swing.JTextField txtRBcoklatkeju;
    private javax.swing.JTextField txtRBgreentea;
    private javax.swing.JTextField txtRBpisangcoklat;
    private javax.swing.JTextField txtRobusta;
    private javax.swing.JTextField txtTehtarik;
    private javax.swing.JTextField txtVLatte;
    private javax.swing.JTextField txtkentang;
    // End of variables declaration//GEN-END:variables
}
