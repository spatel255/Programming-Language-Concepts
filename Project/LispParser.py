import ply.yacc as yacc
from LispLexer import tokens

def p_start_1(p):
  'start : lisp SEMI'
  p[0] = ['lisp',p[1]]

def p_start_2(p):
  'start : list SEMI'
  p[0] = ['list',p[1]]
def p_lisp_1(p):
	'lisp:NUM'
	p[0]=['num',p[1]]
def p_lisp_2(p):
	'lisp:VAR'
	p[0]=['var',p[1]]
def p_lisp_3(p):
	'lisp : LPAREN PLUS lisp lisp RPAREN'
	p[0]=['+',p[3],p[4]]
def p_lisp_4(p):
	'lisp : LPAREN MINUS lisp lisp RPAREN'
	p[0]=['-',p[3],p[4]]
def p_lisp_5(p):
	'lisp : LPAREN TIMES lisp lisp RPAREN'
	p[0]=['*',p[3],p[4]]
def p_lisp_6(p):
	'lisp : LPAREN DIV lisp lisp RPAREN'
	p[0]=['/',p[3],p[4]]
def p_lisp_7(p):
	'lisp : LPAREN CAR list RPAREN'
	p[0]=['car',p[3]]
def p_lisp_8(p):
	'lisp : LPAREN LET LPAREN lisp lisp RPAREN RPAREN'
	p[0]=['let',p[4],p[5]]
def p_list_1(p):
	'list : lisp list'
	p[0]=[p[1],p[2]]
def p_list_2(p):
	'list : LPAREN CDR list RPAREN'
	p[0]=['cdr',p[3]]
def p_seq_1(p):
	'seq  : lisp'
	p[0]=['lisp',p[1]]
def p_seq_2(p):
	'seq  : lisp seq'
	p[0]=[p[1],p[2]]
def p_list_3(p):
	'list : LPAREN CONS lisp list RPAREN'
	p[0]=[p[3],p[4]]


def p_error(p):
	print("Syntax error in input")

parser = yacc.yacc()
