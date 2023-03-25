package net.youssfi.coursesservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Teacher {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String photoOriginalName;
    private String photoURL;
    private String userId;
    @OneToMany(mappedBy = "teacher")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Course> courses;
}
