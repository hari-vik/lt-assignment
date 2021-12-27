// Generated with g9.

package lunatech.services.imdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
/**
 * Entity class for "title_crew" table
 * 
 * @author hari
 *
 */
@Data
@Entity(name="title_crew")
public class TitleCrew {

    @Id
    @Column(name = "tconst", nullable = false, length = 10)
    private String tconst;

    @Column(length=500)
    private String directors;
    
    @Column(length=500)
    private String writers;
    
    @OneToOne(optional=false)
    @JoinColumn(name="tconst", nullable=false)
    private TitleBasics titleBasics;

}
