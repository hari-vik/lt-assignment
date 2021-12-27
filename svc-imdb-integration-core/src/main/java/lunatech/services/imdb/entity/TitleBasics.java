// Generated with g9.

package lunatech.services.imdb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * Entity class for "title_basics" table
 * 
 * @author hari
 *
 */
@Data
@Entity(name = "title_basics")
@NamedEntityGraph(name = "movies-full-relation", attributeNodes = { @NamedAttributeNode(value = "titleCrew"),
		@NamedAttributeNode(value = "titleRating"),
		@NamedAttributeNode(value = "titlePrincipals", subgraph = "name-basics-subgraph"), }, subgraphs = {
				@NamedSubgraph(name = "name-basics-subgraph", attributeNodes = { @NamedAttributeNode("nameBasics") }) })

@NamedEntityGraph(name = "movies-rating-relation", attributeNodes = { @NamedAttributeNode(value = "titleRating")})
public class TitleBasics {

	@Id
	@Column(name = "tconst", unique = true, nullable = false, length = 10)
	private String tconst;

	@Column(name = "titletype", length = 20)
	private String titleType;

	@Column(name = "primarytitle", length = 500)
	private String primaryTitle;

	@Column(name = "originaltitle", length = 500)
	private String originalTitle;

	@Column(name = "isadult", length = 1)
	private Boolean isAdult;

	@Column(name = "startyear", precision = 10)
	private Integer startYear;

	@Column(name = "endyear", precision = 10)
	private Integer endYear;

	@Column(name = "runtimeminutes", precision = 10)
	private Integer runTimeMinutes;

	@Column(name = "genres", length = 200)
	private String genres;

	@OneToOne(mappedBy = "titleBasics")
	private TitleCrew titleCrew;

	@OneToOne
	@JoinColumn(name = "tconst", referencedColumnName = "tconst")
	private TitleRatings titleRating;

	@OneToMany
	@JoinColumn(name = "tconst", referencedColumnName = "tconst")
	private Set<TitlePrincipals> titlePrincipals;

}
