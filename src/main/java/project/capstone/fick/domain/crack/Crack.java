package project.capstone.fick.domain.crack;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.Location;
import project.capstone.fick.domain.structure.Structure;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CRACK")
@Entity
public class Crack extends BaseTimeEntity {

	@Id
	@GeneratedValue
	@Column(name = "crack_id")
	private Long id;

	@Column(nullable = false, length = 500)
	private String photoURI;

	@Column(nullable = false)
	private Double width;

	@Embedded
	private Location location;

	@Enumerated(EnumType.STRING)
	private CrackRiskLevel riskLevel;

	@Column(length = 1000)
	private String comment;

	@Column(columnDefinition = "boolean default true")
	private Boolean isCrack;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "structure_id")
	private Structure structure;

}
