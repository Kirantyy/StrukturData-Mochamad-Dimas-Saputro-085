import random

class HashTable:
    def __init__(self, size=200):
        # DI SINI PERUBAHANNYA: Ukuran tabel diubah menjadi 150 slot
        self.size = size
        self.table = [None] * self.size

    def _hash_function(self, key):
        # Fungsi hash dasar: key mod size
        return key % self.size

    def insert(self, key):
        index = self._hash_function(key)
        start_index = index
        
        # Linear Probing: geser ke indeks berikutnya jika slot sudah terisi
        while self.table[index] is not None:
            if self.table[index] == key:
                return False  # Data duplikat tidak dimasukkan kembali
            
            index = (index + 1) % self.size
            if index == start_index:
                print("Error: Hash Table penuh!")
                return False
                
        self.table[index] = key
        return True

    def search(self, key):
        index = self._hash_function(key)
        start_index = index
        
        while self.table[index] is not None:
            if self.table[index] == key:
                return index
            
            index = (index + 1) % self.size
            if index == start_index:
                break
                
        return -1

    def delete(self, key):
        index = self.search(key)
        if index != -1:
            self.table[index] = None
            return True
        return False

    def display_all(self):
        # Menampilkan seluruh data dari indeks 0 sampai 149
        print("\n================ TAMPILAN SELURUH HASH TABLE ================")
        filled_count = 0
        
        for i in range(self.size):
            if self.table[i] is not None:
                print(f"Slot {i:03d} -> [ {self.table[i]} ]")
                filled_count += 1
            else:
                print(f"Slot {i:03d} -> Kosong (None)")
                
        print(f"=============================================================")
        print(f"[INFO] Total slot Terisi: {filled_count} / {self.size}")


# ==================== MAIN PROGRAM ====================
if __name__ == "__main__":
    # Inisialisasi Hash Table dengan ukuran 150
    hash_table = HashTable(size=200)

    # Generate 100 angka acak yang unik (dibatasi rentang 1 - 200)
    print("Mengisi 100 data acak unik (rentang 1-2000) ke dalam Hash Table...")
    random_numbers = set()
    while len(random_numbers) < 100:
        random_numbers.add(random.randint(1, 200))

    # Masukkan 100 angka acak tersebut ke hash table menggunakan Linear Probing
    for num in random_numbers:
        hash_table.insert(num)

    # Menu Interaktif
    while True:
        # Menampilkan seluruh isi tabel di setiap awal perulangan menu
        hash_table.display_all()
        
        print("\n=== MENU HASH TABLE ===")
        print("1. INPUT DATA")
        print("2. HAPUS DATA")
        print("3. CARI DATA")
        print("4. KELUAR")
        
        pilihan = input("Pilih menu (1-4): ")
        
        if pilihan == '1':
            try:
                angka = int(input("Masukkan angka baru: "))
                if hash_table.insert(angka):
                    print(f"Berhasil! Angka {angka} telah dimasukkan.")
                else:
                    print(f"Gagal! Angka {angka} sudah ada atau table penuh.")
            except ValueError:
                print("Input harus berupa angka numerik!")
                
        elif pilihan == '2':
            try:
                angka = int(input("Masukkan angka yang ingin dihapus: "))
                if hash_table.delete(angka):
                    print(f"Berhasil! Angka {angka} telah dihapus.")
                else:
                    print(f"Data {angka} tidak ditemukan di dalam table.")
            except ValueError:
                print("Input harus berupa angka numerik!")
                
        elif pilihan == '3':
            try:
                angka = int(input("Masukkan angka yang dicari: "))
                posisi = hash_table.search(angka)
                if posisi != -1:
                    print(f"Data {angka} DITEMUKAN pada Slot/Indeks ke-{posisi}.")
                else:
                    print(f"Data {angka} TIDAK DITEMUKAN.")
            except ValueError:
                print("Input harus berupa angka numerik!")
                
        elif pilihan == '4':
            print("Terima kasih! Program selesai.")
            break
        else:
            print("Pilihan menu tidak valid, silakan coba lagi.")
        
        print("-" * 40)