package lunatech.services.imdb.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import lunatech.services.model.imdb.MovieInfo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	private static final String BASE_PATH = "/rest/integration/imdb/v1";

	@Test
	@Sql("classpath:/liquibase/dml/setup-base-relation.sql")
	@Sql("classpath:/liquibase/dml/setup-movies-rating.sql")
	void testFetchMoviesByTitleSuccess() {

		String finalUrl = BASE_PATH.concat("/movies/title").concat("?title=Carmencita");

		final HttpHeaders headers = new HttpHeaders();
		headers.set("x-request-id", "06635760-6748-11ec-90d6-0242ac120003");

		// Create a new HttpEntity
		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<List<MovieInfo>> movieList = restTemplate.exchange(finalUrl, HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<MovieInfo>>() {
				});
		
		assertThat(movieList.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(movieList.getBody()).hasSize(2);
		assertThat(movieList.getBody().get(0).getPrimaryTitle()).isEqualTo("Carmencita");
		assertThat(movieList.getBody().get(0).getCrew()).hasSize(0);
		assertThat(movieList.getBody().get(0).getCast()).hasSize(0);
		assertThat(movieList.getBody().get(1).getCrew()).hasSize(1);
		assertThat(movieList.getBody().get(1).getCast()).hasSize(2);
	}
}
