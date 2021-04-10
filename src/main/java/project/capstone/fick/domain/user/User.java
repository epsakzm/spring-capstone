package project.capstone.fick.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.capstone.fick.domain.BaseTimeEntity;
import project.capstone.fick.domain.project.Project;

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

	private Integer UID;

	private String name;

	@OneToMany(mappedBy = "user")
	private List<Project> projectList = new ArrayList<>();

	@Builder
	public User(Integer UID,
				String name) {
		this.UID = UID;
		this.name = name;
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
}
