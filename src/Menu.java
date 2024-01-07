import java.sql.*;

// Class Interface Menu berisi definisi metode-metode terkait pengelolaan barang
public interface Menu {
    // Method untuk menampilkan data barang
    void tampil() throws SQLException ;

    // Method untuk menyimpan data barang
    void simpan() throws SQLException ;

    // Method untuk mengubah data barang
    void ubah() throws SQLException ;

    // Method untuk menampilkan data barang
    void hapus() throws SQLException ;

    // Method untuk mencari daftar barang
    void cari() throws SQLException ;
}


