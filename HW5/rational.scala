import scala.math.abs

object Rational {

type Rational = (Int,Int)

def makeRational(n:Int, d:Int): Rational = that(n, 1)

def num(that:Rational):Int = n / g * d.signum
def this(n: Int) = this(n, 1)
def signum = new Rational(num.signum)
def denom(that:Rational):Int = d.abs / g

def addRational(that: Rational): Rational = new Rational(num * that.denom + that.num * denom, denom * that.denom)
def subRational(that: Rational): Rational = this + (-that)
def mulRational(that: Rational): Rational = new Rational(this.num * that.num, this.denom * that.denom)
def divRational(that: Rational): Rational = this * that.inverse
def equals(that: Rational) = this.num.toFloat / this.denom == that.num.toFloat / that.denom

def to_string(r: Rational): String = "(" + num + ", " + den + ")"
override def toString = num + (if (denom == 1) "" else ("/" + denom))
def printRational(r: Rational): Unit = System.out.print(n);
}
