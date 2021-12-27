package lunatech.services.imdb.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import lunatech.services.imdb.entity.NameBasics;
import lunatech.services.imdb.entity.TitleCrew;
import lunatech.services.imdb.service.principal.PrincipalService;
import lunatech.services.model.imdb.Crew;

/**
 * Mapper class to map crew members object inside movie object
 * 
 * @author hari
 *
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public abstract class CrewMapper {

	@Autowired
	protected PrincipalService service;

	public List<Crew> mapCrewDetailsList(TitleCrew crew) {
		List<String> listOfId = new ArrayList<>();

		if (null != crew) {

			if (null != crew.getDirectors()) {
				listOfId.addAll(
						Stream.of(crew.getDirectors().split(",")).map(String::trim).collect(Collectors.toList()));
			}

			if (null != crew.getWriters()) {
				listOfId.addAll(Stream.of(crew.getWriters().split(",")).map(String::trim).collect(Collectors.toList()));
			}

		}
		return mapCrewDetails(service.getCrewDetails(listOfId));

	}

	public abstract List<Crew> mapCrewDetails(List<NameBasics> nameBasics);
}
