import java.util.Date;
import java.text.SimpleDateFormat;

// Class struk
public class Struk {
    String namaPembeli;
    String noPembeli;
    String namaBarang;
    String kodeBarang;
    int stokKeluar;
    int total;

    // Konstruktor untuk menginisialisasi objek Struk dengan data pembelian
    public Struk(String namaPembeli, String noPembeli, String namaBarang, String kodeBarang, int stokKeluar, int total) {
        this.namaPembeli = namaPembeli;
        this.noPembeli = noPembeli;
        this.namaBarang = namaBarang;
        this.kodeBarang = kodeBarang;
        this.stokKeluar = stokKeluar;
        this.total = total;
    }

    // Method untuk mencetak struk pembelian
    public void cetakStruk() {
        
        // Membuat objek Date untuk mendapatkan tanggal dan waktu saat ini
        Date date = new Date();

        // String Date (Tanggal dan Waktu)
        SimpleDateFormat hari = new SimpleDateFormat("'Hari/Tanggal \t:' EEEEEEEEEE, dd-MM-yyyy");
        SimpleDateFormat jam = new SimpleDateFormat("'Waktu \t:' hh:mm:ss z");

        // Mencetak struk
        String cetak = "              Struk Pembelian               ";
        System.out.println("\n============================================");
        System.out.println(cetak.toUpperCase());
        System.out.println("============================================");
        System.out.println(hari.format(date));
        System.out.println(jam.format(date));
        System.out.println("============================================");
        System.out.println("\n-------------- Data Pelanggan --------------");
        System.out.println("Nama Pembeli  : " + this.namaPembeli);
        System.out.println("Nomor Pembeli : " + this.noPembeli);
        System.out.println("\n---------------- Data Barang ---------------");
        System.out.println("Kode Barang   : " + this.kodeBarang);
        System.out.println("Nama Barang   : " + this.namaBarang);
        System.out.println("Jumlah Beli   : " + this.stokKeluar);
        System.out.println("____________________________________________");
        System.out.println("Total Harga   : " + this.total);
        String kasir = "Frizqya Dela Pratiwi";
        System.out.println("Kasir\t\t   : " + kasir.toUpperCase());
        System.out.println("============================================");
        System.out.println("\n\t   ~ Terima Kasih ~ ");
    }
}
