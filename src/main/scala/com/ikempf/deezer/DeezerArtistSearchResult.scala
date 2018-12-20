package com.ikempf.deezer

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class DeezerArtistSearchResult(data: List[DeezerArtist])

object DeezerArtistSearchResult {
  implicit val decoder: Decoder[DeezerArtistSearchResult] = deriveDecoder
}

case class DeezerArtist(id: Int, name: String)

object DeezerArtist {
  implicit val decoder: Decoder[DeezerArtist] = deriveDecoder
}
