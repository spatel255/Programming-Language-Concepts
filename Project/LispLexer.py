import ply.lex as lex
tokens=['RPAREN' , 'PLUS' , 'MINUS' , 'TIMES' , 'DIV', 'car', 'cdr', 'cons', 'let', 'var', 'NUM', 'LPAREN']
t_PLUS=r'\+'
t_MINUS=r'\-'
t_DIV=r'\/'
t_TIMES=r'\*'
t_LPAREN=r'\('
t_RPAREN=r'\)'
def t_var(t):
    r'[a-zA-Z][_a-zA-Z0-9]*'
    t.type='var'
    return t
def t_NUM(t):
    r'[0-9][0-9]*'
    t.type='NUM'
    return tx
def t_car(t):
	r'[Cc][Aa][Rr]'
	t.type='car'
	return t
def t_cdr(t):
	r'[Cc][Dd][Rr]'
	t.type='cdr'
	return t
def t_cons(t):
	r'[Cc][Oo][Nn][Ss]'
	t.type='cons'
	return t
def t_let(t):
	r'[Ll][Ee][Tt]'
	t.type='let'
	return t

def t_error(t):
	print("Illegal Character '%s'"%t.value[0])
	raise Exception('Lexer ERROR')
