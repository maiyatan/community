create table tocao_text
(
	text_id int auto_increment ,
	creator int comment '作者的id',
	title varchar(50),
	description text,
	tag varchar(300),
	view_count int default 0 comment '浏览数',
	like_count int default 0 comment '点赞数',
	comment_count int default 0 comment '评论数',
	create_time bigint,
	modified_time bigint,
	constraint tocao_text_pk
		primary key (text_id)
);