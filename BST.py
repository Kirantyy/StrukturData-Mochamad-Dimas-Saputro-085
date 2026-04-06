import csv
import os

class Node:
    def __init__(self, id, nama):
        self.id = id
        self.nama = nama
        self.left = None
        self.right = None

class BST:
    def __init__(self):
        self.root = None
        self.count = 0

    # INSERT
    def insert(self, root, id, nama):
        if root is None:
            self.count += 1
            return Node(id, nama)
        if id < root.id:
            root.left = self.insert(root.left, id, nama)
        elif id > root.id:
            root.right = self.insert(root.right, id, nama)
        return root

    # SEARCH
    def search(self, root, id):
        if root is None or id == root.id:
            return root
        if id < root.id:
            return self.search(root.left, id)
        return self.search(root.right, id)

    # DELETE
    def delete(self, root, id):
        if root is None:
            return root
        if id < root.id:
            root.left = self.delete(root.left, id)
        elif id > root.id:
            root.right = self.delete(root.right, id)
        else:
            if root.left is None:
                self.count -= 1
                return root.right
            elif root.right is None:
                self.count -= 1
                return root.left
            temp = self.minValue(root.right)
            root.id = temp.id
            root.nama = temp.nama
            root.right = self.delete(root.right, temp.id)
        return root

    def minValue(self, node):
        current = node
        while current.left:
            current = current.left
        return current

    # TRAVERSAL
    def inorder(self, root):
        if root:
            self.inorder(root.left)
            print(f"{root.id} - {root.nama}")
            self.inorder(root.right)

    def preorder(self, root):
        if root:
            print(f"{root.id} - {root.nama}")
            self.preorder(root.left)
            self.preorder(root.right)

    def postorder(self, root):
        if root:
            self.postorder(root.left)
            self.postorder(root.right)
            print(f"{root.id} - {root.nama}")

    # LOAD CSV - VERSI ANTI-ERROR UNTUK TITIK KOMA
    def load_csv(self, filename):
        if not os.path.exists(filename):
            print(f"❌ File '{filename}' tidak ditemukan di folder ini!")
            return

        try:
            # PENTING: encoding='utf-8-sig' dan delimiter=';'
            with open(filename, mode='r', encoding='utf-8-sig') as file:
                reader = csv.DictReader(file, delimiter=';')
                
                added = 0
                for row in reader:
                    try:
                        # Kita ambil ID dan Nama. Jika kolomnya "ID" atau "id" tetap terbaca
                        id_val = int(row['ID'])
                        nama_val = row['Nama']
                        self.root = self.insert(self.root, id_val, nama_val)
                        added += 1
                    except (ValueError, KeyError):
                        continue
                
                print(f"✅ Berhasil memuat {added} data dari {filename}!")

        except Exception as e:
            print(f"❌ Error saat membaca file: {e}")

# MAIN PROGRAM
bst = BST()

while True:
    print("\n=== MENU BST BARANG ===")
    print("1. Insert Data")
    print("2. Search Data")
    print("3. Delete Data")
    print("4. Inorder (Terurut)")
    print("5. Preorder")
    print("6. Postorder")
    print("7. Load dari CSV")
    print("8. Exit")

    pilih = input("Pilih menu (1-8): ")

    if pilih == "1":
        try:
            id_in = int(input("ID: "))
            nama_in = input("Nama: ")
            bst.root = bst.insert(bst.root, id_in, nama_in)
            print("Data berhasil ditambahkan.")
        except ValueError:
            print("ID harus angka!")

    elif pilih == "2":
        try:
            id_in = int(input("Cari ID: "))
            hasil = bst.search(bst.root, id_in)
            if hasil:
                print(f"Ditemukan: {hasil.id} - {hasil.nama}")
            else:
                print("Tidak ditemukan.")
        except ValueError:
            print("ID harus angka!")

    elif pilih == "3":
        try:
            id_in = int(input("Hapus ID: "))
            bst.root = bst.delete(bst.root, id_in)
            print("Proses hapus selesai.")
        except ValueError:
            print("ID harus angka!")

    elif pilih == "4":
        print("\n--- DATA INORDER ---")
        bst.inorder(bst.root)

    elif pilih == "5":
        print("\n--- DATA PREORDER ---")
        bst.preorder(bst.root)

    elif pilih == "6":
        print("\n--- DATA POSTORDER ---")
        bst.postorder(bst.root)

    elif pilih == "7":
        file_name = input("Masukkan nama file (contoh: data.csv): ")
        bst.load_csv(file_name)

    elif pilih == "8":
        print("Program keluar. Sampai jumpa!")
        break