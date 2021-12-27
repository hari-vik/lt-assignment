package lunatech.services.imdb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lunatech.services.imdb.entity.TitleBasics;
/**
 * Repository class to interact with movie related data.
 * 
 * @author hari
 *
 */
public interface MovieRepository extends JpaRepository<TitleBasics, String> {

	@EntityGraph("movies-full-relation")
	List<TitleBasics> findByprimaryTitleOrOriginalTitleIgnoreCase(String title, String originalTitle);
	
	@EntityGraph("movies-rating-relation")
	@Query("SELECT tb FROM title_basics tb join title_ratings tr ON tb.tconst = tr.tconst WHERE tb.genres like %:genre%")
	List<TitleBasics> findTopRatedByGenre(String genre, Pageable pageable);

}
