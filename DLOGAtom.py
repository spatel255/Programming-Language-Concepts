from DLOGAtomParser import parser

def read_input():
    result = " "
    while True:
        data = input ('Atom: ').strip()
        if ';' in data:
            i = data.index(';')
            result += data[0:i+1]
            break
        else:
            result += data + ' '
    return result

def main():
    while True:
        data = input ("DLOGAtom: ")
        if data == 'exit':
            break
        try:
            tree = parser.parse(data)
        except Exception as inst:
            print(inst.args[0])
            continue
        print(tree)

main()
