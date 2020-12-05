package Days.Day3

import scala.annotation.tailrec

case class Puzzle(input: List[String]) {

  case class Slope(stepsRight: Int, stepsDown: Int = 1) {
    private val puzzleArray: List[Array[Char]] = input.map(_.toCharArray)

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
        if (stepsDown > 1) skipLines(stepsDown, puzzleArray, Nil).reverse
        else puzzleArray

      val (count, _) = filteredLines.foldLeft(0L, 0) {
        case ((count, index), line) =>
          val newIndex =
            if (line.length <= index) index - line.length else index
          line(newIndex) match {
            case s: Char if s == '#' => (count + 1, newIndex + stepsRight)
            case _                   => (count, newIndex + stepsRight)
          }
      }
      count
    }
  }

  val slope1: Long = Slope(1).countTrees()
  val slope2: Long = Slope(3).countTrees()
  val slope3: Long = Slope(5).countTrees()
  val slope4: Long = Slope(7).countTrees()
  val slope5: Long = Slope(1, 2).countTrees()

  def answerPart1: Long = slope2
  def answerPart2: BigInt = slope1 * slope2 * slope3 * slope4 * slope5
}
