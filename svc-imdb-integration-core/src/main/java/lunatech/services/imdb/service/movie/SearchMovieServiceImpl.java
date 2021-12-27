package lunatech.services.imdb.service.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lunatech.services.imdb.configuration.CacheConfiguration;
import lunatech.services.imdb.entity.TitleBasics;
import lunatech.services.imdb.mapper.MovieBasicInfoMapper;
import lunatech.services.imdb.mapper.MovieFullInfoMapper;
import lunatech.services.imdb.repository.MovieRepository;
import lunatech.services.model.imdb.MovieInfo;

/**
 * Service layer to handle movie search related operations
 * 
 * @author hari
 *
 */
@Service
public class SearchMovieServiceImpl implements SearchMovieService {

	@Autowired
	MovieRepository movieRepository;


	//Mapper instance to map complete movie object (including case and crew)
	@Autowired
	MovieFullInfoMapper movieFullInfoMapper;

	//Mapper instance to map just basic movie info
	@Autowired
	MovieBasicInfoMapper movieBasicInfoMapper;

	@Cacheable(CacheConfiguration.MOVIE_BY_TITLE)
	@Override
	public List<MovieInfo> searchMovieByTitle(String title) {
		List<TitleBasics> movies = movieRepository.findByprimaryTitleOrOriginalTitleIgnoreCase(title, title);
		return movieFullInfoMapper.mapMovieInfoList(movies);
	}

	@Cacheable(CacheConfiguration.TOP_MOVIES_BY_GENRE)
	@Override
	public List<MovieInfo> getTopRatedMoviesByGenre(String genre, Integer page, Integer fetchLimit, boolean fullInfo) {
		List<TitleBasics> movies = movieRepository.findTopRatedByGenre(genre,
				createRatingPageRequest(page.intValue(), fetchLimit.intValue()));

		if (fullInfo) {
			return movieFullInfoMapper.mapMovieInfoList(movies);
		}
		return movieBasicInfoMapper.mapMovieInfoList(movies);
	}
	
	


	private Pageable createRatingPageRequest(int pageValue, int limit) {
		return PageRequest.of(pageValue, limit,
				Sort.by("tr.averagerating").descending().and(Sort.by("tr.numvotes").descending()));
	}

}
