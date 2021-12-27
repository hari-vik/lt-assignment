package lunatech.services.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lunatech.services.imdb.entity.NameBasics;
/**
 * Repository class to interact with principals ( people involved like cast&crew)
 * @author hari
 */

public interface NameRepository extends JpaRepository<NameBasics, String>{

	@Query("SELECT nb.nconst FROM name_basics nb WHERE nb.primaryName=:name")
	String findIdFromName(String name);

}
