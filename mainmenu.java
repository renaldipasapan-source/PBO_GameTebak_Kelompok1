import java.util.InputMismatchException;
import java.util.Scanner;

public class mainmenu {

    /* 
     * =========================================================
     * [MATERI 1 - ENCAPSULATION & DATA HIDING]
     * Objek 'SKOR' diset 'private static final' agar instance 
     * leaderboard terproteksi dan bertahan selama aplikasi berjalan.
     * =========================================================
     */
    private static final leaderboard SKOR = new leaderboard();

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int pilihan;

            do {
                tampilkanMenuUtama();
                pilihan = pilihMenu(in, 1, 4);
                
                switch (pilihan) {
                    case 1 -> TebakAngka.mulaiPermainan(in, SKOR);
                    case 2 -> TebakAbjad.mulaiPermainan(in, SKOR);
                    case 3 -> menuSkor(in);
                    case 4 -> System.out.println("\nTerima kasih telah bermain!\n");
                }
            } 
            while (pilihan != 4);
        }
    }

    /* 
     * =========================================================
     * [MATERI 1 - ENCAPSULATION & MODULARITAS]
     * Helper method diset 'private' agar hanya bisa digunakan 
     * secara internal di dalam kelas mainmenu saja.
     * =========================================================
     */
    private static int pilihMenu(Scanner in, int batasBawah, int batasAtas) {
        while (true) {
            try {
                int pilihan = in.nextInt();
                in.nextLine();

                if (pilihan >= batasBawah && pilihan <= batasAtas) {
                    return pilihan;
                }

                System.out.println("Pilihan tidak valid. Silakan pilih angka antara " + batasBawah + " sampai " + batasAtas + ".");
                System.out.print("Masukkan lagi: ");
            } 
            catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
                in.nextLine();
                System.out.print("Masukkan lagi: ");
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n========================================");
        System.out.println("         MENU UTAMA GAME");
        System.out.println("========================================");
        System.out.println("1. Tebak Angka");
        System.out.println("2. Tebak Abjad");
        System.out.println("3. Skor");
        System.out.println("4. Keluar");
        System.out.println("========================================");
    }

    private static void menuSkor(Scanner in) {
        System.out.println("\n=== MENU SKOR ===");
        System.out.println("1. Lihat leaderboard Tebak Angka");
        System.out.println("2. Lihat leaderboard Tebak Abjad");
        System.out.println("3. Kembali ke menu utama");

        int pilihan = pilihMenu(in, 1, 3);

        if (pilihan == 1) {
            TebakAngka.tampilkanLeaderboard(SKOR);
        } 
        else if (pilihan == 2) {
            TebakAbjad.tampilkanLeaderboard(SKOR);
        }
    }
}