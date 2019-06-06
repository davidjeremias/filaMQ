package com.u2d.filaMq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class FilaMQService {

	@Autowired
    private JmsTemplate jmsTemplate;
	
	public String send(String msg){
	    try{
	        jmsTemplate.convertAndSend("DEV.QUEUE.1", msg);
	        return "OK";
	    }catch(JmsException ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}
	
	public String recv(){
	    try{
	        return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
	    }catch(JmsException ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}
}
