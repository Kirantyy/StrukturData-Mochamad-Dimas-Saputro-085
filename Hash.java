import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class HashTable {
    private int size;
    private Integer[] table; // Menggunakan objek Integer agar slot kosong bernilai null

    public HashTable(int size) {
        this.size = size;
        this.table = new Integer[this.size];
    }

    // Fungsi hash dasar: key mod size
    private int hashFunction(int key) {
        return key % size;
    }

    // 1. INPUT DATA (Insert)
    public boolean insert(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        // Linear Probing: geser ke indeks berikutnya jika slot sudah terisi
        while (table[index] != null) {
            if (table[index] == key) {
                return false; // Data duplikat tidak dimasukkan kembali
            }
            index = (index + 1) % size;

            if (index == startIndex) {
                System.out.println("Error: Hash Table penuh!");
                return false;
            }
        }
        table[index] = key;
        return true;
    }

    // 3. CARI DATA (Search)
    public int search(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != null) {
            if (table[index] == key) {
                return index; // Mengembalikan indeks tempat data ditemukan
            }
            index = (index + 1) % size;

            if (index == startIndex) {
                break;
            }
        }
        return -1; // Data tidak ditemukan
    }

    // 2. HAPUS DATA (Delete)
    public boolean delete(int key) {
        int index = search(key);
        if (index != -1) {
            table[index] = null;
            return true;
        }
        return false;
    }

    // Menampilkan seluruh isi tabel dari indeks 0 sampai 149
    public void displayAll() {
        System.out.println("\n================ TAMPILAN SELURUH HASH TABLE ================");
        int filledCount = 0;

        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                // %03d digunakan agar tampilan angka indeks rapi (misal: 001, 015, 120)
                System.out.printf("Slot %03d -> [ %d ]\n", i, table[i]);
                filledCount++;
            } else {
                System.out.printf("Slot %03d -> Kosong (None)\n", i);
            }
        }
        System.out.println("=============================================================");
        System.out.printf("[INFO] Total slot Terisi: %d / %d\n", filledCount, size);
    }
}

public class Hash { // DI SINI PERUBAHANNYA: Nama class disamakan dengan nama file (Hash)
    public static void main(String[] args) {
        // Inisialisasi Hash Table dengan ukuran 150 slot
        HashTable hashTable = new HashTable(200);
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        // Generate 100 angka acak yang unik (rentang 1 - 200)
        System.out.println("Mengisi 100 data acak unik (rentang 1-200) ke dalam Hash Table...");
        Set<Integer> randomNumbers = new HashSet<>();
        
        while (randomNumbers.size() < 100) {
            // rand.nextInt(200) menghasilkan 0-199, ditambah 1 jadi 1-200
            randomNumbers.add(rand.nextInt(2000) + 1);
        }

        // Masukkan 100 angka acak tersebut ke hash table menggunakan Linear Probing
        for (int num : randomNumbers) {
            hashTable.insert(num);
        }

        // Menu Interaktif
        while (true) {
            hashTable.displayAll();

            System.out.println("\n=== MENU HASH TABLE ===");
            System.out.println("1. INPUT DATA");
            System.out.println("2. HAPUS DATA");
            System.out.println("3. CARI DATA");
            System.out.println("4. KELUAR");
            System.out.print("Pilih menu (1-4): ");
            
            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                try {
                    System.out.print("Masukkan angka baru: ");
                    int angka = Integer.parseInt(scanner.nextLine());
                    if (hashTable.insert(angka)) {
                        System.out.println("Berhasil! Angka " + angka + " telah dimasukkan.");
                    } else {
                        System.out.println("Gagal! Angka " + angka + " sudah ada atau table penuh.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka numerik!");
                }

            } else if (pilihan.equals("2")) {
                try {
                    System.out.print("Masukkan angka yang ingin dihapus: ");
                    int angka = Integer.parseInt(scanner.nextLine());
                    if (hashTable.delete(angka)) {
                        System.out.println("Berhasil! Angka " + angka + " telah dihapus.");
                    } else {
                        System.out.println("Data " + angka + " tidak ditemukan di dalam table.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka numerik!");
                }

            } else if (pilihan.equals("3")) {
                try {
                    System.out.print("Masukkan angka yang dicari: ");
                    int angka = Integer.parseInt(scanner.nextLine());
                    int posisi = hashTable.search(angka) ;
                    if (posisi != -1) {
                        System.out.println("Data " + angka + " DITEMUKAN pada Slot/Indeks ke-" + posisi + ".");
                    } else {
                        System.out.println("Data " + angka + " TIDAK DITEMUKAN.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka numerik!");
                }

            } else if (pilihan.equals("4")) {
                System.out.println("Terima kasih! Program selesai.");
                break;
            } else {
                System.out.println("Pilihan menu tidak valid, silakan coba lagi.");
            }

            System.out.println("----------------------------------------");
        }
        scanner.close();
    }
}