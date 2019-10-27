/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeshop;
/**
 *
 * @author user
 */
public class Pemesan implements Total {
    int id_pemesanan;
    int no_meja;
    String barang;
    int hrg_satuan;
    int qty;
    //int ttl=0;

    public Pemesan(int id_psn, int no_mj, String brg, int hrg, int qt){
        id_pemesanan = id_psn;
        no_meja = no_mj;
        barang = brg;
        hrg_satuan = hrg;
        qty = qt;
    }
    @Override
    public int harga_qty() {
        return hrg_satuan * qty; //To change body of generated methods, choose Tools | Templates.
    }
    
}
