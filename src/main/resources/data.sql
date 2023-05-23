INSERT INTO user_info (first_name, last_name, email, password)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '{noop}pass1'),
    ('Jane', 'Doe', 'jane.doe@example.com', '{noop}pass2'),
    ('Bob', 'Smith', 'bob.smith@example.com', '{noop}pass3');

INSERT INTO user_role (id, name, description)
VALUES
    (1, 'ROLE_Admin', 'Administrator role'),
    (2, 'ROLE_Manager', 'Regular user role'),
    (3, 'ROLE_User', 'Regular user role');

INSERT INTO user_info_roles (user_info_id, roles_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);