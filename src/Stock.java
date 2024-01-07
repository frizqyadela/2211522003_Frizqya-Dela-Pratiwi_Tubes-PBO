import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//Interhitance, Class Stock merupakan kelas turunan (Sup Class / Child Class) dari class barang
public class Stock extends Barang
{
    // Deklarasi variabel koneksi ke database
    Connection conn;
    String link = "jdbc:mysql://localhost:3306/pbotubes";
    Scanner input = new Scanner(System.in);

    boolean barangReady=false;
    boolean supReady=false;
    Integer stok, stokAwal, stokAkhir;
    Integer harga;
    String nama_sup;
    String kode_sup;
    String noHP;

    // Method untuk memasukkan nama barang
    public void nama_barang()
    {
        System.out.print("    Nama Barang\t\t: ");
        this.namaBarang = input.nextLine();
    }

    // Method untuk memasukkan kode barang
    public void kode_barang()
    {
        System.out.print("    Kode Barang\t\t: ");
        this.kodeBarang = input.nextLine();
    }

    // Method untuk memasukkan kategori barang
    public void kategori()
    {
        System.out.print("    Kategori Barang\t: ");
        this.kategori = input.nextLine();
    }

    // Method untuk memasukkan harga barang
    public void harga()
    {
        System.out.print("    Harga \t\t: Rp ");
        this.harga = input.nextInt();
    }

    // Method untuk memasukkan stok barang
    public void stok()
    {
        System.out.print("    Stok Barang\t\t: ");
        this.stok = input.nextInt();
    }

    // Method untuk memasukkan nama supplier
    public void nama_sup()
    {
        System.out.print("    Nama Supplier\t: ");
        this.nama_sup = input.nextLine();
    }

    // Method untuk memasukkan kode supplier
    public void kode_sup()
    {
        System.out.print("\n    Kode Supplier\t: ");
        this.kode_sup = input.nextLine();
    }

    // Metode untuk memasukkan no Hp supplier
    public void noHP()
    {
        System.out.print("    Nomor HP Supplier\t: ");
        this.noHP = input.nextLine();
    }

    // Metode untuk memeriksa keberadaan kode barang di database
    public void cekKode_barang() throws SQLException
    { 
        //Mengecek data yang ada pada database pbotubes tabel barang
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM barang WHERE kode_barang LIKE '"+this.kodeBarang+"'";
        ResultSet result = statement.executeQuery(sql); 

        // Percabangan if, Jika inputan kode barang sudah terdapat pada database pbotubes, maka
        if (result.next())
        {
            System.out.println("Kode barang "+this.kodeBarang+" sudah ada pada database barang");
            this.stokAwal = result.getInt("stok");
            this.namaBarang = result.getString("nama_barang");
            barangReady = true;
        }
    }

    public void barangReady() throws SQLException
    {
        //Mengakses data pada database pbotubes tabel barang
        //Matematika (Proses penjumlahan) 
        this.stokAkhir = this.stokAwal + this.stok;
        String sql = "SELECT * FROM barang WHERE kode_barang = '"+this.kodeBarang+"'";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (result.next())
        {
            //update data pada database pbotubes tabel barang
            System.out.println("Kode barang '"+this.kodeBarang+"' telah di update");
            System.out.println("Stok awal [ "+this.stokAwal+" ] menjadi "+this.stokAkhir+" buah");
            sql = "UPDATE barang SET stok = "+this.stokAkhir+" WHERE kode_barang ='"+this.kodeBarang+"'";  
            
            if(statement.executeUpdate(sql) > 0)
            {
                System.out.println("Data Stok Telah Berhasil Diperbarui");
            }    
        }
    }

    public void supReady() throws SQLException
    {
        //Create data tabel barang_in pada database pbotubes
        conn = DriverManager.getConnection(link,"root","");
        String sql = "INSERT INTO barang_in (nama_barang, kode_barang, kategori, harga, stok, nama_sup, kode_sup, noHP) VALUES('"+this.namaBarang+"', '"+this.kodeBarang+"', '"+this.kategori+"', "+this.harga+", "+this.stok+", '"+this.nama_sup+"', '"+this.kode_sup+"', '"+this.noHP+"')";
        Statement statement = conn.createStatement();
        statement.execute(sql);
    }

    // Method untuk mencek kode supplier
    public void cekKode_sup() throws SQLException
    {
        //Mengakses data pada database pbotubes tabel barang_in
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM barang_in WHERE kode_sup LIKE '"+this.kode_sup+"'";
        ResultSet result = statement.executeQuery(sql); 

        if (result.next())
        {
            System.out.println("\n*Kode Supplier {'"+this.kode_sup+"'} sudah ada pada database*");
           
            //Menyimpan nilai data database pbotubes tabel barang_in ke dalam variabel
            this.nama_sup = result.getString("nama_sup");
            this.noHP = result.getString("noHP");
            supReady = true;
        }
    }

    // Method untuk memasukkan data barang baru pada database pbotubes tabel barang
    public void createBarang() throws SQLException
    {
        Barang barang = new Barang(this.namaBarang, this.kodeBarang, this.kategori, this.harga, this.stok);
        barang.methodKosong();

        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();

        //Create data (input data) ke dalam database pbotubes tabel barang
        String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, harga, stok) VALUES ('"+this.kodeBarang+"', '"+this.namaBarang+"', '"+this.kategori+"', "+this.harga+", "+this.stok+")";
        statement.execute(sql);
        System.out.println(" == Daftar barang baru telah disimpan ==");
    }
    

    // Method untuk create data supplier baru dan memasukkan data pada database pbotubes tabel barang_in
    public void createSupplier() throws SQLException
    {
        Supplier sup = new Supplier(this.nama_sup, this.kode_sup, this.noHP);
        sup.methodKosong();

        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();

        //Create data  ke dalam database pbotubes tabel barang_in
        String sql = "INSERT INTO barang_in (nama_barang, kode_barang, kategori, harga, stok, nama_sup, kode_sup, noHP) VALUES('"+this.namaBarang+"', '"+this.kodeBarang+"', '"+this.kategori+"', "+this.harga+", "+this.stok+", '"+this.nama_sup+"', '"+this.kode_sup+"', '"+this.noHP+"')";
        statement.execute(sql);
    }

    @Override
    public void simpan() throws SQLException 
    {
        //Exception Handling (try-catch)
        try
        {
            kode_sup();
            nama_sup();
            noHP();
            cekKode_sup();
            nama_barang();
            kode_barang();
            kategori();
            harga();
            stok();
            cekKode_barang();
            
            //percabangan (if)
            if (barangReady)
            {
                //percabangan (if)
                if (supReady)
                {
                    supReady();
                    barangReady();
                }
                else
                {
                    createSupplier();
                    barangReady();
                }
            }
            else
            {
                //percabangan (if)
                if (supReady)
                {
                    supReady();
                    createBarang();
                }
                else
                {
                    createSupplier();
                    createBarang();
                }
            }
        }

        //exception SQL, tangkap kesalahan jika ada kesalahan SQL
        catch(SQLException e)
        {
            System.err.println("\nError! Ada Kesalahan dalam penginputan data");
            System.out.println("Silahkan coba lagi");
        }

        //excception jika inputan tidak sesuai dengan tipe data dalam program
        catch(InputMismatchException e)
        {
            System.out.println("\nInputkan data yang benar!");
        }
    }
}