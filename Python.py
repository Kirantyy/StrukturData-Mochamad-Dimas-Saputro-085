import time
import os
import msvcrt # Khusus Windows untuk deteksi tekan tombol tanpa pause

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None

class NewsTicker:
    def __init__(self):
        self.head = None
        self.size = 0

    def insert_news(self, data):
        new_node = Node(data)
        if not self.head:
            self.head = new_node
            self.head.next = self.head
            self.head.prev = self.head
        else:
            tail = self.head.prev
            tail.next = new_node
            new_node.prev = tail
            new_node.next = self.head
            self.head.prev = new_node
        self.size += 1

    def delete_news(self, pos):
        if self.head is None or pos < 1 or pos > self.size:
            print("Nomor tidak valid!")
            return
        
        curr = self.head
        for _ in range(pos - 1):
            curr = curr.next
        
        if self.size == 1:
            self.head = None
        else:
            curr.prev.next = curr.next
            curr.next.prev = curr.prev
            if curr == self.head:
                self.head = curr.next
        self.size -= 1

    def run_simulation(self, forward=True):
        if not self.head:
            print("Berita kosong!")
            return
        
        print("\n>>> SIMULASI JALAN (Tekan sembarang tombol untuk STOP) <<<")
        curr = self.head if forward else self.head.prev
        
        while True:
            # \r untuk menimpa baris yang sama, end='' agar tidak ganti baris
            print(f"\r>>> NEWS: {curr.data}                ", end="", flush=True)
            
            # Tunggu 3 detik, tapi cek apakah user menekan tombol
            start_time = time.time()
            while time.time() - start_time < 3:
                if msvcrt.kbhit(): # Cek jika ada tombol ditekan
                    msvcrt.getch() # Bersihkan buffer
                    print("\n[Sistem] Simulasi dihentikan.")
                    return
            
            curr = curr.next if forward else curr.prev

# --- Main Program ---
ticker = NewsTicker()
while True:
    print("\n=== MENU NEWS ===")
    print("1. Insert\n2. Hapus\n3. Forward\n4. Backward\n5. Exit")
    choice = input("Pilih: ")
    
    if choice == '1':
        text = input("Berita: ")
        ticker.insert_news(text)
    elif choice == '2':
        idx = int(input("Hapus nomor: "))
        ticker.delete_news(idx)
    elif choice == '3':
        ticker.run_simulation(forward=True)
    elif choice == '4':
        ticker.run_simulation(forward=False)
    elif choice == '5':
        break