import scala.math.abs
import scala.math.cos

val tolerance = 0.01

def isCloseEnough(x: Double, y: Double) =
  abs((x - y)) < tolerance

def fixpoint(f: Double => Double)(firstGuess: Double, secondGuess: Double) = {
  def iterate(guess: Double, guess1: Double): Double = {
    val mid = (guess + guess1) / 2
    val next = f(mid)
    println(next)
    println(mid)
    if (isCloseEnough(guess, guess1)) guess
    else if (next < 0) iterate(mid, guess1)
    else iterate(guess, mid)
  }
  iterate(firstGuess, secondGuess)
}

def roots() = fixpoint(y => y * y * y * y - y - 10)(-2.0, 2.0)
def rootd() = fixpoint(y => cos(y) - y * math.exp(y))(-2.0, 2.0)

roots()
rootd()
