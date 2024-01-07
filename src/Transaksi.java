import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

// Class Transaksi yang merupakan turunan dari Class Barang
public class Transaksi extends Barang
{
    // Deklarasi variabel koneksi ke database
    Connection koneksi;
    String link = "jdbc:mysql://localhost:3306/pbotubes";
    
    // Scanner untuk input
    Scanner input = new Scanner(System.in);
    
    // Variabel-variabel yang akan digunakan dalam transaksi
    String namaPembeli;
    String noPembeli;
    String namaBarang;
    String kodeBarang;
    boolean barangReady=false, barangCukup=false, barangPas=false;
    int stok, stokKeluar, stokAwal, stokAkhir;
    int total = 0;
    boolean isMember = false;

    // Metode untuk memeriksa keanggotaan pembeli
    public void cekKeanggotaan() {
        System.out.print("\n    Apakah Pembeli member? (ya/tidak) : ");
        String jawaban = input.nextLine();
        if (jawaban.equalsIgnoreCase("ya")) {
            isMember = true;
        }
    }

    // Method untuk input nama pembeli
    public void nama_pembeli()
    {
        System.out.print("    Nama Pembeli\t: ");
        this.namaPembeli = input.nextLine();
    }

    public void no_pembeli()
    {
        System.out.print("    Nomor Pembeli\t: ");
        this.noPembeli = input.nextLine();
    }

    public void kode_barang()
    {
        System.out.print("\n    Kode Barang\t\t: ");
        this.kodeBarang = input.nextLine();
    }

    public void pembelianBarang()
    {
        System.out.print("    Jumlah Barang\t\t: ");
        this.stokKeluar = input.nextInt();
    }

    public void stok(Integer stok)
    {
        this.stokAwal = stok;
    }

    public void cekStok()
    {
        //Percabangan (if)
        if (this.stokAwal > this.stokKeluar)
        {
            barangCukup = true;
        }
        else if (this.stokAwal == this.stokKeluar)
        {
            barangPas = true;
        }
        else 
        {
            System.out.println("    Jumlah stok barang tidak cukup!");
        }
    }



    public void Beli() throws SQLException 
    {
        
        this.stokAkhir = this.stokAwal - this.stokKeluar; 
        this.total = this.stokKeluar * this.harga;
        
        if (isMember) {
            double diskon = 0.05 * this.total;
            int diskonInteger = (int)diskon; 
            this.total -= diskonInteger;
            System.out.println("    Pelanggan mendapatkan diskon 5%!");
        }

        koneksi = DriverManager.getConnection(link,"root","");
        String sql = "SELECT * FROM barang WHERE kode_barang='"+this.kodeBarang+"'";
        Statement statement = koneksi.createStatement();
        ResultSet result = statement.executeQuery(sql);

        
        if (result.next())
        {
            sql = "UPDATE barang SET stok = "+this.stokAkhir+" WHERE kode_barang ='"+this.kodeBarang+"'";
            if(statement.executeUpdate(sql) > 0)
            {
                System.out.println("    Total Harga : " + this.total);
                System.out.println("\n                ~ Transaksi Berhasil ! ~");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                Struk struk = new Struk(namaPembeli, noPembeli, namaBarang, kodeBarang, stokKeluar, total);
                struk.cetakStruk();

                return;
            }
        }

    }

    public void stokHabis() throws SQLException
    {
        koneksi = DriverManager.getConnection(link,"root","");
        String sql = "DELETE FROM barang WHERE kode_barang = '"+this.kodeBarang+"' ";
        Statement statement = koneksi.createStatement();
        if(statement.executeUpdate(sql) > 0)
        {
            System.out.println("    Barang dengan Kode barang ["+this.kodeBarang+"] telah habis");
        }
    }

    public void isiTabelPembelian() throws SQLException
    {

        koneksi = DriverManager.getConnection(link,"root","");
        Statement statement = koneksi.createStatement();
        String sql = "INSERT INTO beli_barang (nama_pembeli, no_pembeli, nama_barang, kode_barang, jumlah, harga, total) VALUES ('"+this.namaPembeli+"', '"+this.noPembeli+"', '"+this.namaBarang+"', '"+this.kodeBarang+"', "+this.stokKeluar+" , "+this.harga+" , "+this.total+")";
        statement.execute(sql);
    }

    @Override
    public void cari() throws SQLException 
    {
        //nama_barang();
        kode_barang();
        //Mengakses Database
        koneksi = DriverManager.getConnection(link,"root","");
        Statement statement = koneksi.createStatement();
        String sql = "SELECT * FROM barang WHERE kode_barang LIKE '"+this.kodeBarang+"'";
        ResultSet result = statement.executeQuery(sql); 

        
        if (result.next())
        {
            barangReady = true;
            this.namaBarang = result.getString("nama_barang");
            this.harga = result.getInt("harga");
            this.stok = result.getInt("stok");
            stok(result.getInt("stok"));
            
        }
    }

   
    @Override
    public void hapus() throws SQLException 
    {
        try
        {
            tampil();
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("            Transaksi Pembelian Barang ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            nama_pembeli();
            no_pembeli();
            cekKeanggotaan();
            cari();
            
            //Percabangan (if)
            if (barangReady)
            {
                pembelianBarang();
                cekStok();

                if (barangCukup)
                {
                    Beli();
                    isiTabelPembelian();
                }
                else if(barangPas)
                {
                    stokHabis();
                    isiTabelPembelian();
                }
            }
            else
            {
                System.out.println("    Barang tidak tersedia di database");
            }

        }
        //exception SQL
        catch(SQLException e)
        {
            System.err.println("    Error! Ada kesalahan dalam penginputan data");
            System.out.println("    Silahkan coba lagi");
        }
        
        catch(InputMismatchException e)
        {
            System.out.println("    Inputkan data yang benar!");
        }
    }
}
