CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    money INTEGER DEFAULT 0
);

CREATE TABLE habits (
    name VARCHAR(255) NOT NULL,
    good BOOLEAN,
    how_often INTERVAL,
    last_started TIMESTAMP,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, name)
);

CREATE TABLE to_do (
    name VARCHAR(255) NOT NULL,
    do_by TIMESTAMP,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, name)
);

CREATE TABLE follows (
    following_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, following_id),
    CONSTRAINT check_no_self_follow CHECK (user_id <> following_id)
);

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    picture BYTEA, -- Using BYTEA for storing image binary data
    rarity VARCHAR(50) CHECK (rarity IN ('common', 'uncommon', 'rare', 'epic', 'legendary'))
);

CREATE TABLE owns (
    item_id INTEGER NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    date_obtained TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, item_id)
);

