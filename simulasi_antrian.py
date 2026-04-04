import tkinter as tk
from tkinter import messagebox, ttk
from gtts import gTTS
import pygame
import os
import time

class Node:
    def __init__(self, nomor, nama):
        self.nomor = nomor
        self.nama = nama
        self.next = None

class QueueAntrian:
    def __init__(self):
        self.front = None
        self.rear = None

    def enqueue(self, nomor, nama):
        new_node = Node(nomor, nama)
        if self.rear is None:
            self.front = self.rear = new_node
            return
        self.rear.next = new_node
        self.rear = new_node

    def dequeue(self):
        if self.front is None:
            return None
        temp = self.front
        self.front = self.front.next
        if self.front is None:
            self.rear = None
        return temp

class AppAntrian:
    def __init__(self, root):
        self.root = root
        self.root.title("Sistem Antrian Sekolah - FST")
        self.root.geometry("400x500")
        
        self.antrian = QueueAntrian()
        self.counter = 1
        
        # Inisialisasi Audio
        pygame.mixer.init()

        # --- UI DESIGN ---
        main_frame = ttk.Frame(root, padding="20")
        main_frame.pack(fill=tk.BOTH, expand=True)

        ttk.Label(main_frame, text="SISTEM ANTRIAN SEKOLAH", font=("Helvetica", 14, "bold")).pack(pady=10)
        
        ttk.Label(main_frame, text="Nama Pendaftar:").pack(anchor=tk.W)
        self.entry_nama = ttk.Entry(main_frame, font=("Helvetica", 12))
        self.entry_nama.pack(fill=tk.X, pady=5)
        
        btn_frame = ttk.Frame(main_frame)
        btn_frame.pack(pady=10)

        ttk.Button(btn_frame, text="Ambil Antrian", command=self.tambah_antrian).pack(side=tk.LEFT, padx=5)
        ttk.Button(btn_frame, text="Panggil Antrian", command=self.panggil_antrian).pack(side=tk.LEFT, padx=5)
        
        ttk.Label(main_frame, text="Daftar Antrian Saat Ini:", font=("Helvetica", 10, "italic")).pack(anchor=tk.W, pady=(10,0))
        
        # Listbox untuk tampilan yang lebih rapi
        self.listbox = tk.Listbox(main_frame, font=("Courier", 11), height=10)
        self.listbox.pack(fill=tk.BOTH, expand=True, pady=5)

    def tambah_antrian(self):
        nama = self.entry_nama.get().strip()
        if nama:
            self.antrian.enqueue(self.counter, nama)
            messagebox.showinfo("Sukses", f"Nomor Antrian: {self.counter}\nNama: {nama}")
            self.counter += 1
            self.entry_nama.delete(0, tk.END)
            self.update_tampilan()
        else:
            messagebox.showwarning("Peringatan", "Nama tidak boleh kosong!")

    def update_tampilan(self):
        self.listbox.delete(0, tk.END)
        curr = self.antrian.front
        while curr:
            self.listbox.insert(tk.END, f" No. {curr.nomor:02d} | {curr.nama}")
            curr = curr.next

    def panggil_antrian(self):
        data = self.antrian.dequeue()
        if data:
            teks = f"Nomor antrian {data.nomor}, {data.nama}, silakan ke loket."
            self.update_tampilan()
            # Panggil suara
            self.play_voice(teks, data.nomor)
            messagebox.showinfo("Panggilan", teks)
        else:
            messagebox.showwarning("Kosong", "Tidak ada antrian yang tersisa!")

    def play_voice(self, text, nomor):
        try:
            # 1. Hentikan suara yang sedang jalan & lepas file sebelumnya
            if pygame.mixer.music.get_busy():
                pygame.mixer.music.stop()
            pygame.mixer.music.unload() 

            # 2. Buat file dengan nama unik (supaya tidak bentrok Permission Denied)
            filename = f"panggil_{nomor}.mp3"
            tts = gTTS(text=text, lang='id')
            tts.save(filename)
            
            # 3. Putar file baru
            pygame.mixer.music.load(filename)
            pygame.mixer.music.play()
            
            # (Opsional) Hapus file lama setelah beberapa detik jika ingin folder bersih
            # Tapi untuk sekarang biarkan saja agar tidak error lagi
        except Exception as e:
            print(f"Error Suara: {e}")

if __name__ == "__main__":
    root = tk.Tk()
    # Style agar tampilan lebih modern
    style = ttk.Style()
    style.theme_use('clam') 
    
    app = AppAntrian(root)
    root.mainloop()