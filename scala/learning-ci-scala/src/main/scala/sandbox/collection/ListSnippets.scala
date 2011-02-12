package sandbox.collection

object Input {
  val list = List("1", "foo", "var", "baz")
}

object ConsElementAndList extends Application {
  println("extra" :: Input.list) // List(extra, 1, foo, var, baz)
  println(Input.list :: "extra" :: Nil) // List(List(1, foo, var, baz), extra)
  println(("extra" :: Input.list.reverse).reverse) // List(1, foo, var, baz, extra)
}

object ConcatenateListAndList extends Application {
  println(Input.list ::: List("extra")) // List(1, foo, var, baz, extra)
}

object DropRight extends Application {
  println(Input.list.dropRight(1)) // List(1, foo, var)
  println(Input.list.dropRight(2)) // List(1, foo)
}

object DropWhile extends Application {
  println(Input.list.dropWhile(str => str.length <= 1)) // List(foo, var, baz)
}

object Exists extends Application {
  println(Input.list.exists(str => str.length > 1)) // true
  println(Input.list.exists(str => str.length > 10)) // false
  println(Input.list.exists(str => str == "foo")) // true
}

object Filter extends Application {
  println(Input.list.filter(str => str.length > 1)) // List(foo, var, baz)
}

object FilterNot extends Application {
  // List#remove is deprecated -> filterNot
  println(Input.list.filterNot(str => str.length < 2)) // List(foo, var, baz)
}

object FindReturnsFirstElement extends Application {
  println(Input.list.find(str => str.length > 1)) // Some(foo)
}

object FlatMap extends Application {
  println(Input.list.flatMap(str => str)) // List(1, f, o, o, v, a, r, b, a, z)
}

object Flatten extends Application {
  println(Input.list.flatten) // List(1, f, o, o, v, a, r, b, a, z)
}

object Foreach extends Application {
  Input.list foreach (str => print(str + ",")) // 1,foo,var,baz,
}

object Indices extends Application {
  println(Input.list.indices) // Range(0, 1, 2, 3)
}

object RangeDoesNotContainsEnd extends Application {
  println(List.range(1, 4)) // List(1, 2, 3)
}

object Intersect extends Application {
  println(Input.list.intersect(List("foo"))) // List(foo)
}

case class StringWrapper(str: String)

object Map extends Application {
  println(Input.list.map {
    str => StringWrapper(str + str)
  })
  // List(StringWrapper(11), StringWrapper(foofoo), StringWrapper(varvar), StringWrapper(bazbaz))
  println(Input.list.map {
    str => str.length
  })
  // List(1, 3, 3, 3)
}

object Partition extends Application {
  println(Input.list.partition {
    str => str.length < 2
  })
  // (List(1),List(foo, var, baz))
  println(Input.list.partition {
    str => str.length > 2
  })
  // (List(foo, var, baz),List(1))
}

object ReduceRight extends Application {
  println(List(1, 2, 3, 4).reduceRight {
    (v1, v2) => {
      print("(" + v1 + "," + v2 + "), ")
      v1 + v2
    }
  })
  // (3,4), (2,7), (1,9), 10
}

object ReduceLeft extends Application {
  println(List(1, 2, 3, 4).reduceLeft {
    (v1, v2) => {
      print("(" + v1 + "," + v2 + "), ")
      v1 + v2
    }
  })
  // (1,2), (3,3), (6,4), 10
}

object Distinct extends Application {
  // removeDuplicates is deprecated
  val list = Input.list ::: List("foo")
  println(list.distinct) // List(1, foo, var, baz)
}

object Reverse extends Application {
  println(Input.list.reverse) // List(baz, var, foo, 1)
  println(Input.list.reverse_:::(List("hoge", "fuga"))) // List(fuga, hoge, 1, foo, var, baz)
  println(Input.list.reverse ::: List("hoge", "fuga")) // List(baz, var, foo, 1, hoge, fuga)
}

object SliceDoesNotContainsEnd extends Application {
  println(Input.list.slice(1, 3)) // List(foo, var)
}

object Sort extends Application {
  println(List("abc", "1", "de").sortBy(str => str.length)) // List(1, de, abc)
  println(List("abc", "1", "de").sortWith((s1, s2) => s1.length < s2.length)) // List(1, de, abc)
  println(List("abc", "1", "de").sortWith((s1, s2) => s1.length > s2.length)) // List(abc, de, 1)
}

object Count extends Application {
  println(Input.list.count(str => str.length > 2)) // 3
}

object SplitAt extends Application {
  println(Input.list.splitAt(2)) // (List(1, foo),List(var, baz))
}

object HeadAndTail extends Application {
  println(Input.list.head) //  1
  println(Input.list.tail) // List(foo, var, baz)
}

object Take extends Application {
  println(Input.list.take(2)) // List(1, foo)
}

object TakeRight extends Application {
  println(Input.list.takeRight(2)) // List(var, baz)
}

object Diff extends Application {
  println(Input.list diff List("foo", "var")) // List(1, baz)
  println(List("foo", "var") diff Input.list) // List()
  println(List(2, 3) diff List(1, 3)) // List(2)
}

object Union extends Application {
  println(Input.list.union(List("extra")))
}

object Zip extends Application {
  // List("1", "foo", "var", "baz")
  println(Input.list.zip(List(1, 2, 3))) // List((1,1), (foo,2), (var,3))
  println(List(1, 2, 3).zipAll(List(1, 2, 3, 4, 5), 888, 999)) // List((1,1), (2,2), (3,3), (888,4), (888,5))
  println(List(1, 2, 3).zipAll(List(1, 2), 888, 999)) // List((1,1), (2,2), (3,999))
  println(List(1, 2, 3).zipAll(List(1, 2, 3, 4, 5), 888, 999)) // List((1,1), (2,2), (3,3), (888,4), (888,5))
}

object MinMax extends Application {
  println(Input.list.min) // 1
  println(Input.list.max) // var
  println(List(100,2,20).min) // 2
  println(List(100,2,20).max) // 100
}

object Patch extends Application {
  println(Input.list.patch(1,List(1,2,3),0)) // List(1, 1, 2, 3, foo, var, baz)
  println(Input.list.patch(1,List(1,2,3),1)) // List(1, 1, 2, 3, var, baz)
  println(Input.list.patch(1,List(1,2,3),2)) // List(1, 1, 2, 3, baz)
}