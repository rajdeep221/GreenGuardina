
def main():
    n = int(input('Enter the number N: '))
    str1 = ""
    while n != 0:
        if n % 2 == 0:
            str1 = '0' + str1
        else:
            str1 = '1' + str1
        n = n // 2
    print(str1)
            
main()