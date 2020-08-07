from WAEParser import parser

def read_input():
  result = ''
  while True:
    data = input('WAE: ').strip()
    if ';' in data:
      i = data.index(';')
      result += data[0:i+1]
      break
    else:
      result += data + ' '
  return result

def main():
  while True:
    data = read_input()
    if data == 'exit;':
      break
    try:
      if parser.parse(data):
        print("ACCEPT: CAN EVALUATE")
      else:
        print("REJECT: CANNOT EVALUATE")
    except Exception as inst:
      print(inst.args[0])
      continue

main()
