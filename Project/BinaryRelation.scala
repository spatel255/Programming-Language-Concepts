/*object Driver extends App {
  import BinaryRelation._

  val s1 = List((1, 5), (2, 8), (3, 9))
  val r1 = (s1 foldLeft emptyBinaryRelation)((r, e) => add(r, e))
  val r2 =
    (((0 to bound) zip (0 to bound)) foldLeft emptyBinaryRelation)((r, e) =>
      add(r, e)
    )
  val s3 = List((2, 5), (2, 8), (3, 10))
  val r3 = (s3 foldLeft emptyBinaryRelation)((r, e) => add(r, e))
  val s4 = List((1, 2), (2, 3), (3, 4), (4, 5))
  val r4 = (s4 foldLeft emptyBinaryRelation)((r, e) => add(r, e))
  val r5 =
    add(add(add(add(emptyBinaryRelation, (1, 5)), (5, 1)), (1, 1)), (5, 5))

  printBinaryRelation(r1)
  println(reflexive(r1))
  println(reflexive(r2))
  println(symmetric(r1))
  println(transitive(r1))
  printBinaryRelation(inverse(r1))
  printBinaryRelation(union(r1, r3))
  printBinaryRelation(symmetricClosure(r1))
  printBinaryRelation(reflexiveClosure(r1))
  printBinaryRelation(selfJoin(r4))
  printBinaryRelation(transitiveClosure(r4))

}
*/
object BinaryRelation {

    type BinaryRelation = (Int,Int) => Boolean
    type Element = (Int,Int)
    val bound = 99
//    r1 = { (1,5), (2,8), (3,9) }
//    r2 = { (1,2), (2,3), (3,4), (4,5) }
    // returns an empty binary relation, i.e. no pairs
    def emptyBinaryRelation: BinaryRelation = (a,b) => false

    // returns the universal binary relation, i.e. all pairs
    def universalBinaryRelation: BinaryRelation = (a, b) => true

    // returns true of e is in r, false otherwise
    def contains(r: BinaryRelation, e: Element): Boolean = {
        if (r(e._1, e._2))
        true
        else
        false
    }
    // returns true if r1 is a subset of r2, false otherwise
    def subRelation(r1: BinaryRelation, r2: BinaryRelation): Boolean = {
        def helper1(x: Int): Boolean = {
            def helper2(y: Int): Boolean = {
                if (y > bound) true
                else if (contains(r1,(x,y)) && !contains(r2,(x,y))) false
                else helper2(y+1)
            }
            if (x > bound) true
            else if (!helper2(0)) false
            else helper1(x+1)
        }
        helper1(0)
    }

    // returns true of r1 equals r2, false otherwise
    def equal(r1: BinaryRelation, r2: BinaryRelation): Boolean = {
        if
        (subRelation(r1, r2) == true && subRelation(r2, r1) == true)
        true
        else
        false
    }

    // returns a new binary relation obtained by adding e to r; if e already
    // is in r then returns r
    def add(r: BinaryRelation, e: Element): BinaryRelation =
        (a,b) => if ((a == e._1) && (b == e._2)) true else r(a,b)

    // for all a, (a,a) is in r
    def reflexive(r: BinaryRelation): Boolean = {
        def helper1(a: Int): Boolean = {
            if (a > bound)
            true
            else
            if (!contains(r, (a, a)))
            false
            else helper1(a + 1)
        }
        helper1(0)
    }

    // for all a,b, if (a,b) is in r then (b,a) is also in r
    def symmetric(r: BinaryRelation): Boolean = {
        def helper1(a: Int): Boolean = {
        def helper2(b: Int): Boolean = {
                if
                (b > bound)
                true
                else
                if
                (contains(r, (a, b)) && !contains(r, (b, a)))
                false
                else
                helper2(b + 1)
            }
            if
            (a > bound)
            true
            else
            if
            (!helper2(0))
            false
            else
            helper1(a + 1)
        }
        helper1(0)
    }

