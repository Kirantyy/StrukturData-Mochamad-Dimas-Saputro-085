import java.util.Scanner;

public class DataMahasiswa {

    // Kapasitas maksimum array (fixed-size)
    static final int MAX = 10;

    // Array untuk menyimpan NIM dan Nama
    static String[] nim = new String[MAX];
    static String[] nama = new String[MAX];

    // Menyimpan jumlah data yang sedang aktif
    static int count = 0;

    static Scanner sc = new Scanner(System.in);

    // ================= INSERT AT BEGINNING =================
    static void insertAtBeginning() {
        // Cek apakah array sudah penuh
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        // Geser semua data ke kanan satu langkah
        for (int i = count; i > 0; i--) {
            nim[i] = nim[i - 1];
            nama[i] = nama[i - 1];
        }

        // Input data baru di index 0
        System.out.print("Masukkan NIM: ");
        nim[0] = sc.nextLine();
        System.out.print("Masukkan Nama: ");
        nama[0] = sc.nextLine();

        // Tambah jumlah data
        count++;
    }

    // ================= INSERT AT GIVEN POSITION =================
    static void insertAtPosition() {
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        // Input posisi
        System.out.print("Posisi (0 - " + count + "): ");
        int pos = sc.nextInt();
        sc.nextLine(); // buang newline

        // Validasi posisi
        if (pos < 0 || pos > count) {
            System.out.println("Posisi tidak valid!");
            return;
        }

        // Geser data mulai dari belakang sampai posisi
        for (int i = count; i > pos; i--) {
            nim[i] = nim[i - 1];
            nama[i] = nama[i - 1];
        }

        // Input data baru
        System.out.print("Masukkan NIM: ");
        nim[pos] = sc.nextLine();
        System.out.print("Masukkan Nama: ");
        nama[pos] = sc.nextLine();

        count++;
    }

    // ================= INSERT AT END =================
    static void insertAtEnd() {
        if (count == MAX) {
            System.out.println("Array penuh!");
            return;
        }

        // Simpan data di index terakhir (count)
        System.out.print("Masukkan NIM: ");
        nim[count] = sc.nextLine();
        System.out.print("Masukkan Nama: ");
        nama[count] = sc.nextLine();

        count++;
    }

    // ================= DELETE FROM BEGINNING =================
    static void deleteBeginning() {
        if (count == 0) return;

        // Geser semua data ke kiri
        for (int i = 0; i < count - 1; i++) {
            nim[i] = nim[i + 1];
            nama[i] = nama[i + 1];
        }

        count--;
    }

    // ================= DELETE GIVEN POSITION =================
    static void deletePosition() {
        if (count == 0) return;

        System.out.print("Posisi yang dihapus: ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 0 || pos >= count) return;

        // Geser data setelah posisi ke kiri
        for (int i = pos; i < count - 1; i++) {
            nim[i] = nim[i + 1];
            nama[i] = nama[i + 1];
        }

        count--;
    }

    // ================= DELETE FROM END =================
    static void deleteEnd() {
        if (count > 0) {
            // Cukup kurangi count
            count--;
        }
    }

    // ================= DELETE FIRST OCCURRENCE =================
    static void deleteFirstOccurrence() {
        if (count == 0) return;

        System.out.print("Masukkan NIM yang dihapus: ");
        String key = sc.nextLine();

        // Cari NIM yang sama
        for (int i = 0; i < count; i++) {
            if (nim[i].equals(key)) {

                // Geser data setelahnya ke kiri
                for (int j = i; j < count - 1; j++) {
                    nim[j] = nim[j + 1];
                    nama[j] = nama[j + 1];
                }

                count--;
                return;
            }
        }

        System.out.println("Data tidak ditemukan");
    }

    // ================= SHOW DATA =================
    static void showData() {
        for (int i = 0; i < count; i++) {
            System.out.println(i + ". " + nim[i] + " - " + nama[i]);
        }
    }

    // ================= MAIN MENU =================
    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n1.Insert Beginning");
            System.out.println("2.Insert Position");
            System.out.println("3.Insert End");
            System.out.println("4.Delete Beginning");
            System.out.println("5.Delete Position");
            System.out.println("6.Delete End");
            System.out.println("7.Delete First Occurrence");
            System.out.println("8.Show Data");
            System.out.println("9.Exit");

            System.out.print("Pilih: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> insertAtBeginning();
                case 2 -> insertAtPosition();
                case 3 -> insertAtEnd();
                case 4 -> deleteBeginning();
                case 5 -> deletePosition();
                case 6 -> deleteEnd();
                case 7 -> deleteFirstOccurrence();
                case 8 -> showData();
            }
        } while (choice != 9);
    }
}
