import math
from re import X
from tkinter import N

def gcd(a,b):
    gcd = math.gcd(a,b)
    return gcd

def moduinv(a,b):
    mi = pow(a,-1,b)
    return mi

def coprime(a):
    l = []
    for x in range(2,a):
        if(gcd(a,x) == 1 and moduinv(x,a) != None):
            l.append(x)
    for x in l:
        if(x == moduinv(x,a)):
            l.remove(x)
    return l

def encrypt_block(m):
    x = m ** e % n
    return x
def decrypt_encrypt(n):
    y = n ** d % n
    return y

def encrypt_string(es):
    return ''.join([chr(encrypt_block(ord(x))) for x in list(es)])

def encrypt_string(ds):
    return ''.join([chr(encrypt_block(ord(x))) for x in list(ds)])

p = int(input("Enter the 1st prime number: "))
q = int(input("Enter the 2nd prime number: "))

n = p*q

phi = (p-1)* (q-1)
co = coprime(phi)
print(str(coprime(phi)))
se = int(input("Select the coprime"))
e = co[se]
print(e)

d = moduinv(e,phi)

s = input("Enter a message to encrypt: ")

ese = encrypt_string(s)

dse = encrypt_string(ese)

print(ese)

print(s)