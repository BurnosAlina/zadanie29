INSERT INTO user_info (first_name, last_name, email, password)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '{bcrypt}$2a$10$m3bo2rt4YpBM97No6IorzOXem5e.fr1OLRCorlZoohzc7OqKuLOMG'),
    ('Jane', 'Doe', 'jane.doe@example.com', '{bcrypt}$2a$10$bJCSffotwqCRP3IbVvvB7euk0ituM4JFu1HnpbcUTkIh7/hgUH1NO'),
    ('Bob', 'Smith', 'bob.smith@example.com', '{bcrypt}$2a$10$ajRintSYIpHP5ovDTBYwa.1xNASxWE7971ZWJyCSatmWgqI5jZp/q');

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