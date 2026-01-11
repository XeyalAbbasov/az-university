INSERT INTO roles (name, academic,teacher,student)
VALUES ('ROLE_CONTROL_GROUP', 1,0,0 ),
       ('ROLE_CONTROL_LESSON', 1,0,0),
       ('ROLE_CONTROL_REQUEST',1,0,0),
       ('ROLE_CONTROL_TEACHER', 1, 0, 0),
       ('ROLE_CONTROL_TUTOR',1,0,0),
       ('ROLE_CONTROL_STUDENT', 1, 1, 0),
       ('ROLE_FOR_STUDENT',1, 1, 1),
       ('ROLE_UPDATE_TEACHER', 1, 1, 0);



insert into user_info
(username, password,user_id)
values ('admin', '$2a$12$ZqnYwYvFVlWafNnQ/Z5JgOTmG6LhmtqXOUkTQ/4i6cgNGBDXK/iVq',1),
       ('student', '$2a$12$ZqnYwYvFVlWafNnQ/Z5JgOTmG6LhmtqXOUkTQ/4i6cgNGBDXK/iVq',2);

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1,2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8);

INSERT INTO user_roles (user_id, role_id)
VALUES (2, 7);

-- insert into authorities (username, authority)
-- select 'xeyal777', authority
-- from authority_list
-- where librarian = 1;

-- INSERT INTO user_roles (user_id,role_id)
-- select 1,role_id from roles where academic = 1
