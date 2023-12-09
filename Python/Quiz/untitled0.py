import sys

def isPrime(i):
    for num in range(2, i // 2 + 1):
        if i % num == 0:
            return False
    return True

def main():
    n = sys.argv[1]
    count = 0
    for i in range (2, n):
        if (isPrime(i)):
            print(i)
            count += 1
    print('No. of prime numbers are ', count)
            
main()