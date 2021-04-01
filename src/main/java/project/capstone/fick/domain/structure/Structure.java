package project.capstone.fick.domain.structure;

import lombok.*;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.crack.Crack;
import project.capstone.fick.domain.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Structure extends BaseTimeEntity {

	@Id
	@GeneratedValue
	@Column(name = "structure_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(length = 1000)
	private String comment;

	private Double height;

	@Enumerated(EnumType.STRING)
	private StructureStatus status;

	@Column(length = 500)
	private String modelUrl;

	@Embedded
	private Location location;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToMany(mappedBy = "structure")
	private List<Crack> crackList = new ArrayList<>();

}
