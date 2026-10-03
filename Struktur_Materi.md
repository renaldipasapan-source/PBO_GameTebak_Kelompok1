# 📘 Struktur & Alur Integrasi Konsep Materi 3

> Tekan **`Ctrl + Shift + V`** di VS Code untuk melihat tampilan Markdown Preview.

Dokumentasi ini menjelaskan keterkaitan antara 4 pilar utama Materi 3 (**Overriding, Upcasting, Polymorphism, dan Dynamic Binding**) di dalam arsitektur proyek **Game Tebak**.

---

## 1. Peta Arsitektur Kelas & Konsep Materi 3

```text
                  ┌─────────────────────────────────────────┐
                  │          GameTebak (Superclass)         │
                  ├─────────────────────────────────────────┤
                  │ # batasBawah, batasAtas: int            │
                  │ # batasPercobaan, jumlahPercobaan: int  │
                  │ # menang: boolean                       │
                  ├─────────────────────────────────────────┤
                  │ + hitungSkor(): int  <─── [Base Method] │
                  └────────────────────▲────────────────────┘
                                       │
                ┌──────────────────────┴──────────────────────┐
                │                                             │
┌───────────────┴──────────────┐               ┌──────────────┴──────────────┐
│   TebakAngka (Subclass)      │               │   TebakAbjad (Subclass)     │
├──────────────────────────────┤               ├─────────────────────────────┤
│ - angkaRahasia: int          │               │ - abjadRahasia: char        │
├──────────────────────────────┤               ├─────────────────────────────┤
│ + hitungSkor(): int [@Over]  │               │ + hitungSkor(): int [@Over] │
│ + validasiTebakan(): boolean │               │ + validasiTebakan(): boolean│
└──────────────────────────────┘               └─────────────────────────────┘



2. Alur Eksekusi Antar Modul Anggota
Setiap anggota memiliki peran yang saling terhubung dalam alur eksekusi program:

[User Memilih Game di Menu] ──► mainmenu.java (Reza - Dynamic Binding)
                                       │
                                       ▼
                     [Inisialisasi & Assignment Objek]
                                       │
                                       ├──► TebakAngka.java (Jonathan - Overriding Angka)
                                       └──► TebakAbjad.java (Rafli - Overriding Abjad)
                                       │
                                       ▼
                     [Upcasting Reference Variable] ──► Renaldi (Upcasting)
                                       │
                                       ▼
                   [Eksekusi Polimorfik & Simpan Skor] ──► Markwell (Polymorphism)
                                       │
                                       ▼
                         [Array Data Leaderboard] ──► leaderboard.java



3. Ringkasan Tugas & Hasil Kontribusi Anggota

| Anggota | Sub-Materi 3 | File Utama | Hasil Output / Kontribusi |
|---|---|---|---|
| **Renaldi Pasapan** | Upcasting | `GameTebak.java` / Reference | Memungkinkan referensi `GameTebak` menampung objek `TebakAngka` dan `TebakAbjad`[cite: 5, 6] |
| **Jonathan Immanuel I.** | Overriding | `TebakAngka.java` | Menimpa kalkulasi skor & validasi tebakan khusus angka (`int`)[cite: 6] |
| **Rafli Gio Manulang** | Overriding | `TebakAbjad.java` | Menimpa kalkulasi skor & validasi tebakan khusus abjad (`char`)[cite: 5] |
| **Markwell Gilang A.** | Polymorphism | `GameTebak.java` / Subclasses | Memungkinkan pemanggilan nama method seragam untuk beragam bentuk aksi[cite: 5, 6] |
| **Reza Wijaya** | Dynamic Binding | `mainmenu.java` | Penentuan eksekusi logika game secara otomatis saat runtime oleh JVM[cite: 4, 5, 6] |

### 📂 Struktur File :

```text
PBO_GameTebak_Kelompok1/
│
├── GameTebak.java
├── TebakAngka.java
├── TebakAbjad.java
├── leaderboard.java
├── mainmenu.java
│
├── README.md                            <-- (Dokumentasi Utama Project)
├── Struktur_Materi.md                   <-- (Baru: Peta Arsitektur & Tugas Anggota)
├── Struktur_Overriding_Polymorphism.md  <-- (Baru: Bedah Overriding & Polymorphism)
└── Struktur_Upcasting_DynamicBinding.md <-- (Baru: Bedah Upcasting & Dynamic Binding)