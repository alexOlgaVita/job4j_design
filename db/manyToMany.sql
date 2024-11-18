create table pupils (
id serial primary key,
name varchar(255),
age int
);

create table teachers (
id serial primary key,
name varchar(255),
school_subject varchar(255),
work_experience int
);

create table pupils_teachers(
    id serial primary key,
    pupil_id int references pupils(id),
    teacher_id int references teachers(id)
);

