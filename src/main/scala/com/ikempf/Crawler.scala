package com.ikempf
import com.softwaremill.sttp.quick._

object Crawler extends App {

  sttp.get(uri"http://httpbin.org/ip").send()


}
