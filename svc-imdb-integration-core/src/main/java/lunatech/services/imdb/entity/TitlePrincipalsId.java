package lunatech.services.imdb.entity;

import java.io.Serializable;

import lombok.Data;
/**
 * Id class for "title_pricipals" table
 * 
 * @author hari
 *
 */
@Data
public class TitlePrincipalsId implements Serializable{

	private static final long serialVersionUID = 1L;
	private String tconst;
	private Integer ordering;
	private String nconst;

	public TitlePrincipalsId(String tconst, Integer ordering, String nconst) {
		this.tconst = tconst;
		this.ordering = ordering;
		this.nconst = nconst;
	}

	public TitlePrincipalsId() {
	}
}
