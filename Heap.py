import heapq
import csv

# ======================================
# CLASS DATA
# ======================================
class Data:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama

    def __lt__(self, other):
        return self.id < other.id

    def __repr__(self):
        return f"ID: {self.id}, Nama: {self.nama}"


# ======================================
# HEAP
# ======================================
min_heap = []
max_heap = []


# ======================================
# LOAD DATA DARI FILE CSV
# ======================================
def load_data(filename):

    try:
        with open(filename, mode='r', encoding='utf-8-sig') as file:

            # Karena file pakai ;
            reader = csv.DictReader(file, delimiter=';')

            print("HEADER CSV:", reader.fieldnames)

            for row in reader:

                # Sesuaikan dengan header CSV
                id = int(row['ID'])
                nama = row['Nama']

                data = Data(id, nama)

                # MIN HEAP
                heapq.heappush(min_heap, data)

                # MAX HEAP
                heapq.heappush(max_heap, (-id, nama))

        print("Data berhasil dimuat ke Min Heap dan Max Heap!\n")

    except FileNotFoundError:
        print("File tidak ditemukan!\n")

    except KeyError:
        print("Header CSV salah!")
        print("Pastikan header file: ID;Nama\n")

    except Exception as e:
        print("Terjadi error:", e)


# ======================================
# INSERT DATA
# ======================================
def insert_data():

    id = int(input("Masukkan ID : "))
    nama = input("Masukkan Nama : ")

    data = Data(id, nama)

    # Tambah ke Min Heap
    heapq.heappush(min_heap, data)

    # Tambah ke Max Heap
    heapq.heappush(max_heap, (-id, nama))

    print("Data berhasil ditambahkan!\n")


# ======================================
# TAMPILKAN ASCENDING
# MIN HEAP
# ======================================
def show_ascending():

    if not min_heap:
        print("Min Heap kosong!\n")
        return

    print("\n===== DATA ASCENDING (MIN HEAP) =====")

    temp = min_heap.copy()

    while temp:
        data = heapq.heappop(temp)
        print(data)

    print()


# ======================================
# TAMPILKAN DESCENDING
# MAX HEAP
# ======================================
def show_descending():

    if not max_heap:
        print("Max Heap kosong!\n")
        return

    print("\n===== DATA DESCENDING (MAX HEAP) =====")

    temp = max_heap.copy()

    while temp:
        id, nama = heapq.heappop(temp)
        print(f"ID: {-id}, Nama: {nama}")

    print()


# ======================================
# DELETE MIN HEAP
# ======================================
def delete_min():

    if min_heap:

        data = heapq.heappop(min_heap)

        print("Data berhasil dihapus dari Min Heap")
        print(data)
        print()

    else:
        print("Min Heap kosong!\n")


# ======================================
# DELETE MAX HEAP
# ======================================
def delete_max():

    if max_heap:

        id, nama = heapq.heappop(max_heap)

        print("Data berhasil dihapus dari Max Heap")
        print(f"ID: {-id}, Nama: {nama}")
        print()

    else:
        print("Max Heap kosong!\n")


# ======================================
# TAMPILKAN HEAP ASLI
# ======================================
def show_heap():

    print("\n===== MIN HEAP =====")
    print(min_heap)

    print("\n===== MAX HEAP =====")
    print(max_heap)

    print()


# ======================================
# MAIN PROGRAM
# ======================================

filename = input("Masukkan nama file CSV : ")
load_data(filename)

while True:

    print("========== MENU ==========")
    print("1. Insert Data")
    print("2. Show Ascending (Min Heap)")
    print("3. Show Descending (Max Heap)")
    print("4. Delete Min Heap")
    print("5. Delete Max Heap")
    print("6. Show Heap")
    print("7. Exit")
    print("==========================")

    pilih = input("Pilih menu : ")

    # ==================================
    # INSERT
    # ==================================
    if pilih == "1":
        insert_data()

    # ==================================
    # SHOW ASC
    # ==================================
    elif pilih == "2":
        show_ascending()

    # ==================================
    # SHOW DESC
    # ==================================
    elif pilih == "3":
        show_descending()

    # ==================================
    # DELETE MIN
    # ==================================
    elif pilih == "4":
        delete_min()

    # ==================================
    # DELETE MAX
    # ==================================
    elif pilih == "5":
        delete_max()

    # ==================================
    # SHOW HEAP
    # ==================================
    elif pilih == "6":
        show_heap()

    # ==================================
    # EXIT
    # ==================================
    elif pilih == "7":
        print("Program selesai.")
        break

    # ==================================
    # INVALID
    # ==================================
    else:
        print("Menu tidak valid!\n")