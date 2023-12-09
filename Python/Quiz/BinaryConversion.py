
def main():
    count = 0
    for num in range (100, 1001):
        if (num % 30 == 0):
            if(count == 10):
                print()
                count = 0
            print(num, end=" ")
            count += 1
            
main()