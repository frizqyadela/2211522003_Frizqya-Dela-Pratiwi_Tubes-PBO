import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


// Class DoldamTools merupakan kelas driver
public class DoldamTools {

    // Variabel untuk koneksi database
    static Connection koneksi;
    static String link = "jdbc:mysql://localhost:3306/pbotubes";

    // Inisialisasi objek Scanner untuk input dari pengguna
    static Scanner input = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);

    // Daftar kredensial yang valid untuk login menggunakan HashMap
    static Map<String, String> validCredentials = new HashMap<>();

    // Mengisi kredensial valid ke dalam HashMap saat program dimulai
    static {
        validCredentials.put("doldam", "dam123");
    }

    // Method untuk menampilkan captcha
    public static String generateCaptcha() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(code.length());
            sb.append(code.charAt(index));
        }

        return sb.toString();
    }

    // Fungsi utama yang akan dieksekusi saat program dijalankan
    public static void main(String[] args) throws Exception {

        //String Date (Tanggal dan Waktu)
        Date date = new Date();
        SimpleDateFormat hari = new SimpleDateFormat("'Hari/Tanggal \t:' EEEEEEEEEE, dd-MM-yyyy");
        SimpleDateFormat jam = new SimpleDateFormat("'Waktu \t:' hh:mm:ss z");

        // Method string (LowerCase)
        String tagline = "|      ~ ENTRUST YOUR HEALTH TO DOLDAM ~     |";
        System.out.println("\n==============================================\n|\t D O L D A M  M E D  T O O L S\t     |");
        System.out.println(tagline.toLowerCase());
        System.out.println("=============================================="); 
        System.out.println("|                                            |");
        System.out.println("| "+hari.format(date)+"\t     |");
        System.out.println("| "+jam.format(date)+"\t\t     |");
        System.out.println("==============================================\n");

        // Memanggil fungsi untuk proses login
        login();
    }

    // Method Login untuk proses login
    public static void login() {

        boolean isValidLogin = false;
        boolean isValidCaptcha = false;

        System.out.println("\n\t < Welcome, Silahkan Login ! >");
        System.out.println("----------------------------------------------");

        // Perulangan (do-while) untuk proses validasi login menggunakan username dan password
        do {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            isValidLogin = validasiLogin(username, password);

            // Percabangan if untuk mengevaluasi apakah login valid
            if (!isValidLogin) {
                System.out.println("\nLogin gagal. Silakan coba lagi!\n");
            }
        } while (!isValidLogin);

        // Perulangan (do-while) untuk proses validasi CAPTCHA
        do {
            String expectedCaptcha = generateCaptcha();
            System.out.println("\n ------------------------");
            System.out.println("    CAPTCHA: " + expectedCaptcha );
            System.out.println("-------------------------");
            
            // Method EqualsIgnoreCase untuk validasi captcha
            System.out.print("Masukkan CAPTCHA: ");
            String enteredCaptcha = scanner.nextLine();

            isValidCaptcha = expectedCaptcha.equalsIgnoreCase(enteredCaptcha);

            // Percabangan if untuk mengevaluasi apakah captcha valid
            if (!isValidCaptcha) {
                System.out.println("\nCAPTCHA salah. Silakan coba lagi!");
            }
        } while (!isValidCaptcha);

        System.out.println("\n\n  * LOGIN BERHASIL * ");

        // Exception try-catch jika login berhasil, menu utama ditampilkan
        try {
            menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    // Method untuk validasi login berdasarkan kredensial (method string equals)
    public static boolean validasiLogin(String enteredUsername, String enteredPassword) {
        return validCredentials.containsKey(enteredUsername) &&
                validCredentials.get(enteredUsername).equals(enteredPassword);
    }


    // Method untuk menampilkan menu utama dan memproses pilihan 
    private static void menu() throws SQLException {
        boolean remenu = true;
        boolean ulangi = true;
        int pilih;
        String pertanyaan;

        // Perulangan (while) untuk menampilkan dan memproses menu
        while (remenu) {
            System.out.println("\n=========================================================");
            System.out.println("|\t\t   M E N U   U T A M A                  |");
            System.out.println("=========================================================");
            System.out.println("|    1.\tLihat Stok Barang (Read Barang)                 |");
            System.out.println("|    2.\tTambah Data Barang Baru (Create Barang)         |");
            System.out.println("|    3.\tUbah Data Barang (Update Barang)                | ");
            System.out.println("|    4.\tTransaksi Barang (Delete Barang)                |");
            System.out.println("|    5.\tKeluar Dari Program                             |");
            System.out.println("=========================================================");
            System.out.print("Masukkan Pilihan Menu : ");
            pilih = input.nextInt();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            input.nextLine();

            // Objek-objek untuk proses terkait menu
            Barang barang = new Barang(1); 
            Stock barangin = new Stock(); 
            Transaksi transaksi = new Transaksi(); 

            // Percabangan switch case untuk memproses pilihan atau opsi
            switch (pilih) {
                case 1:
                    barang.tampil();
                    ulangi = true;
                    break;

                case 2:
                    barangin.simpan();
                    ulangi = true;
                    break;

                case 3:
                    barang.ubah();
                    ulangi = true;
                    break;

                case 4:
                    transaksi.hapus();
                    ulangi = true;
                    break;

                case 5:
                    ulangi = false;
                    remenu = false;
                    break;

                default:
                    System.out.println("Inv");
                    break;
            }

            // Perulangan while untuk menanyakan apakah pengguna ingin kembali ke menu utama atau tidak
            while (ulangi) {
                System.out.print("\n Balik ke Menu Utama ? (ya/tidak)  : ");
                pertanyaan = input.nextLine();
                System.out.println("`````````````````````````````````````````````````````````");

                // Percabangan if-else untuk pilihan ya/tidak dan method string
                if (pertanyaan.equalsIgnoreCase("tidak")) {
                    remenu = false;
                    ulangi = false;
                } else if (pertanyaan.equalsIgnoreCase("ya")) {
                    remenu = true;
                    ulangi = false;
                } else {
                    System.out.println("Invalid! inputan berupa 'ya' atau 'tidak' ");
                }
            }
        }
        System.out.println("\n\n\t   ~ P R O G R A M   S E L E S A I ~ \n");
    }
}
