package project.capstone.fick.domain.project;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.structure.Structure;
import project.capstone.fick.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Project extends BaseTimeEntity {

	@Id
	@GeneratedValue
	@Column(name = "project_id")
	private Long id;

	@Embedded
	private Location location;

	@Column(length = 500)
	private String photoURI;

	@Column(length = 1000)
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "project")
	private List<Structure> structureList = new ArrayList<>();
}
