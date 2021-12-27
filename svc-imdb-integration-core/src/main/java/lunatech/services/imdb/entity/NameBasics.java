// Generated with g9.

package lunatech.services.imdb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
/**
 * Entity class for "name_basics" table
 * 
 * @author hari
 *
 */
@Data
@Entity(name="name_basics")
public class NameBasics implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(unique=true, nullable=false, length=10)
    private String nconst;
    
    @Column(name="primaryname", length=110)
    private String primaryName;
    
    @Column(name="birthyear", precision=10)
    private Integer birthYear;
    
    @Column(name="deathyear", precision=10)
    private Integer deathYear;
    
    @Column(name="primaryprofession", length=200)
    private String primaryProfession;
    
    @Column(name="knownfortitles", length=100)
    private String knownForTitles;
    
    
}
