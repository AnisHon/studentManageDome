drop database if exists db_design;
create database if not exists db_design;

use db_design;

# -----------------------------------------------------
# 1. 专业表
# -----------------------------------------------------
drop table if exists major;
create table major
(
    major_id    bigint       not null comment '专业id'
        primary key,
    major_name  varchar(255) not null comment '专业名称',
    institution varchar(255) not null comment '学院名称',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁'
) engine=InnoDB comment '专业表';

# -----------------------------------------------------
# 2. 班级表
# -----------------------------------------------------
drop table if exists class;
create table class
(
    class_id    bigint      not null comment '班级id'
        primary key ,
    major_id    bigint      not null comment '专业ID',
    class_name  varchar(255) not null comment '班级姓名',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁',
    constraint class_major_pk foreign key class(major_id)
        references major(major_id)
) engine=InnoDB comment '班级表';


# -----------------------------------------------------
# 3. 用户表
# -----------------------------------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id     bigint       not null comment '用户id'
        primary key,
    work_number varchar(255) null comment '工号',
    password    varchar(255) not null comment '密码',
    username    varchar(255) not null comment '姓名',
    birthday    date         not null comment '生日',
    gender      tinyint      not null comment '性别 0 男 1女',
    role        int          null default 1 comment '(学生 1 辅导员 5 教师 10 管理员 20)',
    status      int          null default 0 comment '用户状态(0 正常，1 异常)',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁',
    constraint sys_user_pk_2
        unique (work_number)

) engine=InnoDB comment '用户表';


# -----------------------------------------------------
# 4. 用户分表 学生表
# -----------------------------------------------------
drop table if exists student;
create table student
(
    user_id     bigint       not null comment '用户id'
        primary key,
    class_id    bigint       not null comment '班级id',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁',
    constraint student_fk_user_id
        foreign key student(user_id) references sys_user(user_id),
    constraint student_class_pk foreign key student(class_id)
        references class(class_id)
) engine=InnoDB comment '用户分表 学生表';

# -----------------------------------------------------
# 5. 用户分表 教师表
# -----------------------------------------------------
drop table if exists teacher;
create table teacher
(
    user_id     bigint       not null comment '用户id'
        primary key,
    title       varchar(255) not null comment '职称',
    major_id    bigint       not null comment '专业',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁',
    constraint teacher_fk_user_id
        foreign key teacher(user_id) references sys_user(user_id),
    constraint teacher_major_pk foreign key teacher(major_id)
        references major(major_id)
) engine=InnoDB comment '用户分表 教师表';

# -----------------------------------------------------
# 6. 用户分表 辅导员表
# -----------------------------------------------------
drop table if exists instructor;
create table instructor
(
    user_id     bigint       not null comment '用户id'
        primary key,
    major_id    bigint       not null comment '专业id',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁',
    constraint instructor
        foreign key instructor(user_id) references sys_user(user_id),
    constraint instructor_major_pk foreign key instructor(major_id)
        references major(major_id)
) engine=InnoDB comment '用户分表 辅导员表';


# -----------------------------------------------------
# 7. 课程表
# -----------------------------------------------------
drop table if exists course;
create table course
(
    course_id   bigint      not null comment '课程ID'
        primary key,
    course_name varchar(255) not null comment '课程名称',
    credit      int         not null comment '学分',
    duration    int         not null comment '课时',
    school_year int         not null comment '学年',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁'
) engine=InnoDB comment '用户分表 辅导员表';





# -----------------------------------------------------
# 8. 任教表
# -----------------------------------------------------
drop table if exists teacher_course;
create table teacher_course
(
    teach_id  bigint
        primary key                  not null comment '任教课程ID',
    course_id bigint        not null comment '课程ID',
    user_id   bigint        not null comment '教师id',
    unique key (course_id, user_id),
    constraint tc_fk_user_id
        foreign key teacher_course(user_id) references teacher(user_id),
    constraint tc_fk_course_id
        foreign key teacher_course(course_id) references course(course_id)

) engine=InnoDB comment '任教表';


# -----------------------------------------------------
# 9. 选课表
# -----------------------------------------------------
drop table if exists student_course;
create table student_course
(
    teach_id  bigint        not null comment '任教课程ID',
    user_id   bigint        not null comment '学生id',
    score     decimal(3, 2) null     comment '得分',
    primary key (teach_id, user_id),
    constraint sc_fk_user_id
        foreign key student_course(user_id) references student(user_id),
    constraint sc_fk_teach_id
        foreign key student_course(teach_id) references teacher_course(teach_id)
) engine=InnoDB comment '选课表';

# -----------------------------------------------------
# 10. 标记表
# -----------------------------------------------------
create table mark
(
    student_id      bigint      not null comment '学生ID',
    instructor_id   bigint      not null comment '辅导员ID',
    tag             varchar(255) null    comment '标记',
    create_time datetime     default current_timestamp comment '创建时间',
    update_time datetime     default current_timestamp comment '修改时间，乐观锁',
    constraint mark_student_fk
        foreign key mark(student_id) references student(user_id),
    constraint mark_instructor_fk
        foreign key mark(instructor_id) references instructor(user_id)
) engine=InnoDB comment '标记表';
