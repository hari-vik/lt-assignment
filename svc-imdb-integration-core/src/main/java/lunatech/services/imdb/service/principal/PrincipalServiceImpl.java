package lunatech.services.imdb.service.principal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lunatech.services.imdb.entity.NameBasics;
import lunatech.services.imdb.repository.NameRepository;
/**
 * Service layer to handle principal operations
 * 
 * @author hari
 *
 */
@Service
public class PrincipalServiceImpl implements PrincipalService {

	@Autowired
	NameRepository nameRepository;

	@Override
	public List<NameBasics> getCrewDetails(List<String> listOfId) {
		return listOfId.isEmpty() ? new ArrayList<>() : nameRepository.findAllById(listOfId);
	}

}
