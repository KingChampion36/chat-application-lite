package com.kingchampion36.chat.application.lite.model

data class ChatMessage(
  val content: String?,
  val sender: String,
  val type: MessageType
)

enum class MessageType {
  CHAT,
  JOIN,
  LEAVE
}
