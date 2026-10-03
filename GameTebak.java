import java.util.InputMismatchException;
import java.util.Scanner;

/* 
 * LABEL [MATERI 2 - SUPERCLASS]: 
 * GameTebak berperan sebagai Superclass (kelas induk) yang menampung atribut 
 * dan perilaku umum untuk di-reuse oleh subclass (TebakAngka & TebakAbjad).
 */
public class GameTebak {
    /* 
     * LABEL [MATERI 1 - ACCESS MODIFIER & DATA HIDING]: 
     * Menggunakan modifier 'protected' agar atribut aman dari akses luar (Encapsulation), 
     * tetapi tetap dapat diakses langsung oleh subclass-nya.
     */
    protected int batasBawah;
    protected int batasAtas;
    protected int batasPercobaan;
    protected int jumlahPercobaan;
    protected boolean menang;

    // CONSTRUCTOR SUPERCLASS
    protected GameTebak(int batasPercobaan) {
        this.jumlahPercobaan = 0;
        this.menang = false;
        
        // LABEL [MATERI 1 - VALIDASI DATA INI]: Memastikan batas percobaan valid saat inisialisasi
        if (!batasPercobaanValid(batasPercobaan)) {
            throw new IllegalArgumentException("Batas percobaan harus 3, 5, atau 7.");
        }
        this.batasPercobaan = batasPercobaan;
    }

    /* LABEL [MATERI 1 - VALIDASI INPUT]: Pengecekan logika bisnis angka percobaan */
    private static boolean batasPercobaanValid(int batasPercobaan) {
        return batasPercobaan == 3 || batasPercobaan == 5 || batasPercobaan == 7;
    }

    /* LABEL [MATERI 1 - GETTER METHOD]: Mengakses data tersembunyi (Encapsulation) secara aman */
    public int getJumlahPercobaan() {
        return jumlahPercobaan;
    }

    public int getBatasPercobaan() {
        return batasPercobaan;
    }

    public boolean isMenang() {
        return menang;
    }

    /* LABEL [MATERI 1 - SETTER METHOD & VALIDASI DATA]: Mengubah data secara terkontrol */
    public final void setBatasPercobaan(int batasPercobaan) {
        if (batasPercobaanValid(batasPercobaan)) {
            this.batasPercobaan = batasPercobaan;
        } else {
            throw new IllegalArgumentException("Batas percobaan harus 3, 5, atau 7.");
        }
    }

    protected void setBatasTebakan(int batasBawah, int batasAtas) {
        if (batasBawah >= batasAtas) {
            throw new IllegalArgumentException("Batas bawah harus lebih kecil dari batas atas.");
        }
        this.batasBawah = batasBawah;
        this.batasAtas = batasAtas;
    }

    protected boolean validasiBatasTebakan(int tebakan) {
        return tebakan >= batasBawah && tebakan <= batasAtas;
    }

    protected void tambahPercobaan() {
        jumlahPercobaan++;
    }

    protected void tandaiMenang() {
        menang = true;
    }

    /* LABEL [MATERI 2 - REUSE / GENERALISASI METHOD]: Logika status tamat dipakai bersama oleh subclass */
    public boolean isGameSelesai() {
        return menang || jumlahPercobaan >= batasPercobaan;
    }

    /* 
     * LABEL [MATERI 3 - POLYMORPHISM BASE METHOD]:
     * Method hitungSkor dasar di superclass. Method ini akan di-OVERRIDE 
     * oleh masing-masing subclass untuk memberikan aturan skor yang spesifik.
     */
    public int hitungSkor() {
        return 0;
    }

    /* LABEL [MATERI 1 - VALIDASI SCANNER / INPUT MISMATCH]: Penanganan input salah pada Scanner */
    protected static int bacaInt(Scanner in) {
        while (true) {
            try {
                return in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("input harus berupa angka. Coba lagi.");
                in.nextLine();
                System.out.print("Masukkan lagi: ");
            }
        }
    }

    protected static int pilihPercobaan(Scanner in) {
        while (true) {
            int pilihan = bacaInt(in);
            if (pilihan == 3 || pilihan == 5 || pilihan == 7) {
                return pilihan;
            }
            System.out.println("Batas percobaan hanya boleh 3, 5, atau 7.");
            System.out.print("Masukkan lagi: ");
        }
    }

    protected static String inputNamaPemain(Scanner in, String namaGame) {
        System.out.print("Masukkan nama pemain untuk " + namaGame + ": ");
        in.nextLine();
        String nama = in.nextLine().trim();
        if (nama.isEmpty()) {
            nama = "Pemain";
        }
        return nama;
    }

    protected static void opsiKembaliKeMenuUtama(Scanner in) {
        System.out.println("\n1. Kembali ke menu utama");
        System.out.println("0. Keluar");

        int pilihan = bacaInt(in);

        while (pilihan != 0 && pilihan != 1) {
            System.out.println("Pilihan tidak valid. Silakan pilih 0 atau 1.");
            System.out.print("Masukkan lagi: ");
            pilihan = bacaInt(in);
        }

        if (pilihan == 0) {
            System.out.println("\nTerima kasih telah bermain!\n");
            System.exit(0);
        }
    }
}