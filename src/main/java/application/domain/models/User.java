package application.domain.models;

import application.domain.valueobjects.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends Person {
    private String username;
    private String password;
    private UserStatus status;
    private Participant participant;
}