package com.hutchinson.playground.service;

import com.hutchinson.playground.config.MovieConfig;
import com.hutchinson.playground.model.ImdbMovie;
import com.hutchinson.playground.model.ImdbMovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class MovieService {
  private final MovieConfig movieConfig;
  private final RestTemplate restTemplate = new RestTemplate();

  public MovieService(MovieConfig movieConfig) {
    this.movieConfig = movieConfig;
  }

  public List<ImdbMovie> getMovies(String searchTerm){
    URI targetUrl= UriComponentsBuilder.fromUriString(movieConfig.getHost())
      .path("/")
      .queryParam("i", movieConfig.getI())
      .queryParam("apikey", movieConfig.getApikey())
      .queryParam("s", searchTerm)
      .build()
      .encode()
      .toUri();

    RequestEntity request = RequestEntity
      .get(targetUrl)
      .accept(MediaType.APPLICATION_JSON)
      .build();

    ResponseEntity<ImdbMovieResponse> response = restTemplate.getForEntity(request.getUrl(), ImdbMovieResponse.class);

    return response.getBody().getSearch();
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
