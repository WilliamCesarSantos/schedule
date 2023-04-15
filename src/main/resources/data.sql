CREATE TABLE IF NOT EXISTS user_(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT uk_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS task(
    id IDENTITY PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    closed_at TIMESTAMP,
    user_id INT NOT NULL,
    status VARCHAR(80) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_(id)
);