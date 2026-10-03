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
| **Renaldi Pasapan** | ⬆️ **Upcasting** | Mengatur variabel referensi `GameTebak` penampung objek subclass | `feature/upcasting` |
| **Jonathan Immanuel I.** | 🔄 **Overriding** | Overriding method `hitungSkor()` & `validasiTebakan()` di `TebakAngka.java` | `feature/overriding-angka` |
| **Rafli Gio Manulang** | 🔄 **Overriding** | Overriding method `hitungSkor()` & `validasiTebakan()` di `TebakAbjad.java` | `feature/overriding-abjad` |
| **Markwell Gilang A.** | 🎭 **Polymorphism** | Menerapkan fleksibilitas panggilan method polimorfik | `feature/polymorphism` |
| **Reza Wijaya** | ⚡ **Dynamic Binding** | Menangani eksekusi late-binding saat game running di `mainmenu.java` | `feature/dynamic-binding` |

> ⚠️ **RULES GIT:** Dilarang keras melakukan `git push` langsung ke branch `master`.
> Setiap anggota WAJIB mengerjakan fitur di branch-nya masing-masing!

---

# 🛠️ Panduan Setup & Workflow Git untuk Anggota

## 1. Cek Instalasi Git

Buka Terminal / Command Prompt:

```bash
git --version
2. Clone Repository
Buka VS Code → Terminal → New Terminal:

Bash
cd Documents
git clone [https://github.com/RenaldiPasapan/PBO_GameTebak_Kelompok1.git](https://github.com/RenaldiPasapan/PBO_GameTebak_Kelompok1.git)
cd PBO_GameTebak_Kelompok1
3. Buka Project di VS Code
Bash
code .
4. Buat & Pindah ke Branch Fitur Masing-Masing
Setiap anggota WAJIB berpindah branch sebelum mulai menulis kodingan:

👑 Renaldi Pasapan — Upcasting
Bash
git checkout master
git pull origin master
git checkout -b feature/upcasting
git push -u origin feature/upcasting
👤 Jonathan Immanuel Iskandar — Overriding Tebak Angka
Bash
git checkout master
git pull origin master
git checkout -b feature/overriding-angka
git push -u origin feature/overriding-angka
👤 Rafli Gio Manulang — Overriding Tebak Abjad
Bash
git checkout master
git pull origin master
git checkout -b feature/overriding-abjad
git push -u origin feature/overriding-abjad
👤 Markwell Gilang Airlanga — Polymorphism
Bash
git checkout master
git pull origin master
git checkout -b feature/polymorphism
git push -u origin feature/polymorphism
👤 Reza Wijaya — Dynamic Binding
Bash
git checkout master
git pull origin master
git checkout -b feature/dynamic-binding
git push -u origin feature/dynamic-binding
5. Pastikan Posisi Branch Aktif
Cek branch aktif menggunakan perintah:

Bash
git branch
🏗️ Arsitektur Kode & Pembagian Fokus Sub-Materi 3
Struktur file project:

Plaintext
PBO_GameTebak_Kelompok1/
│
├── GameTebak.java     <-- Superclass (Generalisasi & Base Method)
├── TebakAngka.java    <-- Subclass (Overriding Logika Angka)
├── TebakAbjad.java    <-- Subclass (Overriding Logika Abjad)
├── leaderboard.java   <-- Data Storage (Enkapsulasi Array Skor)
├── mainmenu.java      <-- Executable (Dynamic Binding & Menu)
└── README.md          <-- Documentation
📝 Panduan Commit & Push Ke GitHub
Setelah selesai koding, lakukan pengecekan dan push dengan perintah berikut:

Bash
# 1. Cek status file yang diubah
git status

# 2. Tambahkan semua perubahan
git add .

# 3. Commit dengan pesan fitur
git commit -m "feat: implementasi konsep materi 3 PBO"

# 4. Push ke branch fitur di GitHub
git push
🔀 Ringkasan Command per Anggota
Bash
git clone [https://github.com/RenaldiPasapan/PBO_GameTebak_Kelompok1.git](https://github.com/RenaldiPasapan/PBO_GameTebak_Kelompok1.git)
cd PBO_GameTebak_Kelompok1
git checkout master
git pull origin master
git checkout -b feature/nama-branch-mu
git push -u origin feature/nama-branch-mu


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