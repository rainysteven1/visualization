SET NAMES utf8mb4;

-- ----------------------------
-- 1、项目结构文件表
-- ----------------------------
drop table if exists project_structure_file;
create table project_structure_file
(
    file_id        bigint(20) not null auto_increment comment '文件ID',
    file_name      varchar(50)  default '' comment '文件名称',
    file_extension varchar(5)   default '' comment '文件后缀',
    file_url       varchar(100) default '' comment '文件URL',
    is_delete      tinyint(1)   default 1 comment '文件是否删除（0否 1是）',
    create_time    datetime comment '文件创建时间',
    update_time    datetime comment '文件更新时间',
    parent_file_id bigint(20) comment '父文件ID',
    primary key (file_id)
) engine = innodb comment = '项目结构文件表';