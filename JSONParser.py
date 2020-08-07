import ply.yacc as yacc
from JSONLexer import tokens

''''
arg : NUMBER
arg : STRING
arg : NAME

atom : NAME LPAREN args RPARN
args : arg
args : args COMMA arg
'''
def p_atom(p):
    'atom : NAME LPAREN args RPAREN'
    p[0] = [p[1], p[3]]

def p_arg_1(p):
    'arg : NUMBER'
    p[0] = ['num', p[1]]

def p_arg_2(p):
    'arg : STRING'
    p[0] = ['name', p[1]]

def p_arg_3(p):
    'arg : NAME'
    p[0] = ['name', p[1]]

def p_args_1(p):
    'args : arg'
    p[0] = p[1]

def p_args_2(p):
    'args : args COMMA arg'
    p[0] = p[1] + [p[3]]

def p_error(p):
    print("Syntex error in input!")

parser = yacc.yacc()