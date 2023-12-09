def main():
    no = input("Enter the number in Binary Format: ")
    result = 0
    i = 0
    for s in no[::-1]:
        n = int(s)
        result += n * pow(2, i)
        i += 1
    print(result)

main()