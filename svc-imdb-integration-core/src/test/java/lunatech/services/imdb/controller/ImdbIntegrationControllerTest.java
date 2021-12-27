package lunatech.services.imdb.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lunatech.services.imdb.exception.ExceptionEnum;
import lunatech.services.imdb.service.movie.SearchMovieService;
import lunatech.services.model.imdb.MovieInfo;

@ExtendWith(SpringExtension.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class))
@WebMvcTest(controllers = ImdbIntegrationControllerImpl.class)
public class ImdbIntegrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	SearchMovieService movieService;

	private String BASE_PATH = "/rest/integration/imdb/v1";

	@Test
	void testMoviesForNoContentStatusCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/integration/imdb/v1/movies/title/")
				.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003")
				.param("title", "name")).andDo(print()).andExpect(status().isNoContent());

	}

	@Test
	void testMoviesForErrorWhenNoRequestParameter() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/movies/title")
				.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003"))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorCode",
						is(ExceptionEnum.MISSING_FIELD.findErrorCode())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage",
						is(ExceptionEnum.MISSING_FIELD.findErrorMessage())));
	}

	@Test
	void testMoviesForValidSuccessResponse() throws Exception {

		List<MovieInfo> movieList = buildValidMovieData();
		given(movieService.searchMovieByTitle("name")).willReturn(movieList);

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/movies/title")
					.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003")
					.param("title", "name"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(movieList.get(0).getId())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].primaryTitle", is(movieList.get(0).getPrimaryTitle())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].cast.size()", is(movieList.get(0).getCast().size())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].crew.size()", is(movieList.get(0).getCrew().size())));
	}

	@Test
	void testGenreRatingForErrorWhenNoRequestParameter() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get(BASE_PATH + "/movies/rating")
					.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003"))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorCode",
						is(ExceptionEnum.MISSING_FIELD.findErrorCode())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage",
						is(ExceptionEnum.MISSING_FIELD.findErrorMessage())));
	}

	@Test
	void testGenreRatingForNoContentStatusCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/movies/rating")
				.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003")
				.param("genre", "Thrill"))
			.andDo(print()).andExpect(status().isNoContent());

	}
	
	
	
	@Test
	void testGenreRatingForValidSuccessResponseBasicMovieInfo() throws Exception {

		List<MovieInfo> movieList = buildValidGenreRatingData();
		given(movieService.getTopRatedMoviesByGenre("horror",0,15,false)).willReturn(movieList);

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/movies/rating")
					.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003")
					.param("genre", "horror")
					.param("perPage", "15")
					.param("page", "0")
					.param("crewAndCastInfo", "false"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(movieList.get(0).getId())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].primaryTitle", is(movieList.get(0).getPrimaryTitle())));
				
	}
	
	@Test
	void testGenreRatingForValidSuccessResponseFullMovieInfo() throws Exception {

		List<MovieInfo> movieList = buildValidMovieData();
		given(movieService.getTopRatedMoviesByGenre("horror",0,15,true)).willReturn(movieList);

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH + "/movies/rating").contentType(MediaType.APPLICATION_JSON)
				.header("x-request-id", "06635760-6748-11ec-90d6-0242ac120003")
				.param("genre", "horror")
				.param("perPage", "15")
				.param("page", "0")
				.param("crewAndCastInfo", "true"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", is(movieList.get(0).getId())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].primaryTitle", is(movieList.get(0).getPrimaryTitle())))
				;
	}

	private List<MovieInfo> buildValidMovieData() {
		return readMovieListFromFile("src/test/resources/dataset/ValidMoviesList.json");
	}

	private List<MovieInfo> buildValidGenreRatingData() {
		return readMovieListFromFile("src/test/resources/dataset/ValidGenreRatingList.json");
	}

	private List<MovieInfo> readMovieListFromFile(String filePath) {
		List<MovieInfo> moviesList = new ArrayList<>();
		File file = new File(filePath);
		String content = null;
		try {
			content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			moviesList = new Gson().fromJson(content, new TypeToken<List<MovieInfo>>() {
			}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return moviesList;
	}

}
