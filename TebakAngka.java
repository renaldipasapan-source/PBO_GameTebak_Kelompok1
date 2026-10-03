import java.util.Random;
import java.util.Scanner;

/* 
 * LABEL [MATERI 2 - SUBCLASS & GENERALISASI]:
 * TebakAngka adalah Subclass yang mewarisi sifat dari Superclass GameTebak ('extends GameTebak').
 *
 * LABEL [MATERI 3 - UPCASTING RELATIONSHIP]:
 * Karena TebakAngka 'is-a' GameTebak, objek TebakAngka dapat di-upcast ke tipe GameTebak.
 */
public class TebakAngka extends GameTebak {
    /* LABEL [MATERI 1 - DATA HIDING]: Atribut private khusus untuk tebak angka */
    private int angkaRahasia;

    public TebakAngka(int batasBawah, int batasAtas, int batasPercobaan) {
        super(batasPercobaan); // MATERI 2: Memanggil constructor milik Superclass
        setBatasTebakan(batasBawah, batasAtas);
        generateAngkaRahasia();
    }

    /* LABEL [MATERI 3 - OVERRIDING]: Validasi tebakan khusus logika angka */
    public boolean validasiTebakan(int tebakan) {
        return validasiBatasTebakan(tebakan);
    }

    /* 
     * LABEL [MATERI 3 - OVERRIDING METHOD]: 
     * Menimpa method hitungSkor() milik Superclass GameTebak untuk membuat 
     * perhitungan skor yang khusus dan spesifik bagi game TebakAngka.
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

    private void generateAngkaRahasia() {
        Random random = new Random();
        angkaRahasia = random.nextInt(batasAtas - batasBawah + 1) + batasBawah;
    }

    public String cekTebakan(int tebakan) {
        if (!validasiTebakan(tebakan)) {
            return "Tebakan tidak valid";
        }

        tambahPercobaan();

        if (tebakan == angkaRahasia) {
            tandaiMenang();
            return "Selamat! Anda menebak angka rahasia!";
        } 
        else if (getJumlahPercobaan() >= getBatasPercobaan()){
            return "Maaf, Anda telah kehabisan percobaan. Angka rahasia adalah: " + angkaRahasia;
        } 
        else if (tebakan < angkaRahasia) {
            return "Tebakan Anda terlalu rendah.";
        } 
        else {
            return "Tebakan Anda terlalu tinggi.";
        }
    }

    public static void mulaiPermainan(Scanner in, leaderboard SKOR) {
        System.out.println("\n=== GAME TEBAK ANGKA ===");

        System.out.print("Masukkan batas bawah: ");
        int batasBawah = bacaInt(in);

        System.out.print("Masukkan batas atas: ");
        int batasAtas = bacaInt(in);

        while (batasAtas <= batasBawah) {
            System.out.println("Batas atas harus lebih besar dari batas bawah.");
            System.out.print("Masukkan batas atas lagi: ");
            batasAtas = bacaInt(in);
        }

        System.out.println("Pilih banyak percobaan: 3x, 5x, atau 7x");
        int batasPercobaan = pilihPercobaan(in);

        TebakAngka game = new TebakAngka(batasBawah, batasAtas, batasPercobaan);

        System.out.println("\nGame dimulai!");
        System.out.println("Tebak angka dari " + batasBawah + " sampai " + batasAtas + ".");
        System.out.println("Anda punya " + game.getBatasPercobaan() + " kali percobaan.\n");

        while (!game.isGameSelesai()) {
            System.out.print("Masukkan tebakan Anda: ");
            int tebakan = bacaInt(in);

            /* 
             * LABEL [MATERI 3 - DYNAMIC BINDING]: 
             * Pemanggilan method cekTebakan() ini diputuskan secara dinamis oleh Java 
             * saat RUNTIME berdasarkan objek asli di memori.
             */
            String hasil = game.cekTebakan(tebakan);
            System.out.println(hasil);

            if (game.isMenang()) {
                System.out.println("\nSelamat! Anda menang.\n");
                game.simpanSkor(in, SKOR);
                break;
            }

            if (game.isGameSelesai()) {
                System.out.println("\nPercobaan habis. Anda kalah.\n");
                game.simpanSkor(in, SKOR);
                break;
            }
        }

        TebakAngka.tampilkanLeaderboard(SKOR);
        opsiKembaliKeMenuUtama(in);
    }

    private void simpanSkor(Scanner in, leaderboard SKOR) {
        String nama = inputNamaPemain(in, "Tebak Angka");
        // MATERI 3: Memanggil method hitungSkor() hasil Overriding
        int skor = hitungSkor();
        SKOR.tambahSkorAngka(nama, skor);
        System.out.println("Skor Anda: " + skor);
    }

    public static void tampilkanLeaderboard(leaderboard SKOR) {
        System.out.println("=== Skor Tebak Angka ===");

        String[] namaPemainAngka = SKOR.getNamaPemainAngka();
        int[] skorPemainAngka = SKOR.getSkorPemainAngka();

        for (int i = 0; i < namaPemainAngka.length; i++) {
            System.out.printf("%d. %s - %d%n", i + 1, namaPemainAngka[i], skorPemainAngka[i]);
        }
    }
}