drop table if exists steps;
create table steps(
    id int primary key auto_increment,
    description varchar(100) not null,
    done bit
)
