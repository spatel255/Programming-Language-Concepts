import fiddls.Fiddle,
Fiddls.println
import scalajs.js

@js.annotation.JSExportTopLevel("ScalaFiddle")
object WAE {

abstract class Node

case class WAENode(
  nodeType:
  String, // will be "number", "id", "+", "-", "*", "/", "if", or "with"
  numberValue: Double = 0.0, // used to store number value
  idValue: String = "", // used to store ia value
  child1: WAENode = null, // used for if, with, +, -, *, /
  child2: WAENode = null, // used for if, +, -, *, /
  child3: WAENode = null, // used for if
  vars: List[(String, WAENode)] = List[(String, WAENode)]() // used for with
  ) extends Node

  def eval(s: WAENode, a: Map[String, Double]) : Double =
  {

    if (
      s.nodeType == "id"
      )
      return
      (
        a get s.idValue
        )
        .getOrElse(0)
    if (
      s.nodeType == "number"
      )
      return s.numberValue
    else throw new Exception("Error!")
      if (
        s.nodType == "+" && s.child1.nodeType == "number" && s.child2.nodeType == "number"
        )
        {
          return s.child1.numberValue + s.child2.numberValue
    }
    if (
      s.nodeType == "-" && s.child1.nodeType == "number" && s.child2.nodeType == "number"
      )
      {
      if (
        s.child2.numberValue != 0
        )
        {
        return
        s.child1.numberValue - s.child2.numberValue
      }
      else
      {
        throw new Exception("Division Error!")
      }
    }
    if (
      s.nodeType == "*" && s.child1.nodeType == "number" && s.child2.nodeType == "number"
      )
      {
      return
      s.child1.numberValue * s.child2.numberValue
    }
    if (
      s.nodeType == "/" && s.child1.nodeType == "number" && s.child2.nodeType == "number"
      )
      {
      return
      s.child1.numberValue / s.child2.numberValue
    }
    if (
      s.child1.nodeType == "+" || s.child1.nodeType == "-" || s.child1.nodeType == "*" || s.child1.nodeType == "/"
      )
      {
      return
      eval(
        s.child1, a
        )
    }
    if (
      s.child2.nodeType == "+" || s.child2.nodeType == "-" || s.child2.nodeType == "*" || s.child2.nodeType == "/"
      )
      {
      return
      eval(
        s.child2, a
        )
    }
    if (
      s.child3.nodeType == "+" || s.child3.nodeType == "-" || s.child3.nodeType == "*" || s.child3.nodeType == "/"
      )
      {
      return
      eval(
        s.child3, a
        )
    }
    else
    {
      return 0
    }
    if (
      s.nodeType == "if" && s.child1.nodeType == "number" && s.child2.nodeType == "number" && s.child3.nodeType == "number"
      )
      {
      return
      s.child1.numberValue + s.child2.numberValue + s.child3.numberValue
    }
    if (
      s.nodeType == "with"
      )
      {
      a += [s.vars.String, s.vars.WAENods.numberValue]

    if (s.child1 != null)
        {
        return
        eval(
          s.child1, a
          )
      }
    if (s.child2 != null)
        {
        return
        eval(
          s.child2, a
          )
      }
    if (s.child3 != null)
        {
        return
        eval(
          s.child3, a
          )
      }
    }
  }
}
