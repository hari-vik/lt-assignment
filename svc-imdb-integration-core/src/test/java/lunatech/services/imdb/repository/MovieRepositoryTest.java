package lunatech.services.imdb.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import lunatech.services.imdb.entity.TitleBasics;

@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Sql("classpath:/liquibase/dml/setup-base-relation.sql")
	@Test
	void testMovieListFromPrimaryTitle() {
		List<TitleBasics> moviesList = movieRepository.findByprimaryTitleOrOriginalTitleIgnoreCase("Carmencita",
				"Carmencita");

		assertThat(moviesList).hasSize(2);
		assertThat(moviesList.get(0).getTconst()).isEqualTo("tt0453643");
		assertThat(moviesList.get(0).getTitlePrincipals()).hasSize(0);

		assertThat(moviesList.get(1).getTconst()).isEqualTo("tt0000001");
		assertThat(moviesList.get(1).getTitlePrincipals()).hasSize(2);

	}

	@Sql("classpath:/liquibase/dml/setup-base-relation.sql")
	@Sql("classpath:/liquibase/dml/setup-movies-rating.sql")

	@Test
	void testTopRatedByGenreLimitApplied() {

		Pageable page = PageRequest.of(0, 1,
				Sort.by("tr.averagerating").descending().and(Sort.by("tr.numvotes").descending()));
		
		List<TitleBasics> moviesList = movieRepository.findTopRatedByGenre("Short", page);

		assertThat(moviesList).hasSize(1);
		assertThat(moviesList.get(0).getTconst()).isEqualTo("tt0453643");

	}
	
	@Sql("classpath:/liquibase/dml/setup-base-relation.sql")
	@Sql("classpath:/liquibase/dml/setup-movies-rating.sql")
	@Test
	void testTopRatedByGenreFetchAllInSinglePage() {

		Pageable page = PageRequest.of(0, 10,
				Sort.by("tr.averagerating").descending().and(Sort.by("tr.numvotes").descending()));
		
		List<TitleBasics> moviesList = movieRepository.findTopRatedByGenre("Short", page);

		assertThat(moviesList).hasSize(2);
		assertThat(moviesList.get(0).getTconst()).isEqualTo("tt0453643");
	}


}
