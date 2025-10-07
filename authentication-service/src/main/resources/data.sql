INSERT INTO roles (name, academic,teacher,student)
VALUES ('ROLE_ADD_TUTOR', 1,0,0 ),
       ('ROLE_ADD_TEACHER', 1,0,0),
       ('ROLE_ADD_GROUP', 1, 0, 0),
       ('ROLE_ADD_STUDENT', 1, 0, 0),
       ('ROLE_ADD_LESSON', 1, 0, 0),
       ('ROLE_ADD_COMPONENT', 1, 1, 0),
       ('ROLE_ADD_GRADE', 1, 1, 0),
       ('ROLE_ADD_MARK', 1, 1, 0),
       ('ROLE_UPDATE_GROUP', 1, 1, 0),
       ('ROLE_UPDATE_STUDENT', 1, 1, 0),
       ('ROLE_UPDATE_LESSON', 1, 1, 0),
       ('ROLE_UPDATE_COMPONENT', 1, 1, 0),
       ('ROLE_UPDATE_TEACHER', 1, 1, 0);



insert into user_info
(username, password,user_id)
values ('admin', '$2a$12$ZqnYwYvFVlWafNnQ/Z5JgOTmG6LhmtqXOUkTQ/4i6cgNGBDXK/iVq',1),
       ('student', '$2a$12$ZqnYwYvFVlWafNnQ/Z5JgOTmG6LhmtqXOUkTQ/4i6cgNGBDXK/iVq',2);

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1,2);

INSERT INTO user_roles (user_id, role_id)
VALUES (2, 2);



-- insert into user_roles (user_id, role_id)
-- select 1, id
-- from roles
-- where seller = 1;
--
-- insert into user_roles (user_id, role_id)
-- select 2, id
-- from roles
-- where seller = 1;
