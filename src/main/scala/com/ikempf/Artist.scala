package com.ikempf

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class Artists(items: List[Artist], next: Option[String], total: Int)

object Artists {
  implicit val decoder: Decoder[Artists] = deriveDecoder
}

case class Artist(name: String)

object Artist {
  implicit val decoder: Decoder[Artist] = deriveDecoder
}
