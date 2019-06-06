package com.u2d.filaMq.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.u2d.filaMq.service.FilaMQService;

@RestController
@RequestMapping("/filaMQ")
@CrossOrigin("*")
public class FilaMQController {

	@Autowired
	private FilaMQService service;
	
	@GetMapping("send")
	public ResponseEntity<String> send(@RequestBody String msg){
		String response = service.send(msg);
		return response.equals("OK") ? ResponseEntity.ok("Mensagem Enviada!") : 
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping("recv")
	public ResponseEntity<String> receiver(){
		String response = service.recv();
		return !response.equals("FAIL") ? ResponseEntity.ok(response) : 
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
