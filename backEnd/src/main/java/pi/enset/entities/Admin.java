package pi.enset.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.TypeAdmin;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends Person {
    @Enumerated(EnumType.STRING)
    private TypeAdmin admin_type;
    @OneToMany(mappedBy = "enseignant", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<NonDisponibilite> nonDisponibilites = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "enseignant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ElementDeModule> elementDeModules = new ArrayList<>();
}
