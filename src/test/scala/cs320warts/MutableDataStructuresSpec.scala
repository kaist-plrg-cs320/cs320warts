package cs320warts

import org.wartremover.test.WartTestTraverser
import org.scalatest.flatspec.AnyFlatSpec

class MutableDataStructuresSpec extends AnyFlatSpec {

  "Wart" should "not disable List" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = List(1)
    }
    assert(result.errors.isEmpty)
  }

  "Wart" should "not disable case classes" in {
    val result = WartTestTraverser(MutableDataStructures) {
      case class A()
    }
    assert(result.errors.isEmpty)
  }

  "Wart" should "not disable case objects" in {
    val result = WartTestTraverser(MutableDataStructures) {
      case object A
      A
    }
    assert(result.errors.isEmpty)
  }

  "Wart" should "disable collection.mutable.Buffer" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = collection.mutable.Buffer(1)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable mutable.Buffer" in {
    val result = WartTestTraverser(MutableDataStructures) {
      import collection.mutable
      val _ = mutable.Buffer(1)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable Buffer" in {
    val result = WartTestTraverser(MutableDataStructures) {
      import collection.mutable.Buffer
      val _ = Buffer(1)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable to(Buffer)" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = List(1).to(collection.mutable.Buffer)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable toBuffer" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = List(1).toBuffer
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable StringBuilder" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = List(1).addString(null)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable Iterator" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = List(1).combinations(1)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable Array" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = Array(1)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable toArray" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = List(1).toArray
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable java.util" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = java.util.Arrays.asList(1)
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable CollectionConverters" in {
    val result = WartTestTraverser(MutableDataStructures) {
      import scala.jdk.CollectionConverters._
      val _ = List(1).asJava
    }
    assert(result.errors.nonEmpty)
  }

  "Wart" should "disable TrieMap" in {
    val result = WartTestTraverser(MutableDataStructures) {
      val _ = collection.concurrent.TrieMap()
    }
    assert(result.errors.nonEmpty)
  }

}
