package com.wanari.jwt.example.authserver.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.wanari.jwt.example.authserver.api.JwtApi._
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

class JwtApi(authentication: JwtAuthentication) {
  val generateJwt: Route = path("jwt") {
    entity(as[CreateJwtApiRequest]) { request =>
      request.username match {
        case "The detonator" =>
          val token = authentication.generateToken(request.username)
          complete(CreateJwtApiResponse(token))
        case _ =>
          complete("Sorry you are not 'The detonator'")
      }
    }
  }
}

object JwtApi {
  implicit val createJwtApiRequestFormatter: RootJsonFormat[CreateJwtApiRequest] = jsonFormat1(CreateJwtApiRequest)
  final case class CreateJwtApiRequest(
      username: String,
  )
  implicit val createJwtApiResponseFormatter: RootJsonFormat[CreateJwtApiResponse] = jsonFormat2(CreateJwtApiResponse)
  final case class CreateJwtApiResponse(
      token: String,
      tokenType: String = "Bearer",
  )
}
