#!/usr/bin/python
# -*- coding: utf-8 -*-
from WAEParser import parser
import sys


def eval(tree):
    exp = ''
    if tree[0] == 'num':
        exp = 'WAENode(nodeType="' + tree[0] + '",numValue=' \
            + str(tree[1]) + ')\n'
    elif tree[0] == 'id':
        exp = 'WAENode(nodeType="' + tree[0] + '",idValue="' \
            + str(tree[1]) + '")'
    elif tree[0] == '+' or tree[0] == '-' or tree[0] == '*' or tree[0] \
        == '/':
        t1 = eval(tree[1])
        t2 = eval(tree[2])
        exp = 'WAENode(nodeType="' + tree[0] + '",child1=' + str(t1) \
            + ',child2=' + str(t2) + ')'
    elif tree[0] == 'with':
        t_arr = []
        for val in tree[1]:
            t_arr.append('("' + str(val[0]) + '",' + eval(val[1]) + ')')
        t1 = ','.join(t_arr)
        t2 = eval(tree[2])
        exp = 'WAENode(nodeType="' + tree[0] + '",vars=List(' + str(t1) \
            + '),child1=' + str(t2) + ')'
    elif tree[0] == 'if':
        t1 = eval(tree[1])
        t2 = eval(tree[2])
        t3 = eval(tree[3])
        exp = 'WAENode(nodeType="' + tree[0] + '",child1=' + str(t1) \
            + ',child2=' + str(t2) + ',child3=' + str(t3) + ')'
    return exp


def main():
    filename = sys.argv[1]
    with open(filename) as fp:
        waes = fp.readlines()
    file = open(r"Driver.scala", 'a')
    lines = ['object Driver extends App { \n', '''import WAE._

''',
             'val d = Map[String,Double]() \n']
    for l in lines:
        file.write(l)
    for (idx, wae) in enumerate(waes):
        try:
            tree = parser.parse(wae)
            if tree is not None:
                exp = eval(tree)
                file.write('val e' + str(idx) + ' = ' + exp)
                file.write('\n')
                file.write('println(eval(e' + str(idx) + ',d))')
                file.write("\n \n")
            else:
                print("else")
        except Exception as abcd:
            print(abcd)
            break

    file.write('} \n')
    file.close()


main()
