create table comment
(
	comment_id bigint auto_increment,
	comment varchar(1024),
	parent_id bigint not null,
	type int not null,
	commentator bigint not null,
	create_time bigint not null,
	modified_time bigint not null,
	like_count bigint default 0,
	constraint comment_pk
		primary key (comment_id)
);

comment on column comment.parent_id is '父类id（吐槽内容id）';

comment on column comment.type is '父类类型。1级评论，2级评论...';

comment on column comment.commentator is '评论人。对标usrid';

comment on column comment.like_count is '点赞数';

