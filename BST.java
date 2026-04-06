import java.io.*;
import java.util.*;

class Node {
    int id;
    String nama;
    Node left, right;

    Node(int id, String nama) {
        this.id = id;
        this.nama = nama;
        left = right = null;
    }
}

public class BST {

    Node root;
    int count = 0;

    // ================= INSERT =================
    Node insert(Node root, int id, String nama) {
        if (root == null) {
            count++;
            return new Node(id, nama);
        }

        if (id < root.id)
            root.left = insert(root.left, id, nama);
        else if (id > root.id)
            root.right = insert(root.right, id, nama);
        else
            System.out.println("ID sudah ada!");

        return root;
    }

    // ================= SEARCH =================
    Node search(Node root, int id) {
        if (root == null || root.id == id)
            return root;

        if (id < root.id)
            return search(root.left, id);

        return search(root.right, id);
    }

    // ================= DELETE =================
    Node delete(Node root, int id) {
        if (root == null) return root;

        if (id < root.id)
            root.left = delete(root.left, id);
        else if (id > root.id)
            root.right = delete(root.right, id);
        else {
            if (root.left == null) {
                count--;
                return root.right;
            } else if (root.right == null) {
                count--;
                return root.left;
            }

            Node temp = minValue(root.right);
            root.id = temp.id;
            root.nama = temp.nama;
            root.right = delete(root.right, temp.id);
        }
        return root;
    }

    Node minValue(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // ================= TRAVERSAL =================
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.id + " - " + root.nama);
            inorder(root.right);
        }
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.println(root.id + " - " + root.nama);
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.id + " - " + root.nama);
        }
    }

    // ================= DISPLAY =================
    void showAll() {
        if (root == null) {
            System.out.println("Tree kosong");
            return;
        }
        System.out.println("\n=== DATA (INORDER) ===");
        inorder(root);
    }

    // ================= HEIGHT =================
    int height(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // ================= STAT =================
    void showStat() {
        System.out.println("\n=== INFO TREE ===");
        System.out.println("Jumlah data : " + count);
        System.out.println("Tinggi tree : " + height(root));
    }

    // ================= LOAD CSV (UPDATED) =================
    void loadCSV(String filename) {
        try {
            // Biar gampang, kita cari filenya di folder kerja sekarang
            File file = new File(System.getProperty("user.dir"), filename);
            
            // Kalau gak ketemu, kita pake filename langsung (siapa tau inputnya full path)
            if (!file.exists()) {
                file = new File(filename);
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            br.readLine(); // skip header

            int sukses = 0;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // lewati baris kosong

                // GANTI DI SINI: Pake titik koma (;)
                String[] data = line.split(";"); 
                
                if (data.length >= 2) {
                    int id = Integer.parseInt(data[0].trim());
                    String nama = data[1].trim();

                    root = insert(root, id, nama);
                    sukses++;
                }
            }

            br.close();
            System.out.println("✅ Berhasil load " + sukses + " data!");

        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: File '" + filename + "' gak ketemu. Pastiin filenya ada di folder yang sama.");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        BST tree = new BST();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU BST =====");
            System.out.println("1. Tambah Data");
            System.out.println("2. Cari Data");
            System.out.println("3. Hapus Data");
            System.out.println("4. Inorder");
            System.out.println("5. Preorder");
            System.out.println("6. Postorder");
            System.out.println("7. Tampilkan Semua Data");
            System.out.println("8. Info Tree");
            System.out.println("9. Load dari CSV");
            System.out.println("0. Exit");

            System.out.print("Pilih: ");
            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nama: ");
                    String nama = sc.nextLine();
                    tree.root = tree.insert(tree.root, id, nama);
                    break;

                case 2:
                    System.out.print("Cari ID: ");
                    int cari = sc.nextInt();
                    Node hasil = tree.search(tree.root, cari);
                    if (hasil != null)
                        System.out.println("Ditemukan: " + hasil.id + " - " + hasil.nama);
                    else
                        System.out.println("Tidak ditemukan");
                    break;

                case 3:
                    System.out.print("Hapus ID: ");
                    int hapus = sc.nextInt();
                    tree.root = tree.delete(tree.root, hapus);
                    break;

                case 4:
                    tree.inorder(tree.root);
                    break;

                case 5:
                    tree.preorder(tree.root);
                    break;

                case 6:
                    tree.postorder(tree.root);
                    break;

                case 7:
                    tree.showAll();
                    break;

                case 8:
                    tree.showStat();
                    break;

                case 9:
                    System.out.print("Nama file CSV (contoh: data.csv): ");
                    String fileInput = sc.nextLine();
                    tree.loadCSV(fileInput);
                    break;

                case 0:
                    System.out.println("Program selesai");
                    return;

                default:
                    System.out.println("Menu tidak tersedia");
            }
        }
    }
}