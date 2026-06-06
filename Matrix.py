def display_matrix(matrix):
    for row in matrix:
        print(" ".join(f"{x:4}" for x in row))
    print()


def input_matrix():
    rows = int(input("Enter number of rows: "))
    cols = int(input("Enter number of columns: "))

    matrix = []

    print(f"Please enter {rows * cols} integer elements row by row:")

    for i in range(rows):
        row = list(map(int, input(
            f"Row {i+1} ({cols} elements separated by spaces): "
        ).split()))

        while len(row) != cols:
            print(f"Please enter exactly {cols} elements.")
            row = list(map(int, input().split()))

        matrix.append(row)

    return matrix


def sort_row_wise(matrix):
    return [sorted(row) for row in matrix]


def sort_col_wise(matrix):
    rows = len(matrix)
    cols = len(matrix[0])

    result = [row[:] for row in matrix]

    for c in range(cols):
        col = sorted(result[r][c] for r in range(rows))

        for r in range(rows):
            result[r][c] = col[r]

    return result


def rotate_clockwise_1(matrix):
    return [row[-1:] + row[:-1] for row in matrix]


def rotate_counter_clockwise_1(matrix):
    return [row[1:] + row[:1] for row in matrix]


def rotate_90(matrix):
    return [list(row) for row in zip(*matrix[::-1])]


def rotate_180(matrix):
    return [row[::-1] for row in matrix[::-1]]


def row_wise_traversal(matrix):
    result = []
    for row in matrix:
        result.extend(row)

    print("Row-wise traversal:", result)


def column_wise_traversal(matrix):
    result = []

    for c in range(len(matrix[0])):
        for r in range(len(matrix)):
            result.append(matrix[r][c])

    print("Column-wise traversal:", result)


def print_spiral(matrix):
    top = 0
    bottom = len(matrix) - 1
    left = 0
    right = len(matrix[0]) - 1

    result = []

    while top <= bottom and left <= right:

        for i in range(left, right + 1):
            result.append(matrix[top][i])
        top += 1

        for i in range(top, bottom + 1):
            result.append(matrix[i][right])
        right -= 1

        if top <= bottom:
            for i in range(right, left - 1, -1):
                result.append(matrix[bottom][i])
            bottom -= 1

        if left <= right:
            for i in range(bottom, top - 1, -1):
                result.append(matrix[i][left])
            left += 1

    print("Spiral traversal:", result)


def transpose_matrix(matrix):
    return [list(row) for row in zip(*matrix)]


def main():
    matrix = None

    while True:
        print("\n--- MENU ---")
        print("1-a. Sort the matrix row-wise")
        print("1-b. Sort the matrix column-wise")
        print("2-a. Rotate Matrix Clockwise by 1")
        print("2-b. Rotate Matrix Counter-Clockwise by 1")
        print("2-c. Rotate a matrix by 90")
        print("2-d. Rotate a matrix by 180")
        print("3-a. Row-wise traversal of matrix")
        print("3-b. Column-wise traversal of matrix")
        print("4. Print matrix in spiral form")
        print("5. Transpose matrix")
        print("6. Quit")

        choice = input("Enter your choice: ").lower().replace("-", "")

        if choice == "6":
            print("Program terminated.")
            break

        if matrix is None:
            print("\nNo matrix initialized. Please create one first.")
            matrix = input_matrix()

        if choice == "1a":
            matrix = sort_row_wise(matrix)
            display_matrix(matrix)

        elif choice == "1b":
            matrix = sort_col_wise(matrix)
            display_matrix(matrix)

        elif choice == "2a":
            matrix = rotate_clockwise_1(matrix)
            display_matrix(matrix)

        elif choice == "2b":
            matrix = rotate_counter_clockwise_1(matrix)
            display_matrix(matrix)

        elif choice == "2c":
            matrix = rotate_90(matrix)
            display_matrix(matrix)

        elif choice == "2d":
            matrix = rotate_180(matrix)
            display_matrix(matrix)

        elif choice == "3a":
            row_wise_traversal(matrix)

        elif choice == "3b":
            column_wise_traversal(matrix)

        elif choice == "4":
            print_spiral(matrix)

        elif choice == "5":
            matrix = transpose_matrix(matrix)
            display_matrix(matrix)

        else:
            print("Invalid choice.")


if __name__ == "__main__":
    main()