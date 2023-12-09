def hex2int(st) :
    result = 0
    i = 0
    for s in st[::-1]:
        if not s.isdigit():
            s = s.upper()
            
            if (ord(s) < 65 or ord(s) > 70):
                raise Exception('INVALID INPUT!!')
            
            result += (ord(s) - 55) * 16 ** i

            
        else:
            result += int(s) * 16 ** i
        i += 1
        
    print(f'The decimal conversion of {st} is {result}')
    
def int2hex(s):
    temp = s
    result = ''
    while(s != 0):
        no = s % 16
        
        if no > 9:
            result = chr(no + 55) + result
            
        else:
            result = str(no) + result
            
        s = s // 16
    
    print(f'The hexadecimal conversion of {temp} is {result}')

def main():
    n1 = int(input('Enter a decimal number: '))
    st = input('Enter a hexadecimal number: ')
    int2hex(n1)
    hex2int(st)

main()