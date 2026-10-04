import java.util.Random;
import java.util.Scanner;

/* 
 * =========================================================
 * [MATERI 2 - INHERITANCE / PEWARISAN]
 * Kata kunci 'extends GameTebak' menandakan bahwa TebakAbjad 
 * adalah subclass yang mewarisi atribut/method dari superclass GameTebak.
 * =========================================================
 */
public class TebakAbjad extends GameTebak {

    /* 
     * =========================================================
     * [MATERI 1 - ENCAPSULATION & DATA HIDING]
     * Variabel 'abjadRahasia' diset 'private' agar tidak bisa diakses 
     * atau diubah secara langsung dari luar kelas ini.
     * =========================================================
     */
    private char abjadRahasia;

    public TebakAbjad(char batasBawah, char batasAtas, int batasPercobaan) {
        super(batasPercobaan);
        validasiRangeAbjad(batasBawah, batasAtas);
        this.batasBawah = batasBawah;
        this.batasAtas = batasAtas;
        generateAbjadRahasia();
    }

    /* 
     * =========================================================
     * [MATERI 3 - POLYMORPHISM: METHOD OVERRIDING]
     * Menulis ulang method setBatasTebakan milik kelas induk.
     * =========================================================
     */
    @Override
    protected void setBatasTebakan(int batasBawah, int batasAtas) {
        this.batasBawah = batasBawah;
        this.batasAtas = batasAtas;
    }

    private void validasiRangeAbjad(char batasBawah, char batasAtas) {
        if (batasBawah < 'a' || batasBawah > 'z' || batasAtas < 'a' || batasAtas > 'z') {
            throw new IllegalArgumentException("Batas huruf harus berada di antara a sampai z.");
        }
        if (batasBawah >= batasAtas) {
            throw new IllegalArgumentException("Batas bawah harus lebih kecil dari batas atas.");
        }
    }

    private void generateAbjadRahasia() {
        Random rand = new Random();
        int min = (int) batasBawah;
        int max = (int) batasAtas;
        this.abjadRahasia = (char) (rand.nextInt((max - min) + 1) + min);
    }

    public String tebakAbjad(char tebakan) {
        tebakan = Character.toLowerCase(tebakan);

        if (tebakan < batasBawah || tebakan > batasAtas) {
            return "Tebakan di luar rentang (" + (char) batasBawah + " - " + (char) batasAtas + ")";
        }

        jumlahPercobaan++;

        if (tebakan == abjadRahasia) {
            menang = true;
            return "Selamat! Tebakan Anda benar.";
        } else if (jumlahPercobaan >= batasPercobaan) {
            return "Maaf, kesempatan Anda habis. Abjad rahasia adalah: " + abjadRahasia;
        } else if (tebakan > abjadRahasia) {
            return "Tebakan Anda Terlalu Tinggi.";
        } else {
            return "Tebakan Anda Terlalu Rendah.";
        }
    }

    /* 
     * =========================================================
     * [MATERI 3 - POLYMORPHISM: METHOD OVERRIDING]
     * Menulis ulang method hitungSkor() milik GameTebak untuk kalkulasi 
     * skor spesifik permainan Tebak Abjad.
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
        System.out.println("\n=== PERMAINAN TEBAK ABJAD ===");
        System.out.print("Masukkan Batas Bawah Huruf (a-z): ");
        char bb = in.nextLine().trim().toLowerCase().charAt(0);
        System.out.print("Masukkan Batas Atas Huruf (a-z): ");
        char ba = in.nextLine().trim().toLowerCase().charAt(0);
        System.out.print("Masukkan Batas Percobaan (3/5/7): ");
        int bp = bacaInt(in);

        TebakAbjad game = new TebakAbjad(bb, ba, bp);

        while (!game.isGameSelesai()) {
            System.out.print("Masukkan tebakan huruf (" + (char) game.batasBawah + " - " + (char) game.batasAtas + "): ");
            String input = in.nextLine().trim();
            if (input.isEmpty()) continue;
            
            char tebakan = input.charAt(0);
            String hasil = game.tebakAbjad(tebakan);
            System.out.println(hasil);
        }

        /* 
         * =========================================================
         * [MATERI 3 - POLYMORPHISM: EXPLICIT UPCASTING & DYNAMIC BINDING]
         * 1. Explicit Upcasting: Mengubah tipe referensi dari Subclass (TebakAbjad) 
         *    menjadi Superclass (GameTebak).
         * 2. Dynamic Binding: Pemanggilan gameUp.hitungSkor() secara otomatis 
         *    menjalankan fungsi hitungSkor() milik TebakAbjad saat runtime.
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
        skor.tambahSkorAbjad(nama, skorAkhir);
        System.out.println("Skor berhasil disimpan!");
    }

    public static void tampilkanLeaderboard(leaderboard skor) {
        System.out.println("\n=== LEADERBOARD TEBAK ABJAD ===");
        skor.tampilkanSkorAbjad();
    }
}