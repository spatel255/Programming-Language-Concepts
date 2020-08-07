#!/usr/bin/python
# -*- coding: utf-8 -*-
from WAE2DriverParser import parser
import sys


def eval(root):
                           aspect = ''
                           if root[0] == 'num':
                                                      aspect = 'WAENode(nodeType="' + root[0] + '",numValue=' \
                                                                                 + str(root[1]) + ')\n'
                           if root[0] == '+' or root[0] == '-' or root[0] == '*' or root[0] \
                                                      == '/':
                                                      s1 = eval(root[1])
                                                      s2 = eval(root[2])
                                                      aspect = 'WAENode(nodeType="' + root[0] + '",\nchild1=' + str(s1) \
                                                                                 + ',\nchild2=' + str(s2) + ')\n'
                           if root[0] == 'id':
                                                      aspect = 'WAENode(nodeType="' + root[0] + '",\nidValue="' \
                                                                        + str(root[1]) + '")\n'
#wae : LBRACE IF wae wae wae RBRACE 
                           if root[0] == 'if':
                                                      s1 = eval(root[1])
                                                      aspect = 'WAENode(nodeType="' + root[1] + '",\nchild1=' + str(s1) + ')\n'
                           if root[0] == 'with':
                                                      t_arr = []
                                                      for val in root[1]:
                                                                                                                                                                                                      t_arr.append('\n(\n"' + str(val[0]) + '",\n' + eval(val[1]) + '\n\n)\n')
                                                      s1 = ','.join(t_arr)
                                                      s2 = eval(root[2])
                                                      aspect = 'WAENode(nodeType="' + root[0] + '",\nvars=List(' \
                                                                                 + str(s1) + '),\nchild1=' + str(s2) + ')'
                           return aspect


def main():
                           fname = sys.argv[1]
                           with open(fname) as fp:
                                                      waes = fp.readlines()

                           f = open(r"Driver.scala", 'a')
                           sent = ['object Driver extends App { \n', '''import WAE._

''',
                                                                                 '''val d = Map[String,Double]()


''']
                           for a in sent:
                                                      f.write(a)

                           for abc, wae in enumerate(waes):
                                                      try:
                                                                 root = parser.parse(wae)
                                                                 if root is not None:
                                                                                 aspect = eval(root)
                                                                                 f.write("\n\n")
                                                                                 f.write("val e"+str(abc)+" = "+aspect)
                                                                                 f.write("\n\n")
                                                                                 f.write("println(eval(e"+str(abc)+",d))")
                                                                                 f.write("\n")
                                                                 else:
                                                                                 print("error")
                                                      except Exception as exc:
                                                                 print(exc)
                                                                 break

                           f.write('} ')
                           f.close()


main()
