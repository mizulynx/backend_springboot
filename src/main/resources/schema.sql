CREATE TABLE IF NOT EXISTS CONTENT(
    id INTEGER AUTO_INCREAMENT,
    title varchar(255) NOT NULL,
    desc text,
    status VARCHAR(20) NOT NULL,
    content_type varchar(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url varchar(255),
    primary key (id)
);