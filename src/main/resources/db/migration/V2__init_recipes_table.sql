create table recipes
(
    id int primary key auto_increment,
    description varchar(100) not null,
    done bit
);

alter table steps add column recipe_id int null;
alter table steps add foreign key (recipe_id) references recipes (id);
