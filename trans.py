import math,pyperclip

message = input("Enter plaintext: ")
key = int(input("Enter key: "))

ciphertext = [''] * key

for column in range(key):
    pointer = column
    while(pointer < len(message)):
        ciphertext[column] += message[pointer]
        pointer += key

print(''.join(ciphertext))

message = input("Enter plaintext: ")
key = int(input("Enter key: "))

noCols = int(math.ceil(len(message) / key))
noRows = key
noShade = (noCols * noRows) - len(message)
column = 0
row = 0
text = [''] * noCols

for symbol in message:
    text[column] += symbol
    column += 1
    if((column == noCols) or(column == noCols - 1) and row >= noRows - noShade):
        column = 0
        row += 1
    

print(''.join(text))
