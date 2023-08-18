package com.pdf.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.generator.service.PlayerService;

//@CrossOrigin(origins="https://pdfgene.netlify.app")
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	PlayerService ps;
	
	//3479
	@GetMapping("/details")
	public ResponseEntity<byte[]> fetchPlayerDetails(@RequestParam String team,@RequestParam String season, @RequestParam String league) {
		System.out.println("Your team id is: "+ team);
		byte[] pdfData = ps.getPlayerById(team,season,league);
		
		//Headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment","stats.pdf");
		
		return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
	}
}
