create table tocao_text
(
	text_id int auto_increment,
	creator int,
	title varchar(50),
	description text,
	tag varchar(300),
	view_count int default 0,
	like_count int default 0,
	comment_count int default 0,
	create_time bigint,
	modified_time bigint,
	constraint tocao_text_pk
		primary key (text_id)
);

comment on column tocao_text.creator is '作者的id';

comment on column tocao_text.description is '正文';

comment on column tocao_text.view_count is '浏览数，默认为0';

comment on column tocao_text.like_count is '点赞数，默认为0';

comment on column tocao_text.comment_count is '评论数';

comment on column tocao_text.modified_time is '修改时间';