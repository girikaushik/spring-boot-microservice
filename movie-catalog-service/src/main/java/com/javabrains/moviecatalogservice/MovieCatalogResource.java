package com.javabrains.moviecatalogservice;

import com.javabrains.moviecatalogservice.Models.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	
	
        
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
//		return Collections.singletonList(
//				new CatalogItem("movie1","good",4)
//				);
		
		RestTemplate restTemplate = new RestTemplate();
		
		
		
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",2)
				);
		
		 return ratings.stream().map(rating -> {
			Movie movie =  restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(),Movie.class);
			return new CatalogItem(movie.getName(),"test",rating.getRating());
			
			}) .collect(Collectors.toList());
	}
}
