import fiddls.Fiddle,
Fiddls.println
import scalajs.js

@js.annotation.JSExportTopLevel("ScalaFiddle")
object WAE {

  abstract class Node

  case class WAENode(
    nodeType: String,
    numValue: Double = 0.0,
    idValue: String = "",
    child1: WAENode = null,
    child2: WAENode = null,
    child3: WAENode = null,
    vars: List[(String,WAENode)] = List[(String,WAENode)]()
  ) extends Node

  def eval(e: WAENode, d: Map[String,Double]): Double = {
    if (e.nodeType == "num") e.numValue
    else if(e.nodeType == "id") {

      if(d.exists(x => x._1 == e.idValue)) d(e.idValue)
      else throw new Exception("Uninstantiated variable error")
    }
    else if(e.nodeType == "+")
    {
      eval(e.child1,d)+eval(e.child2,d)
    }
    else if(e.nodeType == "-")
    {
      eval(e.child1,d)-eval(e.child2,d)
    }
    else if(e.nodeType == "*")
    {
      eval(e.child1,d)*eval(e.child2,d)
    }
    else if(e.nodeType == "/")
    {
      if(eval(e.child2,d)!=0) eval(e.child1,d)/eval(e.child2,d)
      else throw new Exception("Divide by zero error")
    }
    else if(e.nodeType == "if")
    {
      if (eval(e.child1,d)!=0) eval(e.child2,d)
      else eval(e.child3,d)
    }
    else if(e.nodeType == "with")
    {
      //populate dictionary
      var a = d
      for(l<-e.vars)
        {
          a += (l._1 -> eval(l._2, d))
        }
      //println(a)
      eval(e.child1, a)
    }

    else throw new Exception("Illegal Syntax")
  }

}
