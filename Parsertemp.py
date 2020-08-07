import sys
next = None
src = ""
level = 0

def P():
    global src
    scan()
    E()
    if next != '$':
        error(1)
    else:
        sys.stdout.write("ACCEPT, ")
        sys.stdout.write("SOURCE:" +
          src + "\n")
        src = ""

def E():
    enter('E')
    T()
    while next == '+':
        scan()
        T()
    leave('E')

def T():
    enter('T')
    F()
    while next == '*':
        scan()
        F()
    leave('T')

def F():
    enter('F')
    if next.isalnum(): # alphanum
        scan()
    elif next == '(':
        scan()
        E()
        if next == ')':
            scan()
        else:
            error(2)
    else:
        error(3)
    leave('F')

def error(n):
    sys.stdout.write("ERROR:%i, SOURCE:%s\n" %
      (n, src))
    sys.exit(1)

def getch():
    c = sys.stdin.read(1)
    if len(c) > 0:
        return c
    else:
        return None

def scan():
    global next
    global src
    next = getch()
    while next.isspace():
        next = getch()
    src += next

def enter(name):
    global level
    spaces(level)
    level += 1
    sys.stdout.write("+-%c: Enter, \t" % name)
    sys.stdout.write("Next == " + str(next) +
      "\n")

def leave(name):
    global level
    level -= 1
    spaces(level)
    sys.stdout.write("+-%c: Leave, \t" % name)
    sys.stdout.write("Next == %c\n" % next)

def spaces(local_level):
    while local_level > 0:
        local_level -= 1
        sys.stdout.write("| ")

while True:
    P()
