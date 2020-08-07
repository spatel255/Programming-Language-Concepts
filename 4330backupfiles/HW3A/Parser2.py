import sys
from Lexer
import *

next_token = None
l = None
# expr# Parses strings in the language generated by the rules: # < expr > - > < expr > + < term > # < expr > - > < expr > - < term > # < expr > - > < term >
  def lisp():
  global next_token
global l
print("Enter lisp")

if next_token.get_token().value == TokenTypes.LPAREN.value:
  next_token = l.lex()
while next_token.get_token().value == TokenTypes.ADD.value or\
next_token.get_token().value == TokenTypes.SUB.value or\
next_token.get_token().value == TokenTypes.MUL.value or\
next_token.get_token().value == TokenTypes.DIV.value:
  next_token = l.lex()
lisp()
next_token.l.lex()
while next_token.get_token().value == TokenTypes.CAR.value:
  next_token.l.lex()
list()
if next_token.get_token().value == TokenTypes.RPAREN.value:
  next_token = l.lex()
else :
  error("RPAREN error")
sys.exit(-1)
else :
  error("LPAREN error")
sys.exit(-1)
if next_token.get_token.value == TokenTypes.INT.value:
  next_token.lex()
lisp()
print("Exit lisp")

def list():
  global next_token
global l
print("Enter list")
if next_token.get_token().value == TokenTypes.LPAREN.value:
  next_token = l.lex()
list()
if next_token.get_token().value == TokenTypes.RPAREN.value:
  next_token = l.lex()
list()
print("Exit list")

def seq():
  global next_token
global l
print("Enter seq")
while next_token.get_token().value == TokenTypes.INT.value:
  next_token = l.lex()
if next_token.get_token().value != TokenTypes.RPAREN.value:
  seq()
print("Exit seq")

def main():
  global next_token
global l
l = Lexer(sys.argv[1])
next_token = l.lex()
lisp()
if next_token.get_token().value == TokenTypes.EOF.value:
  print("PARSE SUCCEEDED")
else :
  print("PARSE FAILED")
