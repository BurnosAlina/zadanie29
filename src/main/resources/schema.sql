CREATE TABLE user_info
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50)         NOT NULL,
    last_name  VARCHAR(50)         NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL
);

CREATE TABLE user_role
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE user_info_roles
(
    user_info_id      BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    PRIMARY KEY (user_info_id, roles_id),
    FOREIGN KEY (user_info_id) REFERENCES user_info (id),
    FOREIGN KEY (roles_id) REFERENCES user_role (id)
);