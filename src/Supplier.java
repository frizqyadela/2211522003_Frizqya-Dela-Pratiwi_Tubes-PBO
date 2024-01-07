// Class Supplier
public class Supplier
{
    // Deklarasi variabel untuk atribut supplier
    String nama_sup;
    String noHP;
    String kode_sup;
    
    // Konstructor Supplier kosong
    public Supplier()
    {

    }

    //Konstruktor Supplier banyak parameter
    public Supplier(String nama_sup, String kode_sup,String noHP) 
    {
        this.nama_sup = nama_sup;
        this.kode_sup = kode_sup;
        this.noHP = noHP;
        System.out.println("\n    "+this.nama_sup+" merupakan supplier baru");
    }
    
    // Method kosong
    public void methodKosong()
    {

    }
}