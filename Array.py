# Kapasitas maksimum array
MAX = 10

# Array statis untuk NIM dan Nama
nim = [None] * MAX
nama = [None] * MAX

# Menyimpan jumlah data aktif
count = 0

# ================= INSERT AT BEGINNING =================
def insert_beginning():
    global count

    if count == MAX:
        print("Array penuh")
        return

    # Geser data ke kanan
    for i in range(count, 0, -1):
        nim[i] = nim[i - 1]
        nama[i] = nama[i - 1]

    nim[0] = input("Masukkan NIM: ")
    nama[0] = input("Masukkan Nama: ")

    count += 1

# ================= INSERT AT POSITION =================
def insert_position():
    global count

    if count == MAX:
        print("Array penuh")
        return

    pos = int(input("Posisi: "))

    if pos < 0 or pos > count:
        print("Posisi tidak valid")
        return

    for i in range(count, pos, -1):
        nim[i] = nim[i - 1]
        nama[i] = nama[i - 1]

    nim[pos] = input("Masukkan NIM: ")
    nama[pos] = input("Masukkan Nama: ")

    count += 1

# ================= INSERT AT END =================
def insert_end():
    global count

    if count == MAX:
        print("Array penuh")
        return

    nim[count] = input("Masukkan NIM: ")
    nama[count] = input("Masukkan Nama: ")

    count += 1

# ================= DELETE FROM BEGINNING =================
def delete_beginning():
    global count

    if count == 0:
        return

    # Geser data ke kiri
    for i in range(count - 1):
        nim[i] = nim[i + 1]
        nama[i] = nama[i + 1]

    count -= 1

# ================= DELETE GIVEN POSITION =================
def delete_position():
    global count

    pos = int(input("Posisi dihapus: "))

    if pos < 0 or pos >= count:
        return

    for i in range(pos, count - 1):
        nim[i] = nim[i + 1]
        nama[i] = nama[i + 1]

    count -= 1

# ================= DELETE FROM END =================
def delete_end():
    global count

    if count > 0:
        count -= 1

# ================= DELETE FIRST OCCURRENCE =================
def delete_first_occurrence():
    global count

    key = input("NIM yang dihapus: ")

    for i in range(count):
        if nim[i] == key:
            for j in range(i, count - 1):
                nim[j] = nim[j + 1]
                nama[j] = nama[j + 1]

            count -= 1
            return

    print("Data tidak ditemukan")

# ================= SHOW DATA =================
def show_data():
    for i in range(count):
        print(f"{i}. {nim[i]} - {nama[i]}")

# ================= MAIN MENU =================
while True:
    print("\n1.Insert Beginning")
    print("2.Insert Position")
    print("3.Insert End")
    print("4.Delete Beginning")
    print("5.Delete Position")
    print("6.Delete End")
    print("7.Delete First Occurrence")
    print("8.Show Data")
    print("9.Exit")

    choice = int(input("Pilih: "))

    if choice == 1:
        insert_beginning()
    elif choice == 2:
        insert_position()
    elif choice == 3:
        insert_end()
    elif choice == 4:
        delete_beginning()
    elif choice == 5:
        delete_position()
    elif choice == 6:
        delete_end()
    elif choice == 7:
        delete_first_occurrence()
    elif choice == 8:
        show_data()
    elif choice == 9:
        break
