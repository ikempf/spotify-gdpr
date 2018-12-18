package com

package object ikempf {

  implicit class EitherOps[A, B](either: Either[A, B]) {
    def getR: B =
      either.fold(e => throw new RuntimeException(e.toString), identity)
  }

}
