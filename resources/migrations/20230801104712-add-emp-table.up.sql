    CREATE TABLE
    IF NOT EXISTS
        cj_employees(
                  id serial PRIMARY KEY ,
                     name VARCHAR(100) NOT NULL,
                     phone VARCHAR(50) NULL,
                     email VARCHAR(50) NULL,
                  dept_id INTEGER NULL,
                  CONSTRAINT fk_emp_dept
                  FOREIGN KEY (dept_id)
                  REFERENCES cj_departments (id)
                  ON DELETE SET NULL,
                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                  );

