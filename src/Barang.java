import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

// Kelas Barang (Super Class / Parent Class) yang mengimplementasikan interface Menu
public class Barang implements Menu {

    // Deklarasi variabel koneksi ke database
    Connection koneksi;
    String link = "jdbc:mysql://localhost:3306/pbotubes";
    Scanner scanner = new Scanner(System.in);

    // Deklarasi variabel untuk atribut barang
    String namaBarang;
    String kodeBarang;
    String kategori;
    int harga;
    int stok;
    Boolean kosong = true;

    // Konstruktor kosong
    public Barang () { 

    }

    // Konstruktor dengan satu parameter
    public Barang(int x) {
        
    }

    // Konstruktor dengan banyak parameter
    public Barang (String namaBarang , String kodeBarang, String kategori, int harga, int stok) {

        // Inisialisasi atribut barang
        this.namaBarang = namaBarang;
        this.kodeBarang = kodeBarang;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;

        // Menampilkan rincian barang
        System.out.println("\n==================== R I N C I A N =====================");
        System.out.println("Nama barang \t: " + this.namaBarang); 
        System.out.println("Kode barang \t: " + this.kodeBarang); 
        System.out.println("Kategori \t: " + this.kategori); 
        System.out.println("Harga\t\t: " + this.harga); 
        System.out.println("Stok Barang \t: " + this.stok); 
        System.out.println("________________________________________________________");
    }

    // Method kosong
    public void methodKosong() {

    }

    // Implementasi method tampil dari interface Menu
    @Override
    public void tampil() throws SQLException 
    {
        // Method untuk menampilkan daftar barang dari database
        String sql = "SELECT * FROM barang";
        koneksi = DriverManager.getConnection(link,"root","");
        Statement statement = koneksi.createStatement();
		ResultSet result = statement.executeQuery(sql);

        System.out.println(" \n ==========================================================="); 
        System.out.println(" ||                 Daftar Barang Doldam                  ||"); 
        System.out.println(" ==========================================================="); 
       
        // Perulangan while
        while (result.next())
        {
            kosong = false;
            //Menampilkan data barang
            //Mengambil data dari pbotubes tabel barang dan menampilkannya
            System.out.print("    Nama Barang \t: ");
            System.out.println(result.getString("nama_barang"));
            System.out.print("    Kode Barang \t: ");
            System.out.println(result.getString("kode_barang"));
            System.out.print("    Kategori \t\t: ");
            System.out.println(result.getString("kategori"));
            System.out.print("    Harga\t\t: ");
            System.out.println(result.getInt("harga"));
            System.out.print("    Stok\t\t: ");
            System.out.println(result.getInt("stok"));
            System.out.println(" ===========================================================");
        }
        
        // Percabangan if (jika barang kosong akan menampilkan pesan tidak ada barang)
        if (kosong)
        {
            System.out.println("\t\t xxx. Data Barang Kosong .xxx ");
            System.out.println("__________________________________________________________");
        }
        statement.close();
    }

    // Implementasi method simpan dari interface Menu
    @Override
    public void simpan() throws SQLException {
        
    }

    // Implementasi method ubah dari interface Menu
    @Override
    public void ubah() throws SQLException 
    {
        try
        {
            // Memanggil metode tampil() untuk menampilkan daftar barang
            tampil();
            Integer pilih;
            System.out.println("\n\t ==== UBAH DATA BARANG ==== ");
            System.out.print(" \nMasukkan Kode Barang : ");
            String ubah = scanner.nextLine();

            // Membuat SQL untuk mengambil data barang berdasarkan kode yang dimasukkan
            String sql = "SELECT * FROM barang WHERE kode_barang ='"+ ubah +"'";
            Statement statement = koneksi.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // Percabangan if untuk memeriksa apakah data barang dengan kode yang dimasukkan ada
            if (result.next())
            {
                System.out.println("    \n-----------------------------------------");
                System.out.println("            Data yang ingin diubah");
                System.out.println("    -----------------------------------------");
                System.out.println("    1. Nama Barang");
                System.out.println("    2. Kategori");
                System.out.println("    3. Harga Barang ");
                System.out.println("    4. Stok");
                System.out.println("    -----------------------------------------");
                
                System.out.print("    Masukkan Pilihan :  ");
                pilih = scanner.nextInt();
                scanner.nextLine();
        
                // Percabangan switch case, menyediakan opsi perubahan yang berbeda 
                switch (pilih)
                {
                    case 1:
                        System.out.print("    Nama Barang ["+result.getString("nama_barang")+"]\t: ");
                        String ubah1 = scanner.nextLine();
                        //Update data nama barang pada database_barang tabel barang
                        sql = "UPDATE barang SET nama_barang = '"+ubah1+"' WHERE kode_barang ='"+ubah+"'";
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("    Data berhasil di perbarui!");
                        }
                    break;
        
                    case 2:
                        System.out.print("    Kategori ["+result.getString("kategori")+"]\t: ");
                        String ubah2 = scanner.nextLine();
                        //Update data nama barang pada database_barang tabel barang
                        sql = "UPDATE barang SET kategori = '"+ubah2+"' WHERE kode_barang ='"+ubah+"'";
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("    Data berhasil di perbarui!");
                        }
                    break;

                    case 3:
                        System.out.print("    Harga ["+result.getInt("harga")+"]\t: ");
                        Integer ubah3 = scanner.nextInt();
                        //update data harga pada database_barang tabel barang
                        sql = "UPDATE barang SET harga = "+ubah3+" WHERE kode_barang='"+ubah+"'";
                        scanner.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("    Data berhasil di perbarui!");
                        }
                    break;

                    case 4:
                        System.out.print("    Stok ["+result.getInt("stok")+"]\t: ");
                        Integer ubah4 = scanner.nextInt();
                        //update data stok pada database_barang tabel barang
                        sql = "UPDATE barang SET stok = "+ubah4+" WHERE kode_barang ='"+ubah+"'";
                        scanner.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("    Data berhasil di perbarui!");
                        }
                    break;

                    default :
                        System.out.println("    Pilihan tidak valid! Mohon inputkan sesuai no. pilihan yang tertera!");
                    break;
                }
            }
            else
            {
                System.out.println("    Kode barang tidak ditemukan");
            }
        }

        //exception SQL, tangkap kesalahan jika ada kesalahan SQL
        catch (SQLException e)
        {
            System.err.println("    *Error! Terdapat kesalahan update data");
        }

        //exception, tangkap kesalahan jika terjadi kesalahan tipe data masukan
        catch (InputMismatchException e)
        {
            System.err.println("    Input tidak valid. Mohon masukkan angka!");
        }
    }

    // Implementasi method hapus dari interface Menu
    @Override
    public void hapus() throws SQLException {
        
    }

    // Implementasi method cari dari interface Menu
    @Override
    public void cari() throws SQLException {
        
    }


}