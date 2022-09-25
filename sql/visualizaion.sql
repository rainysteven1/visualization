SET NAMES utf8mb4;
-- ----------------------------
-- 1、项目文件表
-- ----------------------------
drop table if exists project_relevance_file;
drop table if exists project;
drop table if exists project_file;

create table project_file
(
    file_id        bigint(20)   not null auto_increment comment '文件ID',
    file_name      varchar(50)  not null default '' comment '文件名称',
    file_extension varchar(5)   not null default '' comment '文件后缀',
    file_type      tinyint(2)   not null default 0 comment '文件类型 (0项目文件 1统计文件 2依赖文件)',
    file_url       varchar(300) not null default '' comment '文件URL',
    is_delete      tinyint(1)   not null default 1 comment '文件是否删除（0否 1是）',
    create_time    datetime comment '文件创建时间',
    update_time    datetime comment '文件更新时间',
    primary key (file_id)
) engine = innodb comment = '项目文件表';

-- ----------------------------
-- 2、项目表
-- ----------------------------
create table project
(
    project_id          bigint(20)  not null auto_increment comment '项目ID',
    project_name        varchar(50) not null comment '项目名称',
    project_description varchar(100)         default '' comment '项目描述',
    project_user_group  bigint(20)  not null default 0 comment '项目用户组',
    project_status      tinyint(2)  not null default 0 comment '项目状态(0开发中 1测试中 2已发布)',
    is_delete           tinyint(1)  not null default 0 comment '项目是否删除（0否 1是）',
    create_time         datetime comment '项目创建时间',
    update_time         datetime comment '项目更新时间',
    primary key (project_id)
) engine = innodb comment = '项目表';

-- ----------------------------
-- 3、项目与项目文件关联表  项目1-N项目文件
-- ----------------------------
create table project_relevance_file
(
    relevance_id           bigint(20) not null auto_increment comment '关联ID',
    project                bigint(20) not null comment '项目',
    project_structure_file bigint(20) not null comment '项目结构文件',
    project_count_file     bigint(20) not null comment '项目统计文件',
    project_relation_file  bigint(20) not null comment '项目依赖文件',
    constraint project foreign key project_relevance_filet (project) references project (project_id) on update cascade on delete cascade,
    constraint project_structure foreign key project_relevance_file (project_structure_file) references project_file (file_id) on update cascade on delete cascade,
    constraint project_count foreign key project_relevance_file (project_count_file) references project_file (file_id) on update cascade on delete cascade,
    constraint project_relation foreign key project_relevance_file (project_relation_file) references project_file (file_id) on update cascade on delete cascade,
    primary key (relevance_id)
) engine = innodb comment = '项目与项目文件关联表';