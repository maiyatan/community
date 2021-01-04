create table user(
    id int not null auto_increment,
    name varchar(50) default null,
    account_id varchar(100),
    token char(36),
    create_time bigint,
    modified_time bigint,
    avatar_url varchar(200),
	primary key (id)
);