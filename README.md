# Kalkulator Sederhana
Buka folder ini di Android Studio. Gunakan JDK 17, Android SDK 35 dan Gradle 8.9. Project belum menyertakan Gradle Wrapper; jika IDE meminta distribusi Gradle, pilih instalasi lokal Gradle 8.9 atau jalankan `gradle wrapper --gradle-version 8.9` menggunakan Gradle yang terpasang. Sync lalu Run pada emulator atau HP.

MainActivity.kt: onCreate menghubungkan layout dan tombol; hitung memvalidasi input dan memilih operasi menggunakan when; onSaveInstanceState menyimpan hasil saat rotasi. activity_main.xml mengatur EditText, Button dan TextView. Manifest mendaftarkan Activity utama. File Gradle mengatur plugin dan SDK.

Pengujian manual: 10+5=15, 10-5=5, 10*5=50, 10/5=2, pembagian nol dan input kosong menampilkan pesan kesalahan. Reset mengosongkan input. Belum diuji dengan Android SDK/emulator.
