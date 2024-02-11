package com.kingchampion36.chat.application.lite.controller

import com.kingchampion36.chat.application.lite.model.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller

@Controller
class ChatController {

  /**
   * Each time a chatMessage is received, it will be queued to '/topic/public' queue.
   */
  @MessageMapping("/chat.sendMessage")
  @SendTo("/topic/public")
  fun sendMessage(@Payload chatMessage: ChatMessage): ChatMessage {
    return chatMessage
  }

  @MessageMapping("/chat.addUser")
  @SendTo("/topic/public")
  fun addUser(@Payload chatMessage: ChatMessage, headerAccessor: SimpMessageHeaderAccessor): ChatMessage {
    // Add username in websocket session
    headerAccessor.sessionAttributes?.put("username", chatMessage.sender)
    return chatMessage
  }
}
