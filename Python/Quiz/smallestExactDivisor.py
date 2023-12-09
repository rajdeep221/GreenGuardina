import math

def main():
    x =int(input('Enter the value of x'))
    n = int(input('Enter the number'))
    sum = 0
    arg = 1
    for i in range(0, n+1, 2):
        sum += arg * pow(x, i) / math.factorial(i)
        arg *= -1
    print(sum)
        
main()