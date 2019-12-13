package com.wanari.jwt.example.authserver

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.wanari.jwt.example.authserver.api.{JwtApi, JwtAuthentication}
import com.wanari.jwt.example.authserver.config.Config

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object Main extends App {
  private implicit lazy val system: ActorSystem                        = ActorSystem("auth-server")
  private implicit lazy val executionContext: ExecutionContextExecutor = system.dispatcher

  bindApi().onComplete {
    case Success(_) =>
      println("Application startup successful!")
    case Failure(exception) =>
      println("Application startup failed!", exception)
      system.terminate()
  }

  def bindApi(): Future[Http.ServerBinding] = {

    val config = new Config()

    val jwtService = new JwtAuthentication(config.jwtConf)

    val jwtApi = new JwtApi(jwtService)

    def routes: Route = {
      jwtApi.generateJwt
    }

    val bind = Http()
      .bindAndHandle(routes, "0.0.0.0", 8080)

    bind.onComplete {
      case Success(_)  => println("Listening on port 8080!")
      case Failure(ex) => println("Api binding failed!", ex)
    }
    bind
  }
}
