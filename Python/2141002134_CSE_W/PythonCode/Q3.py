def covt2dec(st, b):
    result = 0
    i = 0
    for s in st[::-1]:
        if not s.isdigit():
            s = s.upper()
            
            if (ord(s) < 65 or ord(s) > 70):
                raise Exception('INVALID INPUT!!')
            
            result += (ord(s) - 55) * b ** i

            
        else:
            result += int(s) * b ** i
        i += 1
    
    return result

def covt_base(st, b1, b2):
    no = covt2dec(st, b1)
    res = ''
    while no != 0:
        n = no % b2
        
        if n > 9:
            res = chr(n + 55) + res
            
        else:
            res = str(n) + res
            
        no = no // b2
    
    return res

def main():
    b1 = int(input('Enter the base of the given number: '))
    assert b1 > 0 and b1 < 17
    s = input('Enter the new number: ')
    b2 = int(input('Enter the base of the number for conversion: '))
    assert b2 > 0 and b2 < 17
    if b1 == b2:
        print(f'The new converted number is {s}')
    else:
        print(f'The new converted number is {covt_base(s, b1, b2)}')

main()