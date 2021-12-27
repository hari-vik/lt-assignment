package lunatech.services.imdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import lunatech.services.controller.imdb.MoviesApi;
import lunatech.services.imdb.service.movie.SearchMovieService;
import lunatech.services.imdb.utils.ApiConstants;
import lunatech.services.model.imdb.MovieInfo;


/**
 * Rest controller implementation class to delegate request for IMDB integration API
 * Exceptions across project is handled using @ControllerAdvice {@link ControllerExceptionHandler}
 * 
 * @author hari
 *
 */
@RestController
@Slf4j
@RequestMapping(value = ApiConstants.IMDB_V1_PATH)
public class ImdbIntegrationControllerImpl implements MoviesApi {

	@Autowired
	private SearchMovieService service;

	@Override
	public ResponseEntity<List<MovieInfo>> searchMovie(String xRequestId, String title, String xApplicationSource) {
		
		List<MovieInfo> movieInfo = service.searchMovieByTitle(title);

		if (null == movieInfo || movieInfo.isEmpty()) {
			log.info("RequestId: {} : No movie information found for title {}", xRequestId, title);
			return new ResponseEntity<List<MovieInfo>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<MovieInfo>>(movieInfo, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<MovieInfo>> topRatedMovies(String xRequestId, String genre, String xApplicationSource,
			Integer perPage, Integer page, Boolean crewAndCastInfo) {
		
		List<MovieInfo> movieInfo = service.getTopRatedMoviesByGenre(genre, page, perPage, crewAndCastInfo);

		if (null == movieInfo || movieInfo.isEmpty()) {
			log.info("RequestId: {} : No information found for genre {}", xRequestId, genre);
			return new ResponseEntity<List<MovieInfo>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<MovieInfo>>(movieInfo, HttpStatus.OK);
	}

	

	


}
