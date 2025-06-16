package com.urlshorteningservice.URLShorteningService.URLController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Base controller for URL shortening and redirections
 */
@RestController
public class URLController 
{
	private static final String BASE_URL_FOR_REDIRECTION = "http://localhost:8080/redirectToUrl/";

	private static final String URL_FROM_UI = "url";
	
	static Map<String, String> inMemoryDb = new HashMap<>();
	
	/**
	 * This endpoint is responsible for shortening the given URL to a different URL.
	 * @param urlDetails
	 * @return
	 */
	@PostMapping("/shortenURL")
	public String shortenURL(@RequestBody Map<String, String> urlDetails) 
	{
		String uuidToBeUsedInPlace = String.valueOf(UUID.randomUUID());
		String urlFromUI = urlDetails.get(URL_FROM_UI);
		
		inMemoryDb.put(uuidToBeUsedInPlace, urlFromUI);

		return BASE_URL_FOR_REDIRECTION + uuidToBeUsedInPlace;
	}
	
	/**
	 * This endpoint is responsible for redirecting the URL encoded to actual URL.
	 * @param encParam
	 * @return
	 */
	@GetMapping("/redirectToUrl/{UUID}")
	public ResponseEntity<Object> redirectToUrl(@PathVariable("UUID") String encParam) 
	{
		String actualURL = inMemoryDb.get(encParam);
		
		if(actualURL != null) 
		{
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(actualURL)).build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
