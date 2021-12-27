package lunatech.services.imdb.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import lunatech.services.imdb.entity.TitlePrincipals;
import lunatech.services.model.imdb.Cast;
/**
 * Mapper class to map cast members object inside movies object
 * 
 * @author hari
 *
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface CastMapper {

	@Mapping(source = "nameBasics.primaryName", target = "primaryName")
	@Mapping(source = "nameBasics.birthYear", target = "birthYear")
	@Mapping(source = "nameBasics.deathYear", target = "deathYear")
	@Mapping(source = "nameBasics.primaryProfession", target = "primaryProfession")
	public abstract Cast mapCastDetails(TitlePrincipals titlePrincipals);

	public abstract List<Cast> mapCastDetailsList(Set<TitlePrincipals> titlePrincipals);
}
