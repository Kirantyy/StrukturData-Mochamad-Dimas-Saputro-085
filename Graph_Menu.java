import java.util.*;

class Graph {
    // Struktur data graf menggunakan adjacency list
    private Map<String, List<String>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    // Menambahkan vertex
    public void addVertex(String vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new ArrayList<>());
            System.out.println("Vertex '" + vertex + "' berhasil ditambahkan.");
        } else {
            System.out.println("Vertex '" + vertex + "' telah terdaftar sebelumnya.");
        }
    }

    // Menghapus vertex
    public void removeVertex(String vertex) {
        if (graph.containsKey(vertex)) {
            graph.remove(vertex);

            // Hapus referensi vertex dari vertex lain
            for (String v : graph.keySet()) {
                graph.get(v).remove(vertex);
            }

            System.out.println("Vertex '" + vertex + "' beserta edge terkait berhasil dihapus.");
        } else {
            System.out.println("Vertex '" + vertex + "' tidak ditemukan.");
        }
    }

    // Menambahkan edge
    public void addEdge(String v1, String v2) {
        if (!graph.containsKey(v1)) {
            System.out.println("Gagal: Vertex '" + v1 + "' belum terdaftar.");
            return;
        }

        if (!graph.containsKey(v2)) {
            System.out.println("Gagal: Vertex '" + v2 + "' belum terdaftar.");
            return;
        }

        if (!graph.get(v1).contains(v2)) {
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);

            System.out.println("Edge antara '" + v1 + "' dan '" + v2 + "' berhasil ditambahkan.");
        } else {
            System.out.println("Edge antara '" + v1 + "' dan '" + v2 + "' telah ada.");
        }
    }

    // Menghapus edge
    public void removeEdge(String v1, String v2) {
        if (graph.containsKey(v1) && graph.get(v1).contains(v2)) {
            graph.get(v1).remove(v2);

            if (graph.containsKey(v2)) {
                graph.get(v2).remove(v1);
            }

            System.out.println("Edge antara '" + v1 + "' dan '" + v2 + "' berhasil dihapus.");
        } else {
            System.out.println("Edge antara '" + v1 + "' dan '" + v2 + "' tidak ditemukan.");
        }
    }

    // Menampilkan adjacency matrix dan adjacency list
    public void display() {
        if (graph.isEmpty()) {
            System.out.println("Graf saat ini kosong.");
            return;
        }

        List<String> vertices = new ArrayList<>(graph.keySet());
        Collections.sort(vertices);

        int n = vertices.size();

        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.put(vertices.get(i), i);
        }

        int[][] matrix = new int[n][n];

        for (String v : graph.keySet()) {
            for (String neighbor : graph.get(v)) {
                matrix[idxMap.get(v)][idxMap.get(neighbor)] = 1;
            }
        }

        System.out.println("\n========================================");
        System.out.println("Matriks Ketetanggaan (Adjacency Matrix):");

        System.out.print("      ");
        for (String v : vertices) {
            System.out.printf("%-6s", v);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%-6s", vertices.get(i));

            for (int j = 0; j < n; j++) {
                System.out.printf("%-6d", matrix[i][j]);
            }

            System.out.println();
        }

        System.out.println("\nDaftar Ketetanggaan (Adjacency List):");

        for (String v : vertices) {
            System.out.println(v + ": " + graph.get(v));
        }

        System.out.println("========================================\n");
    }

    // DFS Iteratif
    public List<String> dfs(String start) {
        List<String> result = new ArrayList<>();

        if (!graph.containsKey(start)) {
            System.out.println("Gagal: Vertex '" + start + "' tidak ditemukan.");
            return result;
        }

        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            String node = stack.pop();

            if (!visited.contains(node)) {
                visited.add(node);
                result.add(node);

                List<String> neighbors = new ArrayList<>(graph.get(node));
                Collections.sort(neighbors, Collections.reverseOrder());

                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        System.out.println("DFS Traversal dari '" + start + "': " + String.join(" -> ", result));

        return result;
    }

    // BFS Iteratif
    public List<String> bfs(String start) {
        List<String> result = new ArrayList<>();

        if (!graph.containsKey(start)) {
            System.out.println("Gagal: Vertex '" + start + "' tidak ditemukan.");
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            result.add(node);

            List<String> neighbors = new ArrayList<>(graph.get(node));
            Collections.sort(neighbors);

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        System.out.println("BFS Traversal dari '" + start + "': " + String.join(" -> ", result));

        return result;
    }
}

public class Graph_Menu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Graph graf = new Graph();

        System.out.println("Selamat datang, Igas. Silakan gunakan program berikut untuk manajemen graf.\n");

        while (true) {
            System.out.println("===== MENU GRAF =====");
            System.out.println("1. Tambah Vertex");
            System.out.println("2. Hapus Vertex");
            System.out.println("3. Tambah Edge");
            System.out.println("4. Hapus Edge");
            System.out.println("5. Tampilkan Graf (Matriks & Daftar)");
            System.out.println("6. Traversal DFS");
            System.out.println("7. Traversal BFS");
            System.out.println("8. Keluar");

            System.out.print("Masukkan pilihan Anda (1-8): ");
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan nama vertex: ");
                    String vertex = input.nextLine().trim();

                    if (!vertex.isEmpty()) {
                        graf.addVertex(vertex);
                    } else {
                        System.out.println("Input tidak valid.");
                    }
                    break;

                case "2":
                    System.out.print("Masukkan nama vertex yang akan dihapus: ");
                    vertex = input.nextLine().trim();

                    if (!vertex.isEmpty()) {
                        graf.removeVertex(vertex);
                    } else {
                        System.out.println("Input tidak valid.");
                    }
                    break;

                case "3":
                    System.out.print("Masukkan vertex asal: ");
                    String v1 = input.nextLine().trim();

                    System.out.print("Masukkan vertex tujuan: ");
                    String v2 = input.nextLine().trim();

                    if (!v1.isEmpty() && !v2.isEmpty()) {
                        graf.addEdge(v1, v2);
                    } else {
                        System.out.println("Input tidak valid.");
                    }
                    break;

                case "4":
                    System.out.print("Masukkan vertex asal: ");
                    v1 = input.nextLine().trim();

                    System.out.print("Masukkan vertex tujuan: ");
                    v2 = input.nextLine().trim();

                    if (!v1.isEmpty() && !v2.isEmpty()) {
                        graf.removeEdge(v1, v2);
                    } else {
                        System.out.println("Input tidak valid.");
                    }
                    break;

                case "5":
                    graf.display();
                    break;

                case "6":
                    System.out.print("Masukkan vertex awal untuk DFS: ");
                    String startDFS = input.nextLine().trim();

                    if (!startDFS.isEmpty()) {
                        graf.dfs(startDFS);
                    } else {
                        System.out.println("Input tidak valid.");
                    }
                    break;

                case "7":
                    System.out.print("Masukkan vertex awal untuk BFS: ");
                    String startBFS = input.nextLine().trim();

                    if (!startBFS.isEmpty()) {
                        graf.bfs(startBFS);
                    } else {
                        System.out.println("Input tidak valid.");
                    }
                    break;

                case "8":
                    System.out.println("Program telah berakhir. Terima kasih.");
                    input.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1 hingga 8.");
            }
        }
    }
}