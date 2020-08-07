Sample run of bonus, I used WAE as a filename.\
(base) Shilps-MacBook-Pro:~ shilp$ cd downloads\
(base) Shilps-MacBook-Pro:downloads shilp$ cd PLCbonus\
(base) Shilps-MacBook-Pro:PLCbonus shilp$ more in.dat\
45;\
\{+ 2 3\};\
\{with \{\{x 2\}\{y 3\}\} \{+ x y\}\};\
(base) Shilps-MacBook-Pro:PLCbonus shilp$ python3 WAE.py in.dat > Driver.scala\
Generating LALR tables\
(base) Shilps-MacBook-Pro:PLCbonus shilp$ more Driver.scala\
object Driver extends App \{ \
import WAE._\
\
val d = Map[String,Double]() \
val e0 = WAENode(nodeType="num",numValue=45.0)\
\
println(eval(e0,d))\
 \
val e1 = WAENode(nodeType="+",child1=WAENode(nodeType="num",numValue=2.0)\
,child2=WAENode(nodeType="num",numValue=3.0)\
)\
println(eval(e1,d))\
 \
val e2 = WAENode(nodeType="with",vars=List(("x",WAENode(nodeType="num",numValue=2.0)\
),("y",WAENode(nodeType="num",numValue=3.0)\
)),child1=WAENode(nodeType="+",child1=WAENode(nodeType="id",idValue="x"),child2=WAENode(nodeType="id",idValue="y")))\
println(eval(e2,d))\
 \
\} \
(base) Shilps-MacBook-Pro:PLCbonus shilp$ }