insert into user (id, email, password, name, surname, age, location, created_at, updated_at, photo_url, active)
values (1, 't1@gmail.com', '$2a$12$hofbvhi9SCGXGwvuRlYAVOurIca5k7SNUiLJhxVNqLlwXJ0yP1sym', 'Tom', 'Anderson', 36, 'Togliatti', '2019-03-23 17:31:22.560000', '2019-05-23 17:35:23.560000', 'admin.jpg', true);

insert into user_role (user_id, roles) values(1, 'ADMIN');