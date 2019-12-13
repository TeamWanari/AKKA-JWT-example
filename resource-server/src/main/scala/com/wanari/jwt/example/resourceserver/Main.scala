package com.wanari.jwt.example.resourceserver

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.wanari.jwt.example.resourceserver.api.SecretApi

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

    val secretApi = new SecretApi()

    def routes: Route = {
      secretApi.getSecret
    }

    val bind = Http()
      .bindAndHandle(routes, "0.0.0.0", 8081)

    bind.onComplete {
      case Success(_)  => println("Listening on port 8081!")
      case Failure(ex) => println("Api binding failed!", ex)
    }
    bind
  }
}
