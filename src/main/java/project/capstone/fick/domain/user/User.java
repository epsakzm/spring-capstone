package project.capstone.fick.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.project.Project;
import project.capstone.fick.security.domain.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private Integer UID;

	private String name;

	@Column(nullable = false)
	private String password;

	@Transient
	private boolean isActivated;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<Project> projectList = new ArrayList<>();

	@Builder
	public User(Integer UID,
				String name,
				String password,
				Role role) {
		this.UID = UID;
		this.name = name;
		this.role = role;
		this.password = password;
	}

	//Test Method
	public void addProject(Project project) {
		if (this.projectList == null) {
			this.projectList = new ArrayList<>();
		}
		if (project != null) {
			this.projectList.add(project);
		}
	}

	public void setActivated() {
		this.isActivated = true;
	}
}
