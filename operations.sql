-- ONE-TO-ONE example

CREATE TABLE person(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE passport(
    id SERIAL PRIMARY KEY,
    number VARCHAR(255),
    person_id INTEGER REFERENCES person(id)
);

CREATE TABLE phone(
    id SERIAL PRIMARY KEY,
    number VARCHAR(255),
    person_id INTEGER REFERENCES person(id)
);

-- ONE-TO-MANY example
CREATE TABLE consumer(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE orders(
    id SERIAL PRIMARY KEY,
    consumer_id INTEGER REFERENCES consumer(id),
    product_id INTEGER REFERENCES product(id)
);


-- MANY-TO-MANY example

CREATE TABLE article (
    id SERIAL PRIMARY KEY,
    title TEXT
);

CREATE TABLE tag (
    id SERIAL PRIMARY KEY,
    tag_value TEXT
);

CREATE TABLE article_tag(
    article_id INT,
    tag_id INT,
    PRIMARY KEY (article_id, tag_id),
    CONSTRAINT fk_article FOREIGN KEY(article_id) REFERENCES article(id),
    CONSTRAINT fk_tag FOREIGN KEY(tag_id) REFERENCES tag(id)
);

select * from article_tag;












