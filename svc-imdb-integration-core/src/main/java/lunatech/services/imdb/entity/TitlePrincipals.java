// Generated with g9.

package lunatech.services.imdb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * Entity class for "title_principals" table
 * 
 * @author hari
 *
 */
@Data
@Entity(name="title_principals")
@IdClass(TitlePrincipalsId.class)
public class TitlePrincipals implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private String tconst;
	
    @Id
    @Column(nullable=false, precision=10)
    private Integer ordering;
    
    @Id
    @Column(nullable=false, insertable=false, updatable=false)
    private String nconst;
    
    @Column(length=100)
    private String category;
    
    @Column(length=300)
    private String job;
    
    @Column(length=500)
    private String characters;
    
    @OneToOne(optional=false)
    @JoinColumn(name="nconst", nullable=false)
    private NameBasics nameBasics;
   

}
