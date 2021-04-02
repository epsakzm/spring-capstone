package project.capstone.fick.domain.user;

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
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private Integer UID;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "user")
	private List<Project> projectList = new ArrayList<>();

	@Builder
	public User(Integer UID,
				String name) {
		this.UID = UID;
		this.name = name;
	}

	public void updateUserName(String name) {
		this.name = name;
	}

	//Test Method
	public void joinProject(Project project) {
		if (project != null) {
			projectList.add(project);
		}
	}
}