    // for all a,b,c, if (a,b) is in r and (b,c) is in r then (a,c) is also in r
    def transitive(r: BinaryRelation): Boolean = {
        def helper1(a: Int): Boolean = {
        def helper2(b: Int): Boolean = {
        def helper3(c: Int): Boolean = {
                if
                (c > bound)
                true
                else
                if
                (contains(r, (a, b)) && contains(r, (b, c)) && !contains(r,(a, c)))
                false
                else
                helper3(c + 1)
                }
                if
                (b > bound)
                true
                else
                if
                (!helper3(0))
                false
                else
                helper2(b + 1)
            }
            if
            (a > bound)
            true
            else
            if
            (!helper2(0))
            false
            else
            helper1(a + 1)
        }
        helper1(0)
    }

    // equivalence relation is reflexive, symmetric, and transitive
    def equivalence(r: BinaryRelation): Boolean = {
        if
        (reflexive(r) && symmetric(r) && transitive(r))
        true
        else
        false
    }

    // For all a,b, if a != b and (a,b) in r then (b,a) is not in r
    def antisymmetric(r: BinaryRelation): Boolean = {
        def helper1(a: Int) : Boolean = {
        def helper2(b: Int) : Boolean = {
                if
                (b > bound)
                true
                else
                if
                (a != b && contains(r, (a, b)) && contains(r, (b, a)))
                false
                else
                helper2(b + 1)
            }
            if
            (a > bound)
            true
            else
            if
            (!helper2(0))
            false
            else
            helper1(a + 1)
        }
        helper1(0)
    }

    // returns set union of r1 and r2
    def union(r1: BinaryRelation, r2: BinaryRelation): BinaryRelation = {
        val s1 = (for
                            (a <- 0 to bound; b <- 0 to bound)
                            yield
                            (a, b)
                         ).toList
        s1.foldLeft(emptyBinaryRelation)((r, e) =>
            if (contains(r1, (e._1, e._2)) || contains(r2, (e._1, e._2))) add(r, e)
            else r
        )
    }


    // returns inverse of r; inverse(r) = { (b,a) | (a,b) in r }
    def inverse(r: BinaryRelation): BinaryRelation = {
        val s1 = (for
                            (a <- 0 to bound; b <- 0 to bound)
                            yield (a, b)).toList
        s1.foldLeft(emptyBinaryRelation)((inverse, e) =>
            if
                                                                         (contains(r , (e._1, e._2))) add(inverse, (e._2, e._1))
                                                                         else
                                                                         inverse
        )
    }

    // look up definition in BinaryRelationNotes.pdf
    def reflexiveClosure(r: BinaryRelation): BinaryRelation = {
        val s1 = (for
                            (a <- 0 to bound)
                            yield
                            (a, a)
                         ).toList
        s1.foldLeft (emptyBinaryRelation)((r,e)=>add(r,e))
    }

    // look up definition in BinaryRelationNotes.pdf
    def symmetricClosure(r: BinaryRelation): BinaryRelation = {union(r, inverse(r))}

    // selfJoin will be useful in implementing transitiveClosure
    // returns a new relation { (a,c) | (a,b) is in r and (b,c) is in r }
    def selfJoin(r: BinaryRelation): BinaryRelation = {
        val s1 = (for (a <- 0 to bound;
                                     b <- 0 to bound
                                     if contains(r, (a, b)))
                            yield
                            (a, b)).toList
        val s2 = (for ((t1, q1) <- s1; (t2, q2) <- s1
                                     if
                                     (q1 == t2))
                            yield
                            (q1, t2)).toSet.toList
        s2.foldLeft (emptyBinaryRelation)((r1, e)=>add(r1, e))
    }

        // look up definition in BinaryRelationNotes.pdf


    // look up definition in BinaryRelationNotes.pdf
    def transitiveClosure(r: BinaryRelation): BinaryRelation = {val r1 = selfJoin(r)
                                                                                                                                            union(r, r1)}

    def toString(r: BinaryRelation): String = {
        val rs = for {
            i <- 0 to bound
            j <- 0 to bound
            if contains(r, (i, j))
        } yield ((i,j))
        rs.mkString("{", ",", "}")
    }

    def printBinaryRelation(r: BinaryRelation): Unit = {
        println(toString(r))
    }

}
