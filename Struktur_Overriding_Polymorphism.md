# 🔄 Struktur Overriding & Polymorphism (Jonathan, Rafli, & Markwell)

> Tekan **`Ctrl + Shift + V`** di VS Code untuk melihat tampilan Markdown Preview.

Dokumentasi ini menjelaskan secara rinci penerapan **Method Overriding** dan **Polymorphism** pada proyek Game Tebak.

---

## 1. Konsep Method Overriding

Method Overriding terjadi ketika *subclass* menimpa/menulis ulang implementasi method yang diwarisi dari *superclass* dengan nama, parameter, dan tipe kembalian yang sama persis.

### Struktur Pohon Overriding Method `hitungSkor()`:

```text
GameTebak.java (Superclass)
│
└── public int hitungSkor() { return 0; }  <-- [Method Base Dasar]
      │
      ├── TebakAngka.java (Jonathan)
      │   └── @Override
      │       public int hitungSkor() { ... }  <-- [Aturan Skor Spesifik Angka]
      │
      └── TebakAbjad.java (Rafli)
          └── @Override
              public int hitungSkor() { ... }  <-- [Aturan Skor Spesifik Abjad]
Langkah Pembuatan & Implementasi Kodingan:
Aturan Dasar di Induk (GameTebak.java): Menyiapkan method public int hitungSkor() sebagai blueprint.

Overriding di Class Tebak Angka (TebakAngka.java):

Java
@Override
public int hitungSkor() {
    if (!menang) return 0;
    final int maxSkor = 2500;
    int deduct = switch (batasPercobaan) {
        case 3 -> 100;
        case 5 -> 200;
        case 7 -> 300;
        default -> 0;
    };
    return Math.max(0, maxSkor - (deduct * (jumlahPercobaan - 1)));
}
Overriding di Class Tebak Abjad (TebakAbjad.java):
Sama-sama menimpa method hitungSkor(), sehingga jika kelak aturan skor abjad diubah, perubahan tidak akan merusak kelas TebakAngka.

2. Konsep Polymorphism (Markwell Gilang)
Polimorfisme (banyak bentuk) memungkinkan satu antarmuka/nama method yang sama dipanggil untuk mengeksekusi perilaku yang berbeda sesuai objeknya.

Alur Eksekusi Polimorfik:
Plaintext
Satu Perintah Method: game.hitungSkor()
        │
        ├──► Jika Objek = TebakAngka ──► Mengeksekusi Rumus Skor Angka
        │
        └──► Jika Objek = TebakAbjad ──► Mengeksekusi Rumus Skor Abjad
Hasil Output Program:
Plaintext
=== GAME TEBAK ANGKA ===
Tebak angka dari 1 sampai 10.
Masukkan tebakan Anda: 7
Selamat! Anda menebak angka rahasia!
Skor Anda: 2500   <-- Hasil Output Polimorfik TebakAngka

=== GAME TEBAK ABJAD ===
Tebak huruf dari a sampai z.
Masukkan tebakan huruf Anda: r
Selamat! Tebakan Anda benar.
Skor Anda: 2400   <-- Hasil Output Polimorfik TebakAbjad