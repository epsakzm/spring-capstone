package project.capstone.fick.domain.crack;

import lombok.*;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.structure.Structure;

import javax.persistence.*;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Crack extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crack_id")
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String photoUrl;

	private Double width;

	@Embedded
	private Location location;

	private Double height;

	@Enumerated(EnumType.STRING)
	private CrackRiskLevel riskLevel;

	@Column(columnDefinition = "TEXT")
	private String comment;

	@Column(columnDefinition = "boolean default true")
	private Boolean isCrack = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structure_id")
	private Structure structure;

	@Builder
	public Crack(String photoUrl,
				 Double width,
				 Location location,
				 Double height,
				 CrackRiskLevel riskLevel,
				 String comment,
				 Structure structure) {
		this.photoUrl = photoUrl;
		this.width = width;
		this.height = height;
		this.comment = comment;
		this.location = location;
		this.riskLevel = riskLevel;
		this.structure = structure;
	}

	public void setStructure(Structure structure) {
		if (structure != null)
			this.structure = structure;
	}

	public void update(Boolean isCrack, String comment, CrackRiskLevel riskLevel) {
		this.isCrack = isCrack;
		this.comment = comment;
		this.riskLevel = riskLevel;
	}
}
