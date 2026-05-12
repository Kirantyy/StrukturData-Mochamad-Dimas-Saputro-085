import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// ======================================
// CLASS DATA
// ======================================
class Data {
    int id;
    String nama;

    public Data(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama;
    }
}

public class HeapProgram {

    static PriorityQueue<Data> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(d -> d.id));

    static PriorityQueue<Data> maxHeap =
            new PriorityQueue<>((a, b) -> b.id - a.id);

    static Scanner input = new Scanner(System.in);

    // ======================================
    // LOAD DATA CSV (MODIFIED)
    // ======================================
    static void loadData(String filename) {
        // Otomatis mencari ke dalam folder HeapStructure
        String path = "HeapStructure/" + filename;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length < 2) continue; // Cek baris kosong

                int id = Integer.parseInt(data[0].trim());
                String nama = data[1].trim();

                Data d = new Data(id, nama);
                minHeap.add(d);
                maxHeap.add(d);
            }

            br.close();
            System.out.println("Data berhasil dimuat dari: " + path);
            System.out.println();

        } catch (Exception e) {
            System.out.println("Terjadi error saat membaca file!");
            System.out.println("Pastikan file berada di: " + path);
            System.out.println(e);
        }
    }

    static void insertData() {
        System.out.print("Masukkan ID : ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Masukkan Nama : ");
        String nama = input.nextLine();

        Data d = new Data(id, nama);
        minHeap.add(d);
        maxHeap.add(d);

        System.out.println("Data berhasil ditambahkan!");
        System.out.println();
    }

    static void showAscending() {
        if (minHeap.isEmpty()) {
            System.out.println("Min Heap kosong!");
            return;
        }
        System.out.println("\n===== DATA ASCENDING (MIN HEAP) =====");
        PriorityQueue<Data> temp = new PriorityQueue<>(minHeap);
        while (!temp.isEmpty()) {
            System.out.println(temp.poll());
        }
    }

    static void showDescending() {
        if (maxHeap.isEmpty()) {
            System.out.println("Max Heap kosong!");
            return;
        }
        System.out.println("\n===== DATA DESCENDING (MAX HEAP) =====");
        PriorityQueue<Data> temp = new PriorityQueue<>(maxHeap);
        while (!temp.isEmpty()) {
            System.out.println(temp.poll());
        }
    }

    static void deleteMin() {
        if (!minHeap.isEmpty()) {
            Data d = minHeap.poll();
            maxHeap.remove(d); // Agar sinkron
            System.out.println("Data berhasil dihapus dari Min Heap: " + d);
        } else {
            System.out.println("Min Heap kosong!");
        }
    }

    static void deleteMax() {
        if (!maxHeap.isEmpty()) {
            Data d = maxHeap.poll();
            minHeap.remove(d); // Agar sinkron
            System.out.println("Data berhasil dihapus dari Max Heap: " + d);
        } else {
            System.out.println("Max Heap kosong!");
        }
    }

    static void showHeap() {
        System.out.println("\n===== MIN HEAP (Struktur Array) =====");
        System.out.println(minHeap);
        System.out.println("\n===== MAX HEAP (Struktur Array) =====");
        System.out.println(maxHeap);
    }

    public static void main(String[] args) {
        System.out.print("Masukkan nama file CSV : ");
        String filename = input.nextLine();

        loadData(filename);

        while (true) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Insert Data");
            System.out.println("2. Show Ascending (Min Heap)");
            System.out.println("3. Show Descending (Max Heap)");
            System.out.println("4. Delete Min Heap");
            System.out.println("5. Delete Max Heap");
            System.out.println("6. Show Heap Raw");
            System.out.println("7. Exit");
            System.out.print("Pilih menu : ");
            String pilih = input.nextLine();

            if (pilih.equals("1")) insertData();
            else if (pilih.equals("2")) showAscending();
            else if (pilih.equals("3")) showDescending();
            else if (pilih.equals("4")) deleteMin();
            else if (pilih.equals("5")) deleteMax();
            else if (pilih.equals("6")) showHeap();
            else if (pilih.equals("7")) {
                System.out.println("Program selesai.");
                break;
            } else {
                System.out.println("Menu tidak valid!");
            }
        }
    }
}