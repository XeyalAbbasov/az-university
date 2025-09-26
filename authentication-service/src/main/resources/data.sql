INSERT INTO roles (name, academic,teacher,student)
VALUES ('ROLE_GET_ALL', 1,0,0 ),
       ('ROLE_GET_ONE', 1,0,0);
--        ('ROLE_UPDATE', 1, 1, 0),
--        ('ROLE_ADD_CUSTOMER', 1, 1, 0),
--        ('ROLE_GET_CUSTOMER', 1, 1, 0),
--        ('ROLE_ACTIVATE_CUSTOMER', 1, 1, 0),
--        ('ROLE_DEACTIVATE_CUSTOMER', 1, 1, 0),
--        ('ROLE_SEARCH', 1, 1, 0),
--        ('ROLE_ADD_PRODUCT', 1, 1, 0),
--        ('ROLE_GET_PRODUCT', 1, 1, 0),
--        ('ROLE_GET_ENTRY_LOGS', 1, 1, 0);



insert into user_info
(username, password)
values ('admin', '$2a$12$ZqnYwYvFVlWafNnQ/Z5JgOTmG6LhmtqXOUkTQ/4i6cgNGBDXK/iVq'),
       ('student', '$2a$12$ZqnYwYvFVlWafNnQ/Z5JgOTmG6LhmtqXOUkTQ/4i6cgNGBDXK/iVq');

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
