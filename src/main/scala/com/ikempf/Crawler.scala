package com.ikempf

import java.net.URI

import com.softwaremill.sttp.Uri
import com.softwaremill.sttp.quick._
import io.circe.parser.parse
import cats.implicits._

object Crawler extends App {

  val startUri = uri"https://api.spotify.com/v1/me/following?limit=50&type=artist&market=from_token"
  val token = ""

  def getArtists(uri: Uri) = {
    val resp =
      sttp
        .headers("authorization" -> s"Bearer $token")
        .get(uri)
        .send()
        .body
        .getR

    parse(resp)
      .getR
      .hcursor
      .downField("artists")
      .as[Artists]
      .getR
  }

  def listArtists(uri: Uri): List[Artist] = {
    println(s"Crawling $uri")
    val artists = getArtists(uri)
    artists.items ++ artists.next.foldMap(n => listArtists(Uri(URI.create(n))))
  }

  val artists = listArtists(startUri)

  println(artists.map(_.name))
  println(artists.size)

}
