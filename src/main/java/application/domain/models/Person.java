package application.domain.models;

import application.domain.valueobjects.SystemRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {
    private String identifier;
    private String fullName;
    private String email;
    private SystemRole role;
}