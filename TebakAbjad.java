import java.util.Random;
import java.util.Scanner;

/* 
 * LABEL [MATERI 2 - SUBCLASS & GENERALISATION]:
 * TebakAbjad mewarisi sifat dan atribut dari Superclass GameTebak.
 *
 * LABEL [MATERI 3 - POLYMORPHISM & UPCASTING]:
 * Memungkinkan objek TebakAbjad diperlakukan sebagai bentuk umum GameTebak.
 */
public class TebakAbjad extends GameTebak {
    /* LABEL [MATERI 1 - DATA HIDING]: Atribut private khusus abjad rahasia */
    private char abjadRahasia;

    public TebakAbjad(char batasBawah, char batasAtas, int batasPercobaan) {
        super(batasPercobaan); // MATERI 2: Reuse constructor induk
        validasiRangeAbjad(batasBawah, batasAtas);
        setBatasTebakan(batasBawah, batasAtas);
        generateAbjadRahasia();
    }

    /* 
     * LABEL [MATERI 3 - OVERRIDING METHOD]: 
     * Menimpa method hitungSkor() dari GameTebak. Jika di masa depan ingin 
     * mengubah rumus skor abjad, cukup di-override di sini tanpa merusak TebakAngka.
     */
    @Override
    public int hitungSkor() {
        if (!menang) {
            return 0;
        }

        final int maxSkor = 2500;
        int deduct = switch (batasPercobaan) {
            case 3 -> 100;
            case 5 -> 200;
            case 7 -> 300;
            default -> throw new IllegalArgumentException("Batas percobaan harus 3, 5, atau 7.");
        };

        return Math.max(0, maxSkor - (deduct * (jumlahPercobaan - 1)));
    }

    private void generateAbjadRahasia() {
        Random random = new Random();
        abjadRahasia = (char) (random.nextInt(batasAtas - batasBawah + 1) + batasBawah);
    }

    /* LABEL [MATERI 1 - VALIDASI TEBAKAN]: Memastikan tebakan berupa huruf valid ('a'-'z') */
    private boolean validasiTebakan(char tebakan) {
        tebakan = Character.toLowerCase(tebakan);
        return tebakan >= batasBawah && tebakan <= batasAtas;
    }

    private void validasiRangeAbjad(char batasBawah, char batasAtas) {
        if (batasBawah < 'a' || batasBawah > 'z' || batasAtas < 'a' || batasAtas > 'z') {
            throw new IllegalArgumentException("Batas huruf harus berada di antara a sampai z.");
        }

        if (batasBawah >= batasAtas) {
            throw new IllegalArgumentException("Batas bawah harus lebih kecil dari batas atas.");
        }
    }

    private static char bacaHuruf(Scanner in) {
        while (true) {
            String inUser = in.next();

            if (!inUser.isEmpty()) {
                char huruf = Character.toLowerCase(inUser.charAt(0));

                if (huruf >= 'a' && huruf <= 'z') {
                    return huruf;
                }
            }

            System.out.println("input harus berupa huruf a sampai z. Coba lagi.");
            System.out.print("Masukkan lagi: ");
        }
    }

    public String tebakAbjad(char tebakan) {
        if (isGameSelesai()) {
            return "Permainan sudah selesai.";
        }

        if (!validasiTebakan(tebakan)) {
            return "Tebakan harus berupa huruf.";
        }

        tambahPercobaan();
        tebakan = Character.toLowerCase(tebakan);

        if (tebakan == abjadRahasia) {
            tandaiMenang();
            return "Selamat! Tebakan Anda benar.";
        } 
        else if (getJumlahPercobaan() >= getBatasPercobaan()) {
            return "Maaf, Anda telah kehabisan percobaan. Abjad rahasia adalah: " + abjadRahasia;
        } 
        else if (tebakan > abjadRahasia){
            return "Tebakan Anda Terlalu Tinggi.";
        }
        else {
            return "Tebakan Anda Terlalu Rendah.";
        }
    }

    public static void mulaiPermainan(Scanner in, leaderboard SKOR) {
        System.out.println("\n=== GAME TEBAK ABJAD ===");

        System.out.print("Masukkan batas bawah huruf: ");
        char batasBawah = bacaHuruf(in);

        System.out.print("Masukkan batas atas huruf: ");
        char batasAtas = bacaHuruf(in);

        while (batasAtas <= batasBawah) {
            System.out.println("Batas atas harus lebih besar dari batas bawah.");
            System.out.print("Masukkan batas atas huruf lagi: ");
            batasAtas = bacaHuruf(in);
        }

        System.out.println("Pilih banyak percobaan: 3x, 5x, atau 7x");
        int batasPercobaan = pilihPercobaan(in);

        TebakAbjad game = new TebakAbjad(batasBawah, batasAtas, batasPercobaan);

        System.out.println("\nGame dimulai!");
        System.out.println("Tebak huruf dari " + batasBawah + " sampai " + batasAtas + ".");
        System.out.println("Anda punya " + game.getBatasPercobaan() + " kali percobaan.\n");

        while (!game.isGameSelesai()) {
            System.out.print("Masukkan tebakan huruf Anda: ");
            char tebakan = bacaHuruf(in);

            /* 
             * LABEL [MATERI 3 - DYNAMIC BINDING]: 
             * Pemanggilan tebakAbjad() diselesaikan secara dinamis saat runtime oleh JVM.
             */
            String hasil = game.tebakAbjad(tebakan);
            System.out.println(hasil);

            if (game.isGameSelesai()) {
                System.out.println("\nPermainan selesai.\n");
                game.simpanSkor(in, SKOR);
                break;
            }
        }

        TebakAbjad.tampilkanLeaderboard(SKOR);
        opsiKembaliKeMenuUtama(in);
    }

    private void simpanSkor(Scanner in, leaderboard SKOR) {
        String nama = inputNamaPemain(in, "Tebak Abjad");
        // MATERI 3: Memanggil skor dari hasil Overriding kelas ini
        int skor = hitungSkor();
        SKOR.tambahSkorAbjad(nama, skor);
        System.out.println("Skor Anda: " + skor);
    }

    public static void tampilkanLeaderboard(leaderboard SKOR) {
        System.out.println("=== Skor Tebak Abjad ===");

        String[] namaPemainAbjad = SKOR.getNamaPemainAbjad();
        int[] skorPemainAbjad = SKOR.getSkorPemainAbjad();

        for (int i = 0; i < namaPemainAbjad.length; i++) {
            System.out.printf("%d. %s - %d%n", i + 1, namaPemainAbjad[i], skorPemainAbjad[i]);
        }
    }
}