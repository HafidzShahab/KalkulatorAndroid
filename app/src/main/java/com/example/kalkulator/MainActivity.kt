// Identitas package aplikasi.
package com.example.kalkulator

// Activity sebagai halaman utama Android.
import android.app.Activity
// Bundle untuk menyimpan dan memulihkan keadaan halaman.
import android.os.Bundle
// Komponen tombol.
import android.widget.Button
// Komponen input angka.
import android.widget.EditText
// Komponen penampil teks.
import android.widget.TextView

// Class utama yang mengatur tampilan dan logika kalkulator.
class MainActivity : Activity() {
    // Referensi input angka pertama, diinisialisasi saat onCreate.
    private lateinit var angka1: EditText
    // Referensi input angka kedua.
    private lateinit var angka2: EditText
    // Referensi teks hasil perhitungan.
    private lateinit var hasil: TextView

    // Dipanggil ketika halaman dibuat.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Menjalankan inisialisasi bawaan Activity.
        super.onCreate(savedInstanceState)
        // Memasang desain dari activity_main.xml.
        setContentView(R.layout.activity_main)
        // Menghubungkan input pertama dengan ID pada XML.
        angka1 = findViewById(R.id.angka1)
        // Menghubungkan input kedua dengan ID pada XML.
        angka2 = findViewById(R.id.angka2)
        // Menghubungkan komponen hasil dengan ID pada XML.
        hasil = findViewById(R.id.hasil)
        // Tombol tambah memanggil hitung dengan operator +.
        findViewById<Button>(R.id.tambah).setOnClickListener { hitung('+') }
        // Tombol kurang memanggil hitung dengan operator -.
        findViewById<Button>(R.id.kurang).setOnClickListener { hitung('-') }
        // Tombol kali memanggil hitung dengan operator *.
        findViewById<Button>(R.id.kali).setOnClickListener { hitung('*') }
        // Tombol bagi memanggil hitung dengan operator /.
        findViewById<Button>(R.id.bagi).setOnClickListener { hitung('/') }
        // Tombol reset menghapus input dan hasil.
        findViewById<Button>(R.id.reset).setOnClickListener {
            // Mengosongkan input pertama.
            angka1.text.clear()
            // Mengosongkan input kedua.
            angka2.text.clear()
            // Mengembalikan hasil menjadi nol.
            hasil.text = "0"
        }
        // Memulihkan hasil sebelumnya, misalnya setelah rotasi layar.
        hasil.text = savedInstanceState?.getString("hasil") ?: "0"
    }

    // Fungsi perhitungan yang menerima satu karakter operator.
    private fun hitung(operator: Char) {
        // Mengambil input pertama, mengubah koma menjadi titik, lalu mengonversi ke Double; null jika gagal.
        val a = angka1.text.toString().trim().replace(',', '.').toDoubleOrNull()
        // Melakukan konversi yang sama untuk input kedua.
        val b = angka2.text.toString().trim().replace(',', '.').toDoubleOrNull()
        // Memastikan kedua angka valid dan bernilai hingga.
        if (a == null || b == null || !a.isFinite() || !b.isFinite()) {
            // Menampilkan pesan jika input tidak valid.
            hasil.text = "Masukkan angka yang valid"
            return
        }
        // Mencegah pembagian dengan nol.
        if (operator == '/' && b == 0.0) {
            // Menampilkan pesan kesalahan pembagian.
            hasil.text = "Tidak bisa dibagi nol"
            return
        }
        // Memilih operasi matematika sesuai operator.
        val nilai = when (operator) {
            // Menjumlahkan kedua angka.
            '+' -> a + b
            // Mengurangi angka pertama dengan angka kedua.
            '-' -> a - b
            // Mengalikan kedua angka.
            '*' -> a * b
            // Membagi angka pertama dengan angka kedua.
            '/' -> a / b
            // Menghentikan fungsi jika operator tidak dikenal.
            else -> return
        }
        // Menampilkan hasil tanpa nol desimal yang tidak diperlukan; menolak hasil tak hingga.
        hasil.text = if (nilai.isFinite()) java.math.BigDecimal.valueOf(nilai).stripTrailingZeros().toPlainString() else "Hasil terlalu besar"
    }

    // Menyimpan hasil ketika Android membuat ulang Activity.
    override fun onSaveInstanceState(outState: Bundle) {
        // Menyimpan teks hasil ke Bundle.
        outState.putString("hasil", hasil.text.toString())
        // Menjalankan mekanisme penyimpanan bawaan Android.
        super.onSaveInstanceState(outState)
    }
}
