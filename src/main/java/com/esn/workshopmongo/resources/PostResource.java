package com.esn.workshopmongo.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esn.workshopmongo.domain.Post;
import com.esn.workshopmongo.resources.util.URL;
import com.esn.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam String text){
		text = URL.decodePrams(text);
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(defaultValue="") String text,
			@RequestParam(defaultValue="") String minDate,
			@RequestParam(defaultValue="") String maxDate
		){
		text = URL.decodePrams(text);
		Instant min = URL.convertDate(minDate, Instant.EPOCH);
		Instant max = URL.convertDate(maxDate, Instant.now());
		List<Post> posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
}
