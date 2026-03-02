import java.util.Scanner;

class Node {
    String data;
    Node next, prev;

    Node(String data) {
        this.data = data;
    }
}

class NewsTicker {
    Node head = null;
    int size = 0;

    // 1. INSERT BERITA (Selalu di akhir/Tail)
    void insertNews(String news) {
        Node newNode = new Node(news);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        size++;
        System.out.println("[Sistem] Berita berhasil ditambahkan.");
    }

    // 2. HAPUS BERITA (Berdasarkan nomor urut)
    void deleteNews(int pos) {
        if (head == null || pos < 1 || pos > size) {
            System.out.println("[Sistem] Nomor tidak valid!");
            return;
        }
        Node curr = head;
        for (int i = 1; i < pos; i++) curr = curr.next;

        if (size == 1) {
            head = null;
        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            if (curr == head) head = curr.next;
        }
        size--;
        System.out.println("[Sistem] Berita No." + pos + " dihapus.");
    }

    // 3 & 4. SIMULASI TEXT BERJALAN (Forward & Backward)
    void runTicker(boolean forward) {
        if (head == null) {
            System.out.println("[Sistem] Belum ada berita.");
            return;
        }
        System.out.println("\n>>> SIMULASI MULAI (Tekan ENTER untuk stop) <<<");
        Node curr = forward ? head : head.prev;

        try {
            // Loop terus menerus sampai ada input dari user
            while (System.in.available() == 0) {
                // \r berfungsi mengembalikan kursor ke awal baris
                System.out.print("\r>>> NEWS TICKER: " + curr.data + "                ");
                Thread.sleep(3000); // Delay 3 detik
                curr = forward ? curr.next : curr.prev;
            }
            System.in.read(); // Membersihkan sisa Enter di buffer
        } catch (Exception e) {
            System.out.println("Terjadi error.");
        }
        System.out.println("\n[Sistem] Simulasi berhenti.");
    }

    // 5. TAMPILKAN BERITA SPESIFIK
    void displaySpecific(int pos) {
        if (head == null || pos < 1 || pos > size) {
            System.out.println("[Sistem] Data tidak ditemukan.");
            return;
        }
        Node curr = head;
        for (int i = 1; i < pos; i++) curr = curr.next;
        System.out.println("Berita No." + pos + ": " + curr.data);
    }
}

public class NewsText {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NewsTicker ticker = new NewsTicker();
        int opt = 0;

        while (opt != 6) {
            System.out.println("\n=== MENU TV NEWS TICKER ===");
            System.out.println("1. Tambah Berita\n2. Hapus Berita\n3. Jalan Forward\n4. Jalan Backward\n5. Cari Berita\n6. Exit");
            System.out.print("Pilih: ");
            try {
                opt = sc.nextInt();
                sc.nextLine(); 
                switch(opt) {
                    case 1: System.out.print("Isi Berita: "); ticker.insertNews(sc.nextLine()); break;
                    case 2: System.out.print("No Berita: "); ticker.deleteNews(sc.nextInt()); break;
                    case 3: ticker.runTicker(true); break;
                    case 4: ticker.runTicker(false); break;
                    case 5: System.out.print("No Berita: "); ticker.displaySpecific(sc.nextInt()); break;
                }
            } catch (Exception e) {
                System.out.println("Input salah!");
                sc.nextLine();
            }
        }
    }
}
