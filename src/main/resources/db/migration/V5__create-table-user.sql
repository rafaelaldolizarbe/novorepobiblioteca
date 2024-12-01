CREATE TABLE IF NOT EXISTS _user
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    CONSTRAINT check_role CHECK (role IN ('USER', 'ADMIN', 'MANAGER'))
);

CREATE TABLE IF NOT EXISTS token
(
    expired BOOLEAN NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    revoked BOOLEAN NOT NULL,
    user_id INT,
    token VARCHAR(255),
    token_type VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY token_token_key (token),
    CONSTRAINT user_reference FOREIGN KEY (user_id) REFERENCES _user(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CHECK (token_type = 'BEARER')
);
