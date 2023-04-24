CREATE TABLE people
(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255)
);

CREATE TABLE expenses
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    expense_title VARCHAR(255),
    amount        INT,
    expense_type  VARCHAR(50),
    date          DATE,
    person_id     BIGINT,
    FOREIGN KEY (person_id) REFERENCES people (id)
);

CREATE TABLE incomes
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(255),
    amount      INT,
    income_type VARCHAR(50),
    date        DATE,
    person_id   BIGINT,
    FOREIGN KEY (person_id) REFERENCES people (id)
);