SET NAMES utf8mb4;
-- ----------------------------
-- 1、项目文件表
-- ----------------------------
drop table if exists project_file;
create table project_file
(
    file_id        bigint(20) not null auto_increment comment '文件ID',
    file_name      varchar(50)         default '' comment '文件名称',
    file_extension varchar(5)          default '' comment '文件后缀',
    file_type      tinyint(2) not null default 0 comment '文件类型 (0项目文件 1统计文件 2依赖文件)',
    file_url       varchar(100)        default '' comment '文件URL',
    is_delete      tinyint(1)          default 1 comment '文件是否删除（0否 1是）',
    create_time    datetime comment '文件创建时间',
    update_time    datetime comment '文件更新时间',
    primary key (file_id)
) engine = innodb comment = '项目文件表';

-- ----------------------------
-- 2、项目表
-- ----------------------------
drop table if exists project;
create table project
(
    project_id             bigint(20) not null auto_increment comment '项目ID',
    project_name           varchar(50)  default '' comment '项目名称',
    project_description    varchar(100) default '' comment '项目描述',
    project_structure_file bigint(20) comment '项目结构文件',
    project_count_file     bigint(20) comment '项目统计文件',
    project_relation_file  bigint(20) comment '项目依赖文件',
    project_status         tinyint(2)   default 0 comment '项目状态(0 开发中)',
    is_delete              tinyint(1)   default 0 comment '项目是否删除（0否 1是）',
    create_time            datetime comment '项目创建时间',
    update_time            datetime comment '项目更新时间',
    primary key (project_id),
    constraint project_structure foreign key project (project_structure_file) references project_file (file_id) on update cascade on delete cascade,
    constraint project_count foreign key project (project_count_file) references project_file (file_id) on update cascade on delete cascade,
    constraint project_relation foreign key project (project_relation_file) references project_file (file_id) on update cascade on delete cascade
) engine = innodb comment = '项目表';