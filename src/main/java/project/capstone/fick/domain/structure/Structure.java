package project.capstone.fick.domain.structure;

import lombok.*;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Structure extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "structure_id")
	private Long id;

//	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String comment;

	private Double height;

	@Column(columnDefinition = "boolean default false")
	private Boolean isWorkDone = false;

	@Column(columnDefinition = "TEXT")
	private String modelUrl;

	@Embedded
	private Location location;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToMany(mappedBy = "structure")
	private List<Crack> crackList = new ArrayList<>();

	@Builder
	public Structure(String name,
					 String comment,
					 Double height,
					 String modelUrl,
					 Location location,
					 Project project) {
		this.name = name;
		this.comment = comment;
		this.height = height;
		this.modelUrl = modelUrl;
		this.location = location;
		this.project = project;
	}

	public void addCrack(Crack crack) {
		if (this.crackList == null) {
			this.crackList = new ArrayList<>();
		}
		if (crack != null)
			this.crackList.add(crack);
	}

	public void setProject(Project project) {
		if (project != null) {
			this.project = project;
		}
	}
}
