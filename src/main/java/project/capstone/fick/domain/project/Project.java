package project.capstone.fick.domain.project;

import lombok.AccessLevel;
import lombok.Builder;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long id;

	private String name;

	@Embedded
	private Location location;

	@Column(length = 500)
	private String photoUrl;

	@Column(columnDefinition = "TEXT")
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "project")
	private List<Structure> structureList = new ArrayList<>();

	@Builder
	public Project(String name, User user) {
		this.name = name;
		this.user = user;
	}

	// Test Method
	public void setUser(User user) {
		if (user != null) {
			this.user = user;
		}
	}

	public void addStructure(Structure structure) {
		if (this.structureList == null) {
			this.structureList = new ArrayList<>();
		}
		if (structure != null) {
			this.structureList.add(structure);
		}
	}
}
