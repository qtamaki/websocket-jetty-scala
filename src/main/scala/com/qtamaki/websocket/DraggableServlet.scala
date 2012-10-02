package com.qtamaki.websocket

import scala.collection.mutable.HashSet
import scala.collection.mutable.SynchronizedSet
import javax.servlet.http._
import org.eclipse.jetty.websocket.WebSocket
import org.eclipse.jetty.websocket.WebSocketServlet
import org.eclipse.jetty.websocket.WebSocket.Connection
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DraggableServlet extends WebSocketServlet {
  val logger = LoggerFactory.getLogger(classOf[DraggableServlet]);
  val clients = new HashSet[DraggableWebSocket] with SynchronizedSet[DraggableWebSocket]

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse):Unit = {
logger.info(">> doGet")
    getServletContext.getNamedDispatcher("default").forward(req, resp)
  }

  override def doWebSocketConnect(req:HttpServletRequest, protocol:String ):DraggableWebSocket = {
logger.info(">> doWebSocketConnect")
    new DraggableWebSocket
  }

  class DraggableWebSocket extends WebSocket.OnTextMessage {

    var connection:Connection = _

    override def onMessage(data:String) = {
logger.info(">> onMessage: " + data)
      clients.foreach{c =>
        try{ c.connection.sendMessage(data)}catch{case e:Exception =>logger.error(e.toString);}
        }
logger.info("<< onMessage")
     }

    override def onOpen(connection:Connection) = {
logger.info(">> onOpen")
      this.connection = connection
      clients add this
     }

    override def onClose(closeCode:Int, message:String) = {
logger.info(">> onClose")
      clients remove this
     }
  }
}

