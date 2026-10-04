import java.util.Random;
import java.util.Scanner;

/* 
 * =========================================================
 * [MATERI 2 - INHERITANCE / PEWARISAN]
 * Kata kunci 'extends GameTebak' menandakan bahwa TebakAngka 
 * adalah subclass yang mewarisi atribut/method dari superclass GameTebak.
 * =========================================================
 */
public class TebakAngka extends GameTebak {

    /* 
     * =========================================================
     * [MATERI 1 - ENCAPSULATION & DATA HIDING]
     * Variabel 'angkaRahasia' diset 'private' agar tidak bisa diakses 
     * atau diubah secara langsung dari luar kelas ini.
     * =========================================================
     */
    private int angkaRahasia;

    public TebakAngka(int batasBawah, int batasAtas, int batasPercobaan) {
        super(batasPercobaan);
        setBatasTebakan(batasBawah, batasAtas);
        generateAngkaRahasia();
    }

    /* 
     * =========================================================
     * [MATERI 3 - POLYMORPHISM: METHOD OVERRIDING]
     * Menulis ulang method setBatasTebakan milik kelas induk dengan validasi khusus.
     * =========================================================
     */
    @Override
    protected final void setBatasTebakan(int batasBawah, int batasAtas) {
        if (batasBawah >= batasAtas) {
            throw new IllegalArgumentException("Batas bawah harus lebih kecil dari batas atas.");
        }
        this.batasBawah = batasBawah;
        this.batasAtas = batasAtas;
    }

    private void generateAngkaRahasia() {
        Random rand = new Random();
        this.angkaRahasia = rand.nextInt((batasAtas - batasBawah) + 1) + batasBawah;
    }

    public boolean validasiTebakan(int tebakan) {
        return tebakan >= batasBawah && tebakan <= batasAtas;
    }

    public String cekTebakan(int tebakan) {
        if (!validasiTebakan(tebakan)) {
            return "Tebakan tidak valid (harus antara " + batasBawah + " - " + batasAtas + ")";
        }

        jumlahPercobaan++;

        if (tebakan == angkaRahasia) {
            menang = true;
            return "Selamat! Anda menebak angka rahasia!";
        } else if (jumlahPercobaan >= batasPercobaan) {
            return "Maaf, Anda telah kehabisan percobaan. Angka rahasia adalah: " + angkaRahasia;
        } else if (tebakan < angkaRahasia) {
            return "Tebakan Anda terlalu rendah.";
        } else {
            return "Tebakan Anda terlalu tinggi.";
        }
    }

    /* 
     * =========================================================
     * [MATERI 3 - POLYMORPHISM: METHOD OVERRIDING]
     * Menulis ulang method hitungSkor() milik GameTebak untuk kalkulasi 
     * skor spesifik permainan Tebak Angka.
     * =========================================================
     */
    @Override
    public int hitungSkor() {
        if (!menang) return 0;

        final int maxSkor = 2500;
        int deduct = switch (batasPercobaan) {
            case 3 -> 100;
            case 5 -> 200;
            case 7 -> 300;
            default -> 100;
        };

        return Math.max(0, maxSkor - (deduct * (jumlahPercobaan - 1)));
    }

    public static void mulaiPermainan(Scanner in, leaderboard skor) {
        System.out.println("\n=== PERMAINAN TEBAK ANGKA ===");
        System.out.print("Masukkan Batas Bawah: ");
        int bb = bacaInt(in);
        System.out.print("Masukkan Batas Atas: ");
        int ba = bacaInt(in);
        System.out.print("Masukkan Batas Percobaan (3/5/7): ");
        int bp = bacaInt(in);

        TebakAngka game = new TebakAngka(bb, ba, bp);

        while (!game.isGameSelesai()) {
            System.out.print("Masukkan tebakan Anda (" + game.batasBawah + " - " + game.batasAtas + "): ");
            int tebakan = bacaInt(in);
            String hasil = game.cekTebakan(tebakan);
            System.out.println(hasil);
        }

        /* 
         * =========================================================
         * [MATERI 3 - POLYMORPHISM: EXPLICIT UPCASTING & DYNAMIC BINDING]
         * 1. Explicit Upcasting: Mengubah tipe referensi dari Subclass (TebakAngka) 
         *    menjadi Superclass (GameTebak).
         * 2. Dynamic Binding: Pemanggilan gameUp.hitungSkor() secara otomatis 
         *    menjalankan fungsi hitungSkor() milik TebakAngka saat runtime.
         * =========================================================
         */
        GameTebak gameUp = (GameTebak) game;

        System.out.println("\n========================================");
        System.out.println("     RINGKASAN AKHIR (UPCASTING)");
        System.out.println("========================================");
        System.out.println("Status Selesai : " + (gameUp.isGameSelesai() ? "Selesai" : "Belum Selesai"));
        System.out.println("Total Percobaan: " + gameUp.getJumlahPercobaan() + " kali");
        System.out.println("Skor Akhir     : " + gameUp.hitungSkor());
        System.out.println("========================================\n");

        if (game.menang) {
            simpanSkor(in, skor, game.hitungSkor());
        }
    }

    private static void simpanSkor(Scanner in, leaderboard skor, int skorAkhir) {
        System.out.print("Masukkan Nama Anda untuk Leaderboard: ");
        String nama = in.nextLine();
        skor.tambahSkorAngka(nama, skorAkhir);
        System.out.println("Skor berhasil disimpan!");
    }

    public static void tampilkanLeaderboard(leaderboard skor) {
        System.out.println("\n=== LEADERBOARD TEBAK ANGKA ===");
        skor.tampilkanSkorAngka();
    }
}