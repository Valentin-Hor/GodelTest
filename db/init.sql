DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

GRANT ALL ON SCHEMA public TO godel_user;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE department
(
    id serial primary key,
    title varchar(32)
);

CREATE TABLE job_title
(
    id serial primary key,
    title varchar(32)
);

CREATE TABLE employee
(
    id serial primary key,
    first_name varchar(32),
    last_name varchar(32),
    department_id int references department(id) on delete cascade,
    job_title_id int references job_title(id) on delete cascade,
    gender varchar(32),
    date_of_birth date
);
INSERT INTO department(title)
    VALUES ('Administration'),('Manufacture');

INSERT INTO job_title(title)
VALUES('Director'),('Engineer'),('Secretery'),('Economist'),('Manager'),('Master'),('Worker');

INSERT INTO employee(first_name, last_name, department_id, job_title_id, gender, date_of_birth)
    VALUES ('Valentin','Khorunzhyn','1','1','MALE','04-02-1987'),
        ('Igor','Ryabchikov','1','2','MALE','27-01-1965'),
        ('Elena','Sokolova','1','3','FEMALE','18-06-1990'),
        ('Alexander','Morozov','1','4','MALE','23-04-1982'),
        ('Egor','Plutoev','1','5','MALE','12-10-1985'),
        ('Dmitry','Usov','1','5','MALE','03-05-1991'),
        ('Yuri','Dubenko','2','6','MALE','07-12-1984'),
        ('Sergey','Platonov','2','7','MALE','14-03-1990'),
        ('Vasiliy','Korgan','2','7','MALE','21-12-1991');

-- select employee.first_name,
--     employee.last_name,
--     employee.gender,
--     employee.date_of_birth,
--     department.title,
--     job_title.title
-- FROM employee,department,job_title
-- WHERE employee.department_id = department.id
--       AND employee.job_title_id = job_title.id;






