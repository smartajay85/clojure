CREATE TABLE
    IF NOT EXISTS
        cj_orders (
                        id serial PRIMARY KEY,
                        item VARCHAR (100) NOT NULL
                        );