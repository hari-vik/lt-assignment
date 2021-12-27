package lunatech.services.imdb.configuration;

import javax.management.timer.Timer;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;
/**
 * Class to maintain cache configuration. Including scheduling, cache names etc
 * 
 * 
 * @author hari
 *
 */

@EnableCaching
@EnableScheduling
@Configuration
@Slf4j
public class CacheConfiguration {
	public static final String MOVIE_BY_TITLE = "MOVIES_BY_TITLE";
	public static final String TOP_MOVIES_BY_GENRE = "TOP_RATED_MOVIES_BY_GENRE";

	@Scheduled(fixedRate = Timer.ONE_WEEK)
	@CacheEvict(value = { MOVIE_BY_TITLE }, allEntries = true)
	public void clearMovieCache() {
		log.info("Clearing {} caches", MOVIE_BY_TITLE);
	}

	@Scheduled(fixedRate = Timer.ONE_DAY)
	@CacheEvict(value = { TOP_MOVIES_BY_GENRE }, allEntries = true)
	public void clearRatingCache() {
		log.info("Clearing {} caches", TOP_MOVIES_BY_GENRE);
	}

}
