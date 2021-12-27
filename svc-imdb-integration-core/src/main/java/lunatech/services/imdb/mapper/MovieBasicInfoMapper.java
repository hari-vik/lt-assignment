package lunatech.services.imdb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import lunatech.services.imdb.entity.TitleBasics;
import lunatech.services.model.imdb.MovieInfo;
/**
 * Mapper class to map only basic movie information (primarytitle, year, running time etc)
 * 
 * @author hari
 *
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieBasicInfoMapper {

	@Mapping(source = "tconst", target = "id")
	@Mapping(source = "titleRating.averagerating", target = "averageRating")
	@Mapping(source = "titleRating.numvotes", target = "numberOfVotes")
	public abstract MovieInfo mapMovieInfoDetails(TitleBasics titleBasicEntity);

	public abstract List<MovieInfo> mapMovieInfoList(List<TitleBasics> titleBasicEntity);
}
