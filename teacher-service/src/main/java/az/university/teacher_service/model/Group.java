package az.university.teacher_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // məsələn: "Math-101"
    private String codeOfSubject;
    private LocalDate createdAt;
    private Long createdBy;
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_teacher",
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers=new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;



//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "tutor_id",nullable = false)
//    private Tutor createdBy; // qrupun kim tərəfindən yaradıldığı

}
