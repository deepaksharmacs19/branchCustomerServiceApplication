package com.branch.app;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.branch.app.ChatMessagePojo.MessageType;

@Controller
public class ChatController {

	private static ReadCSV reader=new ReadCSV();	

	@MessageMapping("/sendCSVMessage.{username}")
    @SendTo("/web/public.{username}")
    public ChatMessagePojo sendCSVMessage(@Payload ChatMessagePojo chatMessagePojo) {
		String str=reader.getLine();
		String s[]=null;
		if(str.length()>0) { 
			s=str.split(",");
			if(s!=null) chatMessagePojo.setContent(s[2]);
		}
		
		if(chatMessagePojo.getType()==MessageType.CHAT) {
			if(s!=null) chatMessagePojo.setSender("Customer (ID : "+s[0]+")");
		}

		else chatMessagePojo.setSender("Customer");
			
			
        return chatMessagePojo;
    }

	
	
    @MessageMapping("/sendMessage.{username}")
    @SendTo("/web/public.{username}")
    public ChatMessagePojo sendMessage(@Payload ChatMessagePojo chatMessagePojo) {
        return chatMessagePojo;
    }

    @MessageMapping("/adduser.{username}")
    @SendTo("/web/public.{username}")
    public ChatMessagePojo addUser(@Payload ChatMessagePojo chatMessagePojo, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessagePojo.getSender());
        return chatMessagePojo;
    }

}