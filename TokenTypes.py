import enum

class TokenTypes(enum.Enum):
  LPAREN = 1
  RPAREN = 2
  ADD = 3
  SUB = 4
  MUL = 5
  DIV = 6
  ID = 7
  INT = 8
  EOF = 0
