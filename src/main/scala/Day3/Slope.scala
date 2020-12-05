package Day3

import scala.annotation.tailrec

import Input._

case class Slope(stepsRight: Int, stepsDown: Int = 1) {
  private val maxInputLengthBeforeRepeating: Int = firstLineOfPuzzleInput.length
  private val puzzleArray: Array[Array[Char]] =
    puzzleInput.toCharArray.grouped(maxInputLengthBeforeRepeating).toArray

  def countTrees(): Long = {
    @tailrec
    def skipLines(
        i: Int,
        lst: List[Array[Char]],
        acc: List[Array[Char]]
    ): List[Array[Char]] =
      (i, lst) match {
        case (_, Nil)     => acc
        case (1, _ :: xs) => skipLines(stepsDown, xs, acc)
        case (i, x :: xs) => skipLines(i - 1, xs, x :: acc)
      }

    val filteredLines =
      if (stepsDown > 1) skipLines(stepsDown, puzzleArray.toList, Nil).reverse
      else puzzleArray.toList

    val (count, _) = filteredLines.foldLeft(0L, 0) {
      case ((count, index), line) => {
        val newIndex = if (line.length <= index) index - line.length else index
        line(newIndex) match {
          case s: Char if (s == '#') => (count + 1, newIndex + stepsRight)
          case _                     => (count, newIndex + stepsRight)
        }
      }
    }
    count
  }
}

object Slope {
  val slope1 = Slope(1)
  val slope2 = Slope(3)
  val slope3 = Slope(5)
  val slope4 = Slope(7)
  val slope5 = Slope(1, 2)

  val answerPart1: Long = slope2.countTrees()
  val answerPart2: BigInt =
    slope1.countTrees() * slope2.countTrees() * slope3.countTrees() * slope4
      .countTrees() * slope5.countTrees()
}
