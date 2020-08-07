from LispParser import parser

def read_input():
  result = ''
  while True:
    data = input('LISP: ').strip()
    if ';' in data:
      i = data.index(';')
      result += data[0:i+1]
      break
    else:
      result += data + ' '
  return result

def main():
  while True:
    data = read_input().strip()
    if data == 'exit;':
      break
    try:
      print(data)
      if parser.parse(data):
        print("The value is "+t.get_value() )
      else:
        print("EVALUATION ERROR:"+t.get_value())
    except SyntaxError as inst1:
      print("SYNTAX ERROR"+t.get_value())
      continue
    except Exception as inst2:
      print(inst2.args[0])
      continue

main()
