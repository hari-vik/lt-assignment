package lunatech.services.imdb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import lunatech.services.imdb.entity.TitleBasics;
import lunatech.services.model.imdb.MovieInfo;
/**
 * Mapper class to map full movie information including rating, crew and cast information.
 * 
 * @author hari
 *
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = { CastMapper.class, CrewMapper.class}, componentModel = "spring")
public interface MovieFullInfoMapper {

	@Mapping(source = "tconst", target = "id")
	@Mapping(source = "titleRating.averagerating", target = "averageRating")
	@Mapping(source = "titleRating.numvotes", target = "numberOfVotes")
	@Mapping(source = "titleBasicEntity.titleCrew", target = "crew")
	@Mapping(source = "titleBasicEntity.titlePrincipals", target = "cast")
	public abstract MovieInfo mapMovieInfoDetails(TitleBasics titleBasicEntity);

	public abstract List<MovieInfo> mapMovieInfoList(List<TitleBasics> titleBasicEntity);


}
