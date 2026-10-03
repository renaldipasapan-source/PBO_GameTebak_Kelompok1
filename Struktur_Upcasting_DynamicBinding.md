# ⚡ Struktur Upcasting & Dynamic Binding (Renaldi & Reza)

> Tekan **`Ctrl + Shift + V`** di VS Code untuk melihat tampilan Markdown Preview.

Dokumentasi ini menjelaskan alur teknis penggunaan **Upcasting** dan mekanisme **Dynamic Binding** (Late Binding) saat aplikasi berjalan.

---

## 1. Konsep Upcasting (Renaldi Pasapan)

Upcasting adalah proses mengubah tipe referensi objek dari kelas anak (*subclass*) menjadi tipe kelas induknya (*superclass*). Di Java, proses ini berjalan otomatis (*implicit*) dan 100% aman (*type-safe*).

### Diagram Alur Upcasting:

```text
        ┌──────────────────────────────────────────────┐
        │            GameTebak (Superclass)            │
        └──────────────────────▲───────────────────────┘
                               │
               [Upcasting Implisit / Otomatis]
                               │
        ┌──────────────────────┴──────────────────────┐
        │                                             │
┌───────┴────────┐                           ┌────────┴───────┐
│   TebakAngka   │                           │   TebakAbjad   │
│   (Subclass)   │                           │   (Subclass)   │
└────────────────┘                           └────────────────┘
Langkah & Contoh Kodingan Upcasting:
Java
// 1. Inisialisasi Objek Subclass spesifik
TebakAngka gameAngka = new TebakAngka(1, 10, 3);

// 2. Upcasting ke tipe Superclass (GameTebak)
GameTebak gameRef = gameAngka; // Tipe variabel dinaikkan ke GameTebak

// 3. Objek kini dipandang sebagai GameTebak secara umum
System.out.println("Batas Percobaan: " + gameRef.getBatasPercobaan());
2. Konsep Dynamic Binding / Late Binding (Reza Wijaya)
Dynamic Binding adalah mekanisme Mesin Virtual Java (JVM) saat menentukan method hasil overriding mana yang harus dieksekusi pada saat program berjalan (runtime), bukan saat kompilasi (compile-time).

Alur Kerja Dynamic Binding di Menu Utama (mainmenu.java):
Plaintext
User Memilih Menu Game (Saat Application Running)
                       │
        ┌──────────────┴──────────────┐
        ▼                             ▼
Pilihan 1: Tebak Angka        Pilihan 2: Tebak Abjad
        │                             │
        ▼                             ▼
JVM mendeteksi objek          JVM mendeteksi objek 
asli di memori = TebakAngka   asli di memori = TebakAbjad
        │                             │
        ▼                             ▼
Mengeksekusi method           Mengeksekusi method 
cekTebakan() milik Angka      tebakAbjad() milik Abjad
Hasil Output Simulasi Runtime:
Plaintext
========================================
         MENU UTAMA GAME
========================================
1. Tebak Angka
2. Tebak Abjad
3. Skor
4. Keluar
========================================
Pilihan Anda: 1

[JVM Dynamic Binding Active]
--> Menjalankan modul TebakAngka secara otomatis di memori.
Masukkan tebakan Anda: 5
Tebakan Anda terlalu rendah.