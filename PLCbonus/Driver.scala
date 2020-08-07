object Driver extends App { 
import WAE._

val d = Map[String,Double]()




val e0 = WAENode(nodeType="num",numValue=45)


println(eval(e0,d))


val e1 = WAENode(nodeType="+",
child1=WAENode(nodeType="num",numValue=2)
,
child2=WAENode(nodeType="num",numValue=3)
)


println(eval(e1,d))


val e2 = WAENode(nodeType="with",
vars=List(
(
"x",
WAENode(nodeType="num",numValue=2)


)
,
(
"y",
WAENode(nodeType="num",numValue=3)


)
),
child1=WAENode(nodeType="+",
child1=WAENode(nodeType="id",
idValue="x")
,
child2=WAENode(nodeType="id",
idValue="y")
)
)

println(eval(e2,d))
} 