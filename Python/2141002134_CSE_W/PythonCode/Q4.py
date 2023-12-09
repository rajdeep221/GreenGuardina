import random

def main():
    n = random.randint(7, 11)
    res = ''
    for i in range (n):
        no = random.randint(33, 126)
        res += chr(no)
    print(res)

main()