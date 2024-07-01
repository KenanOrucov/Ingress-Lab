CREATE TABLE authors(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    email VARCHAR(256),
    created_at TIMESTAMP
);

CREATE TABLE subscribers(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255)
);

CREATE TABLE posts(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    tag VARCHAR(255),
    created_at TIMESTAMP
);

ALTER TABLE authors ADD COLUMN about VARCHAR(255);
ALTER TABLE authors ADD COLUMN nick VARCHAR(255);

CREATE TABLE authors_posts(
    id SERIAL PRIMARY KEY,
    author_id INTEGER REFERENCES authors(id),
    post_id INTEGER REFERENCES posts(id)
);

ALTER TABLE authors ALTER COLUMN created_at SET DEFAULT now();

ALTER TABLE authors ADD CONSTRAINT authors_nick_email_key UNIQUE (nick, email);

ALTER TABLE posts ADD COLUMN image_url VARCHAR(255);

CREATE TABLE posts_tags(
    id SERIAL PRIMARY KEY,
    post_id INTEGER REFERENCES posts(id),
    tag_id INTEGER REFERENCES tags(id)
);

CREATE INDEX index_tags_tag ON tags(tag);

ALTER TABLE authors ADD COLUMN github VARCHAR(255);
ALTER TABLE authors ADD COLUMN updated_at TIMESTAMP;

CREATE OR REPLACE view authors_posts_view AS
    SELECT a.nick as authors_nick,
           p.title as post_title,
           p.created_at as post_created_at
    FROM authors a
    INNER JOIN authors_posts ap ON a.id = ap.author_id
    INNER JOIN posts p ON p.id = ap.post_id;



-- PART 2
CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price INTEGER,
    created_at TIMESTAMP
);

CREATE TABLE specification(
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES product(id),
    feature_one VARCHAR(255),
    feature_two VARCHAR(255),
    feature_three VARCHAR(255)
);