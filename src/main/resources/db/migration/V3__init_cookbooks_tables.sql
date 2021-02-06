create table cookbooks
(
    id int primary key auto_increment,
    description varchar(100) not null
);


alter table recipes add column cookbook_id int null;
alter table recipes add foreign key (cookbook_id) references cookbooks (id);
