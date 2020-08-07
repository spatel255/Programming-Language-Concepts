import ply.lex as lex

tokens = ('NAME', 'LBRACE', 'RBRACE', 'COMMA', 'NUMBER', 'SEMI', 'STRING', 'LBRACKET','RBRACKET','SEMI','QUOT', 'COLON')

t_LBRACE = r'\{'
t_RBRACE = r'\}'
t_COMMA = r'\,'
t_SEMI = r';'
t_COLON = r':'
t_LBRACKET = '\['
t_RBRACKET = '\]'
t_QUOT = r'""'


def t_STRING(t):
     r'"(\\.|[^"\\])*\"'
     t.type = 'STRING'
     return t

def t_NUMBER(t):
	r'[-+]?[0-9]+(\.([0-9]+)?)?'
	t.value = float(t.value)
	t.type = 'NUMBER'
	return t

def t_NAME(t):
  r'[a-zA-Z_][_a-zA-Z0-9]*'
  t.type = 'NAME'
  return t




#Ignored Characters
t_ignore = " \r\n\t"
t_ignore_COMMENT = r'\#.*'

def t_error(t) :
	print("Illegal character '%s'" % t.value[0])
	t.lexer.skip(1)

lexer = lex.lex()

# Test it out
data = '''
#	[1,2,3];
	#'''

 # Give the lexer some input
lexer.input(data)

 # Tokenize
while True:
     tok = lexer.token()
     if not tok:
         break      # No more input
     print(tok)
