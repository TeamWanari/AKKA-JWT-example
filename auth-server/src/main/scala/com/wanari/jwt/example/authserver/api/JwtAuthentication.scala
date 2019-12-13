package com.wanari.jwt.example.authserver.api

import com.wanari.jwt.example.authserver.api.JwtAuthentication.JwtPayload
import com.wanari.jwt.example.authserver.config.Config.JwtConf
import pdi.jwt.{JwtAlgorithm, JwtSprayJson}
import spray.json.DefaultJsonProtocol._
import spray.json._

class JwtAuthentication(jwtConf: JwtConf) {
  def generateToken(username: String): String = {
    val claim = JwtPayload(username).toJson.asJsObject
    JwtSprayJson.encode(claim, jwtConf.privateKey, JwtAlgorithm.RS512)
  }
}

object JwtAuthentication {
  implicit val jwtPayloadFormatter: RootJsonFormat[JwtPayload] = jsonFormat1(JwtPayload)
  final case class JwtPayload(
      username: String,
  )
}
