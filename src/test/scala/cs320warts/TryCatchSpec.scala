package cs320warts

import org.wartremover.test.WartTestTraverser
import org.scalatest.flatspec.AnyFlatSpec

class TryCatchSpec extends AnyFlatSpec {

  "Wart" should "disable try-catch" in {
    val result = WartTestTraverser(TryCatch) {
      try {
      } catch {
        case x: Exception =>
      }
    }
    assert(result.errors.nonEmpty)
  }

}
