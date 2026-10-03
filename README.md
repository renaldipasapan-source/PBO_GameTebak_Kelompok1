# 🎮 PBO Game Tebak (Materi 3: Polymorphism, Overriding, Dynamic Binding, & Upcasting)

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java)
![PBO Course](https://img.shields.io/badge/Course-PBO%20Kelompok%201-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

Project game interaktif berbasis **Java** untuk memenuhi tugas mata kuliah **Pemrograman Berorientasi Objek (PBO) - Kelompok 1**.

Proyek ini dirancang secara khusus untuk mendemonstrasikan penerapan **4 Pilar Utama Materi 3 PBO**:
- 🔄 **Method Overriding:** Menimpa method validasi tebakan & perhitungan skor spesifik pada subclass.
- ⬆️ **Upcasting:** Memperlakukan objek subclass (`TebakAngka` / `TebakAbjad`) sebagai tipe general superclass (`GameTebak`).
- 🎭 **Polymorphism:** Pemanggilan nama method yang seragam untuk menghasilkan respons perilaku dinamis yang berbeda.
- ⚡ **Dynamic Binding:** Penentuan eksekusi method game secara dinamis oleh JVM saat program berjalan (*runtime*).

---

## 👥 Pembagian Tugas Kelompok 1 (Fokus Materi 3)

| Kontributor | Sub-Materi 3 | Peran Modul & Kodingan | Branch Feature |
|---|---|---|---|
| **Renaldi Pasapan** *(Ketua)* | ⬆️ **Upcasting** | Mengatur variabel referensi `GameTebak` penampung objek subclass | `feature/upcasting` |
| **Jonathan Immanuel I.** | 🔄 **Overriding** | Overriding method `hitungSkor()` & `validasiTebakan()` di `TebakAngka.java` | `feature/overriding-angka` |
| **Rafli Gio Manulang** | 🔄 **Overriding** | Overriding method `hitungSkor()` & `validasiTebakan()` di `TebakAbjad.java` | `feature/overriding-abjad` |
| **Markwell Gilang A.** | 🎭 **Polymorphism** | Menerapkan fleksibilitas panggilan method polimorfik | `feature/polymorphism` |
| **Reza Wijaya** | ⚡ **Dynamic Binding** | Menangani eksekusi late-binding saat game running di `mainmenu.java` | `feature/dynamic-binding` |

> ⚠️ **RULES GIT:** Dilarang keras melakukan `git push` langsung ke branch `master`.
> Setiap anggota WAJIB mengerjakan fitur di branch-nya masing-masing!

---

# 📘 BEDAH ARSITEKTUR KONSEP MATERI 3

## 1. Structure Tree Integration

```text
Arsitektur Materi 3 (Game Tebak)
│
├── 1. Overriding (Metode Menimpa Perilaku Induk)
│   ├── GameTebak.java       ──► hitungSkor() [Method Base]
│   ├── TebakAngka.java      ──► hitungSkor() [@Override khusus Angka]
│   └── TebakAbjad.java      ──► hitungSkor() [@Override khusus Abjad]
│
├── 2. Upcasting (Pengangkatan Status Objek ke Superclass)
│   ├── Subclass Object      ──► new TebakAngka(...) / new TebakAbjad(...)
│   └── Reference Variable  ──► GameTebak game = ...
│
├── 3. Polymorphism (Satu Antarmuka, Banyak Bentuk)
│   └── Pemanggilan method seragam berbasis tipe superclass GameTebak
│
└── 4. Dynamic Binding (Pengecekan Method saat Runtime)
    └── JVM mengeksekusi logika validasi & skor sesuai objek aktif saat runtime

```

---

## 2. Diagram & Detail Penjelasan Konsep Materi 3

### A. Overriding Method (Jonathan & Rafli)

Method Overriding terjadi ketika *subclass* menimpa/menulis ulang implementasi method yang diwarisi dari *superclass* dengan nama, parameter, dan tipe kembalian yang sama persis.

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

```

---

### B. Upcasting (Renaldi Pasapan)

Upcasting adalah proses mengubah tipe referensi objek dari kelas anak (*subclass*) menjadi tipe kelas induknya (*superclass*). Di Java, proses ini berjalan otomatis (*implicit*) dan 100% aman (*type-safe*).

```text
      ┌──────────────────────────────────────────┐
      │          GameTebak (Superclass)          │
      └────────────────────▲─────────────────────┘
                           │
             [Upcasting Otomatis / Implicit]
                           │
        ┌──────────────────┴──────────────────┐
        │                                     │
┌───────┴────────┐                   ┌────────┴───────┐
│   TebakAngka   │                   │   TebakAbjad   │
│   (Subclass)   │                   │   (Subclass)   │
└────────────────┘                   └────────────────┘

```

**Contoh Kode Upcasting:**

```java
// Objek TebakAngka (Subclass) di-upcast ke referensi GameTebak (Superclass)
GameTebak gameTebakAngka = new TebakAngka(1, 10, 3);

// Objek TebakAbjad (Subclass) di-upcast ke referensi GameTebak (Superclass)
GameTebak gameTebakAbjad = new TebakAbjad('a', 'z', 5);

```

---

### C. Polymorphism & Dynamic Binding (Markwell & Reza)

* **Polymorphism:** Pemanggilan nama method yang sama (`hitungSkor()`) menghasilkan eksekusi yang disesuaikan dengan jenis objeknya.
* **Dynamic Binding:** Keputusan method mana yang dieksekusi ditentukan secara otomatis oleh Java Virtual Machine (JVM) saat **RUNTIME** saat aplikasi sedang dimainkan.

```text
User Memilih Game di Menu Utama (Runtime)
                 │
                 ├──► [1. Tebak Angka]  ──► JVM Memanggil Method TebakAngka
                 │
                 └──► [2. Tebak Abjad]  ──► JVM Memanggil Method TebakAbjad

```

---

# 🚀 PANDUAN SETUP & WORKFLOW GIT UNTUK ANGGOTA

## 1. Cek Instalasi Git

```bash
git --version

```

## 2. Clone Repository

```bash
cd Documents
git clone [https://github.com/RenaldiPasapan/PBO_GameTebak_Kelompok1.git](https://github.com/RenaldiPasapan/PBO_GameTebak_Kelompok1.git)
cd PBO_GameTebak_Kelompok1

```

## 3. Buat & Pindah ke Branch Fitur Masing-Masing

### 👑 Renaldi Pasapan — Upcasting

```bash
git checkout master
git pull origin master
git checkout -b feature/upcasting
git push -u origin feature/upcasting

```

### 👤 Jonathan Immanuel Iskandar — Overriding Tebak Angka

```bash
git checkout master
git pull origin master
git checkout -b feature/overriding-angka
git push -u origin feature/overriding-angka

```

### 👤 Rafli Gio Manulang — Overriding Tebak Abjad

```bash
git checkout master
git pull origin master
git checkout -b feature/overriding-abjad
git push -u origin feature/overriding-abjad

```

### 👤 Markwell Gilang Airlanga — Polymorphism

```bash
git checkout master
git pull origin master
git checkout -b feature/polymorphism
git push -u origin feature/polymorphism

```

### 👤 Reza Wijaya — Dynamic Binding

```bash
git checkout master
git pull origin master
git checkout -b feature/dynamic-binding
git push -u origin feature/dynamic-binding

```

---

## 4. Panduan Commit & Push

```bash
git status
git add .
git commit -m "feat: implementasi konsep materi 3 PBO"
git push

```

---

## 💡 VS Code Markdown Preview

Untuk melihat tampilan preview `README.md` ini secara langsung di VS Code, tekan tombol **`Ctrl + Shift + V`**.

```

---

### Cara Update ke GitHub:
Setelah mengganti isi file `README.md` dengan kodingan di atas, jalankan ini di Terminal VS Code:

```bash
git add README.md
git commit -m "docs: update gabungan struktur materi 3 lengkap di README"
git push origin master

```

Begitu selesai di-push, seluruh penjelasan struktur dan diagram pohonnya akan langsung tampil rapi pas orang scroll halaman depan GitHub kamu!



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
├── Struktur_Materi3.md                  <-- (Baru: Peta Arsitektur & Tugas Anggota)
├── Struktur_Overriding_Polymorphism.md  <-- (Baru: Bedah Overriding & Polymorphism)
└── Struktur_Upcasting_DynamicBinding.md <-- (Baru: Bedah Upcasting & Dynamic Binding)