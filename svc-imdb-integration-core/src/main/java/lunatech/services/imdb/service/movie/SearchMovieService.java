package lunatech.services.imdb.service.movie;

import java.util.List;

import lunatech.services.model.imdb.MovieInfo;
/**
 * Service interface to assist operations involving movie 
 * 
 * @author hari
 *
 */
public interface SearchMovieService {
	public List<MovieInfo> searchMovieByTitle(String title);
	public List<MovieInfo> getTopRatedMoviesByGenre(String genre, Integer page, Integer fetchLimit, boolean fullInfo);
}
 