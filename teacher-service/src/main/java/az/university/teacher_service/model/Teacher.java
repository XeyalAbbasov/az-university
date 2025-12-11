package az.university.teacher_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    //burada username password lazim deyil chunki user repoda onlar var gelecekde siline biler
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean active;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.LAZY)
    private List<Group> groups=new ArrayList<>();

}
