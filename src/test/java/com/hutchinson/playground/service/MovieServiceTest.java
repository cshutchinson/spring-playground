package com.hutchinson.playground.service;

import com.hutchinson.playground.config.MovieConfig;
import com.hutchinson.playground.model.ImdbMovie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

  @MockBean
  private MovieConfig movieConfig;
  private MovieService movieService;
  private MockRestServiceServer mockServer;

  @Before
  public void setUp() throws Exception {

    when(movieConfig.getHost()).thenReturn("http://www.omdbapi.com");
    when(movieConfig.getI()).thenReturn("iParam");
    when(movieConfig.getApikey()).thenReturn("apikey");

    movieService = new MovieService(movieConfig);
    mockServer  = MockRestServiceServer.createServer(movieService.getRestTemplate());
  }

  @Test
  public void getMovies_returnsTheCorrectResponse() throws Exception {
    String json = getJSON("/movie.json");

    URI targetUrl= UriComponentsBuilder.fromUriString(movieConfig.getHost())
      .path("/")
      .queryParam("i", movieConfig.getI())
      .queryParam("apikey", movieConfig.getApikey())
      .queryParam("s", "Bogroll's")
      .build()
      .encode()
      .toUri();

    ImdbMovie movie = new ImdbMovie("Bogroll's Christmas Nightmare", "tt5294038", "N/A", "2015" );
    List<ImdbMovie> expected = Collections.singletonList(movie);

    mockServer
      .expect(requestTo(targetUrl))
      .andExpect(method(HttpMethod.GET))
      .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

    assertThat(movieService.getMovies("Bogroll's").toString(), equalTo(expected.toString()));
    mockServer.verify();
  }

  private String getJSON(String path) throws Exception {
    URL url = this.getClass().getResource(path);
    return new String(Files.readAllBytes(Paths.get(url.getFile())));
  }
}