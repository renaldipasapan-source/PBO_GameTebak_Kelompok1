import java.util.ArrayList;

public class leaderboard {
    private static class SkorEntry {
        String nama;
        int skor;

        SkorEntry(String nama, int skor) {
            this.nama = nama;
            this.skor = skor;
        }
    }

    private final ArrayList<SkorEntry> listSkorAngka = new ArrayList<>();
    private final ArrayList<SkorEntry> listSkorAbjad = new ArrayList<>();

    public void tambahSkorAngka(String nama, int skor) {
        listSkorAngka.add(new SkorEntry(nama, skor));
        listSkorAngka.sort((a, b) -> Integer.compare(b.skor, a.skor));
    }

    public void tambahSkorAbjad(String nama, int skor) {
        listSkorAbjad.add(new SkorEntry(nama, skor));
        listSkorAbjad.sort((a, b) -> Integer.compare(b.skor, a.skor));
    }

    public void tampilkanSkorAngka() {
        if (listSkorAngka.isEmpty()) {
            System.out.println("Belum ada skor Tebak Angka tercatat.");
            return;
        }
        for (int i = 0; i < listSkorAngka.size(); i++) {
            SkorEntry e = listSkorAngka.get(i);
            System.out.println((i + 1) + ". " + e.nama + " - " + e.skor + " Poin");
        }
    }

    public void tampilkanSkorAbjad() {
        if (listSkorAbjad.isEmpty()) {
            System.out.println("Belum ada skor Tebak Abjad tercatat.");
            return;
        }
        for (int i = 0; i < listSkorAbjad.size(); i++) {
            SkorEntry e = listSkorAbjad.get(i);
            System.out.println((i + 1) + ". " + e.nama + " - " + e.skor + " Poin");
        }
    }
}