// Generated with g9.

package lunatech.services.imdb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
/**
 * Entity class for  "title_ratings" table
 * @author hari
 *
 */
@Data
@Entity(name="title_ratings")
public class TitleRatings implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(precision=17, scale=17)
    private double averagerating;
    
    @Column(precision=10)
    private Integer numvotes;
    
    @Id
    private String tconst;
}
