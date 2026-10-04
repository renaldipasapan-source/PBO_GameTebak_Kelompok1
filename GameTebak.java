import java.util.Scanner;

public class GameTebak {

    /* 
     * =========================================================
     * [MATERI 1 - ENCAPSULATION & DATA HIDING]
     * Variabel diset 'protected' agar disembunyikan dari luar, 
     * tetapi tetap dapat diakses langsung oleh kelas anak (subclass).
     * =========================================================
     */
    protected int batasBawah;
    protected int batasAtas;
    protected int batasPercobaan;
    protected int jumlahPercobaan;
    protected boolean menang;

    public GameTebak(int batasPercobaan) {
        this.batasPercobaan = batasPercobaan;
        this.jumlahPercobaan = 0;
        this.menang = false;
    }

    protected void setBatasTebakan(int batasBawah, int batasAtas) {
        this.batasBawah = batasBawah;
        this.batasAtas = batasAtas;
    }

    /* 
     * Implementasi default method hitungSkor() pada kelas induk.
     */
    public int hitungSkor() {
        return 0;
    }

    public boolean isGameSelesai() {
        return menang || jumlahPercobaan >= batasPercobaan;
    }

    public int getBatasPercobaan() {
        return batasPercobaan;
    }

    public int getJumlahPercobaan() {
        return jumlahPercobaan;
    }

    public boolean isMenang() {
        return menang;
    }

    public static int bacaInt(Scanner in) {
        while (true) {
            try {
                int val = in.nextInt();
                in.nextLine();
                return val;
            } catch (Exception e) {
                System.out.print("Input harus berupa angka. Masukkan lagi: ");
                in.nextLine();
            }
        }
    }
}