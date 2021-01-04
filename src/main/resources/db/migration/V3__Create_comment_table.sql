create table comment
(
	comment_id int auto_increment ,
	comment varchar(1024) ,
	parent_id int not null comment  '父类id（吐槽内容id）',
	type int not null comment '父类类型。1级评论，2级评论...',
	commentator int not null comment '评论人。对标usrid',
	like_count bigint default 0 comment '点赞数',
	create_time bigint not null ,
	modified_time bigint not null ,
	constraint comment_pk
		primary key (comment_id)
);