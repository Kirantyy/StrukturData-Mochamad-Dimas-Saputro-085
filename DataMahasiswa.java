import java.util.Scanner;

/*
 * Class Mahasiswa
 * Digunakan untuk menyimpan data mahasiswa (NIM dan Nama)
 */
class Mahasiswa {
    String nim, nama;

    // Constructor untuk mengisi NIM dan Nama saat objek dibuat
    Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }
}

/*
 * Class utama untuk mengelola data mahasiswa
 * Menggunakan array statis dengan kapasitas maksimal 10 data
 */
public class DataMahasiswa {

    // Array untuk menyimpan objek Mahasiswa
    static Mahasiswa[] dataMhs = new Mahasiswa[10];

    // Menyimpan jumlah data mahasiswa yang sudah terisi
    static int count = 0;

    // Scanner untuk input dari user
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;

        // Perulangan menu sampai user memilih Exit (9)
        do {
            System.out.println("\n=== MENU JAVA (Data: " + count + "/10) ===");
            System.out.println("1. Insert Start");
            System.out.println("2. Insert Pos");
            System.out.println("3. Insert End");
            System.out.println("4. Del Start");
            System.out.println("5. Del Pos");
            System.out.println("6. Del End");
            System.out.println("7. Del First Occur");
            System.out.println("8. Show");
            System.out.println("9. Exit");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();

            // Switch-case untuk menentukan aksi sesuai pilihan menu
            switch (pilihan) {
                case 1 -> insertPos(0);              // Insert di awal array
                case 2 -> {
                    System.out.print("Posisi: ");
                    insertPos(sc.nextInt());        // Insert di posisi tertentu
                }
                case 3 -> insertPos(count);          // Insert di akhir array
                case 4 -> deletePos(0);              // Hapus data di awal
                case 5 -> {
                    System.out.print("Posisi: ");
                    deletePos(sc.nextInt());        // Hapus data di posisi tertentu
                }
                case 6 -> deletePos(count - 1);      // Hapus data di akhir
                case 7 -> deleteFirstOccurrence();   // Hapus berdasarkan NIM pertama yang ditemukan
                case 8 -> show();                    // Tampilkan semua data
            }
        } while (pilihan != 9); // Keluar jika pilih 9
    }

    /*
     * Method untuk insert data mahasiswa di posisi tertentu
     */
    static void insertPos(int pos) {
        // Cek apakah array sudah penuh
        if (count >= 10) {
            System.out.println("Penuh!");
            return;
        }

        // Validasi posisi
        if (pos < 0 || pos > count) {
            System.out.println("Posisi salah!");
            return;
        }

        // Geser data ke kanan mulai dari posisi insert
        for (int i = count; i > pos; i--) {
            dataMhs[i] = dataMhs[i - 1];
        }

        // Input data mahasiswa
        System.out.print("NIM: ");
        String nim = sc.next();

        sc.nextLine(); // Membersihkan buffer newline
        System.out.print("Nama: ");
        String nama = sc.nextLine();

        // Simpan data ke array
        dataMhs[pos] = new Mahasiswa(nim, nama);
        count++; // Tambah jumlah data
    }

    /*
     * Method untuk menghapus data mahasiswa di posisi tertentu
     */
    static void deletePos(int pos) {
        // Validasi jika data kosong atau posisi tidak valid
        if (count == 0 || pos < 0 || pos >= count) {
            System.out.println("Gagal!");
            return;
        }

        // Geser data ke kiri setelah posisi yang dihapus
        for (int i = pos; i < count - 1; i++) {
            dataMhs[i] = dataMhs[i + 1];
        }

        // Kosongkan elemen terakhir
        dataMhs[count - 1] = null;
        count--; // Kurangi jumlah data
    }

    /*
     * Method untuk menghapus data mahasiswa
     * berdasarkan NIM pertama yang ditemukan
     */
    static void deleteFirstOccurrence() {
        System.out.print("Cari NIM: ");
        String target = sc.next();

        // Cari NIM di array
        for (int i = 0; i < count; i++) {
            if (dataMhs[i].nim.equals(target)) {
                deletePos(i); // Hapus jika ditemukan
                return;
            }
        }

        // Jika NIM tidak ditemukan
        System.out.println("Tidak ditemukan.");
    }

    /*
     * Method untuk menampilkan seluruh data mahasiswa
     */
    static void show() {
        if (count == 0) {
            System.out.println("Kosong.");
        }

        // Tampilkan data satu per satu
        for (int i = 0; i < count; i++) {
            System.out.println(i + ". " + dataMhs[i].nim + " - " + dataMhs[i].nama);
        }
    }
}
