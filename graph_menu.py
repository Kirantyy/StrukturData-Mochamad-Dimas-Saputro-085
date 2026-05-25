class Graph:
    """Kelas yang merepresentasikan struktur data Graf (Undirected)."""

    def __init__(self) -> None:
        self.graph: dict[str, list[str]] = {}

    def add_vertex(self, vertex: str) -> None:
        """Menambahkan vertex baru ke dalam graf."""
        if vertex not in self.graph:
            self.graph[vertex] = []
            print(f"Vertex '{vertex}' berhasil ditambahkan.")
        else:
            print(f"Vertex '{vertex}' telah terdaftar sebelumnya.")

    def remove_vertex(self, vertex: str) -> None:
        """Menghapus vertex beserta seluruh edge yang terhubung dengannya."""
        if vertex in self.graph:
            del self.graph[vertex]
            # Menghapus referensi vertex dari daftar ketetanggaan vertex lain
            for v in self.graph:
                if vertex in self.graph[v]:
                    self.graph[v].remove(vertex)
            print(f"Vertex '{vertex}' beserta edge terkait berhasil dihapus.")
        else:
            print(f"Vertex '{vertex}' tidak ditemukan.")

    def add_edge(self, v1: str, v2: str) -> None:
        """Menambahkan edge antara dua vertex. Graf bersifat undirected."""
        if v1 not in self.graph:
            print(f"Gagal: Vertex '{v1}' belum terdaftar.")
            return
        if v2 not in self.graph:
            print(f"Gagal: Vertex '{v2}' belum terdaftar.")
            return
        if v2 not in self.graph[v1]:
            self.graph[v1].append(v2)
            self.graph[v2].append(v1)
            print(f"Edge antara '{v1}' dan '{v2}' berhasil ditambahkan.")
        else:
            print(f"Edge antara '{v1}' dan '{v2}' telah ada.")

    def remove_edge(self, v1: str, v2: str) -> None:
        """Menghapus edge antara dua vertex."""
        if v1 in self.graph and v2 in self.graph[v1]:
            self.graph[v1].remove(v2)
            if v2 in self.graph and v1 in self.graph[v2]:
                self.graph[v2].remove(v1)
            print(f"Edge antara '{v1}' dan '{v2}' berhasil dihapus.")
        else:
            print(f"Edge antara '{v1}' dan '{v2}' tidak ditemukan.")

    def display(self) -> None:
        """Menampilkan graf dalam bentuk Matriks Ketetanggaan dan Daftar Ketetanggaan."""
        if not self.graph:
            print("Graf saat ini kosong.")
            return

        vertices = sorted(self.graph.keys())
        n = len(vertices)
        idx_map = {v: i for i, v in enumerate(vertices)}

        # Inisialisasi matriks
        matrix = [[0] * n for _ in range(n)]
        for v in self.graph:
            for neighbor in self.graph[v]:
                if neighbor in idx_map:
                    matrix[idx_map[v]][idx_map[neighbor]] = 1

        print("\n" + "="*40)
        print("Matriks Ketetanggaan (Adjacency Matrix):")
        header = "     " + " ".join(f"{v:^6}" for v in vertices)
        print(header)
        for i, v in enumerate(vertices):
            row = f"{v:^6} " + " ".join(f"{matrix[i][j]:^6}" for j in range(n))
            print(row)

        print("\nDaftar Ketetanggaan (Adjacency List):")
        for v in sorted(self.graph.keys()):
            print(f"{v}: {self.graph[v]}")
        print("="*40 + "\n")

    def dfs(self, start: str) -> list[str]:
        """Melakukan penelusuran Depth-First Search (iteratif)."""
        if start not in self.graph:
            print(f"Gagal: Vertex '{start}' tidak ditemukan.")
            return []

        visited = set()
        result = []
        stack = [start]

        while stack:
            node = stack.pop()
            if node not in visited:
                visited.add(node)
                result.append(node)
                # Urutan tetangga dibalik agar traversal konsisten dan deterministik
                for neighbor in reversed(sorted(self.graph[node])):
                    if neighbor not in visited:
                        stack.append(neighbor)

        print(f"DFS Traversal dari '{start}': {' -> '.join(result)}")
        return result

    def bfs(self, start: str) -> list[str]:
        """Melakukan penelusuran Breadth-First Search (iteratif)."""
        if start not in self.graph:
            print(f"Gagal: Vertex '{start}' tidak ditemukan.")
            return []

        visited = {start}
        result = []
        queue = [start]

        while queue:
            node = queue.pop(0)
            result.append(node)
            for neighbor in sorted(self.graph[node]):
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append(neighbor)

        print(f"BFS Traversal dari '{start}': {' -> '.join(result)}")
        return result


def main() -> None:
    """Fungsi utama untuk menjalankan antarmuka menu interaktif."""
    graf = Graph()
    print("Selamat datang, Igas. Silakan gunakan program berikut untuk manajemen graf.\n")

    while True:
        print("\n===== MENU GRAF =====")
        print("1. Tambah Vertex")
        print("2. Hapus Vertex")
        print("3. Tambah Edge")
        print("4. Hapus Edge")
        print("5. Tampilkan Graf (Matriks & Daftar)")
        print("6. Traversal DFS")
        print("7. Traversal BFS")
        print("8. Keluar")

        pilihan = input("Masukkan pilihan Anda (1-8): ").strip()

        if pilihan == '1':
            vertex = input("Masukkan nama vertex: ").strip()
            if vertex:
                graf.add_vertex(vertex)
            else:
                print("Input tidak valid. Nama vertex tidak boleh kosong.")

        elif pilihan == '2':
            vertex = input("Masukkan nama vertex yang akan dihapus: ").strip()
            if vertex:
                graf.remove_vertex(vertex)
            else:
                print("Input tidak valid.")

        elif pilihan == '3':
            v1 = input("Masukkan vertex asal: ").strip()
            v2 = input("Masukkan vertex tujuan: ").strip()
            if v1 and v2:
                graf.add_edge(v1, v2)
            else:
                print("Input tidak valid.")

        elif pilihan == '4':
            v1 = input("Masukkan vertex asal: ").strip()
            v2 = input("Masukkan vertex tujuan: ").strip()
            if v1 and v2:
                graf.remove_edge(v1, v2)
            else:
                print("Input tidak valid.")

        elif pilihan == '5':
            graf.display()

        elif pilihan == '6':
            start = input("Masukkan vertex awal untuk DFS: ").strip()
            if start:
                graf.dfs(start)
            else:
                print("Input tidak valid.")

        elif pilihan == '7':
            start = input("Masukkan vertex awal untuk BFS: ").strip()
            if start:
                graf.bfs(start)
            else:
                print("Input tidak valid.")

        elif pilihan == '8':
            print("Program telah berakhir. Terima kasih.")
            break

        else:
            print("Pilihan tidak valid. Silakan masukkan angka antara 1 hingga 8.")


if __name__ == "__main__":
    main()