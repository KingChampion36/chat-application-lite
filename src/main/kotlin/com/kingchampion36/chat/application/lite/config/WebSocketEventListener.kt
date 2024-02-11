package com.kingchampion36.chat.application.lite.config

import com.kingchampion36.chat.application.lite.model.ChatMessage
import com.kingchampion36.chat.application.lite.model.MessageType
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEventListener(
  private val messageTemplate: SimpMessageSendingOperations
) {

  private val log = KotlinLogging.logger { }

  @EventListener
  fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
    val headerAccessor: StompHeaderAccessor = StompHeaderAccessor.wrap(event.message)
    val username = headerAccessor.sessionAttributes?.get("username")?.toString()
    if (username != null) {
      log.info { "User disconnected $username" }
      val chatMessage = ChatMessage(type = MessageType.LEAVE, sender = username, content = null)
      messageTemplate.convertAndSend("/topic/public", chatMessage)
    }
  }
}
