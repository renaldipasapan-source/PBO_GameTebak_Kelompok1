/*
 * LABEL [MATERI 1 - DATA HIDING & ENKAPSULASI]:
 * Kelas leaderboard sekarang murni hanya bertugas menyimpan data skor (private arrays).
 */
public class leaderboard {
    private String[] namaPemainAngka;
    private int[] skorPemainAngka;
    private String[] namaPemainAbjad;
    private int[] skorPemainAbjad;

    public leaderboard() {
        this.namaPemainAngka = new String[0];
        this.skorPemainAngka = new int[0];
        this.namaPemainAbjad = new String[0];
        this.skorPemainAbjad = new int[0];
    }

    private static String[] tambahElementString(String[] array, String element) {
        String[] tempArray = new String[array.length + 1];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        tempArray[array.length] = element;
        return tempArray;
    }

    private static int[] tambahElementInt(int[] array, int element) {
        int[] tempArray = new int[array.length + 1];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        tempArray[array.length] = element;
        return tempArray;
    }

    /* 
     * LABEL [MATERI 1 - SETTER METHOD]: 
     * Menerima nilai skor yang sudah dihitung oleh masing-masing kelas game.
     */
    public void tambahSkorAngka(String namaPemain, int skor) {
        namaPemainAngka = tambahElementString(namaPemainAngka, namaPemain);
        skorPemainAngka = tambahElementInt(skorPemainAngka, skor);
        urutkanSkorAngka(false);
    }

    public void tambahSkorAbjad(String namaPemain, int skor) {
        namaPemainAbjad = tambahElementString(namaPemainAbjad, namaPemain);
        skorPemainAbjad = tambahElementInt(skorPemainAbjad, skor);
        urutkanSkorAbjad(false);
    }

    private static void urutkanSkor(String[] namaPemain, int[] skorPemain, boolean ascending) {
        for (int i = 0; i < skorPemain.length - 1; i++) {
            for (int j = 0; j < skorPemain.length - 1 - i; j++) {
                boolean shouldSwap = ascending ? (skorPemain[j] > skorPemain[j + 1]) : (skorPemain[j] < skorPemain[j + 1]);

                if (shouldSwap) {
                    int tempSkor = skorPemain[j];
                    skorPemain[j] = skorPemain[j + 1];
                    skorPemain[j + 1] = tempSkor;
                    
                    String tempNama = namaPemain[j];
                    namaPemain[j] = namaPemain[j + 1];
                    namaPemain[j + 1] = tempNama;
                }
            }
        }
    }

    public void urutkanSkorAngka() { 
        urutkanSkor(namaPemainAngka, skorPemainAngka, false); 
    }

    public void urutkanSkorAngka(boolean ascending) { 
        urutkanSkor(namaPemainAngka, skorPemainAngka, ascending); 
    }

    public void urutkanSkorAbjad() { 
        urutkanSkor(namaPemainAbjad, skorPemainAbjad, false); 
    }

    public void urutkanSkorAbjad(boolean ascending) { 
        urutkanSkor(namaPemainAbjad, skorPemainAbjad, ascending); 
    }

    /* 
     * LABEL [MATERI 1 - GETTER METHOD & DATA HIDING]: 
     * Mengembalikan copy (.clone()) agar array privat tidak bisa dimanipulasi dari luar.
     */
    public String[] getNamaPemainAngka() { 
        return namaPemainAngka.clone(); 
    }

    public int[] getSkorPemainAngka() { 
        return skorPemainAngka.clone(); 
    }

    public String[] getNamaPemainAbjad() { 
        return namaPemainAbjad.clone(); 
    }

    public int[] getSkorPemainAbjad() { 
        return skorPemainAbjad.clone(); 
    }
}