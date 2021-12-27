package lunatech.services.imdb.service.principal;

import java.util.List;

import lunatech.services.imdb.entity.NameBasics;
/**
 * Service interface to handle operations on principals ( persons like crew, cast etc)
 * 
 * @author hari
 *
 */
public interface PrincipalService {
	
	public List<NameBasics> getCrewDetails(List<String> listOfId);


}
