--liquibase formatted sql

--changeset mingaripov:1
CREATE TABLE dog_shelter
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    information TEXT,
    address TEXT,
    phoneNumber TEXT,
    workSchedule TEXT,
    securityContacts TEXT,
    safetyRecommendations TEXT,
    CONSTRAINT pk_dog_shelter PRIMARY KEY (id)
);

--changeset mingaripov:2
CREATE TABLE dog
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name TEXT,
    age INT,
    health_status BOOLEAN,
    vaccination BOOLEAN,
    status_animal INT,
    CONSTRAINT pk_dog PRIMARY KEY (id)
);

--changeset mingaripov:3
CREATE TABLE dog_owner
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    phoneNumber TEXT,
    CONSTRAINT pk_dog_owner PRIMARY KEY (id)
);

--changeset mingaripov:4
create table dog_owner_dog
(
    dog_owner_id bigint not null
        constraint fkipftsx386s1e9e1lk5onryuh5
            references dog_owner,
    dog_id  bigint not null
        constraint uk_k09alvu5bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs01ttp0ucho9yp8addx4m
            references dog,
    constraint dogOwner_dog_pkey
        primary key (dog_owner_id, dog_id)
);

--changeset mingaripov:5
create table dog_shelter_dog
(
    dog_shelter_id bigint not null
        constraint fkipftsx386s1e9e1lk5onryuh7
            references dog_shelter,
    dog_id  bigint not null
        constraint uk_k09alvu7bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs01ttp0ucho7yp8addx4m
            references dog,
    constraint dog_shelter_dog_pkey
        primary key (dog_shelter_id, dog_id)
);

--changeset mingaripov:6
CREATE TABLE cat_shelter
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    information TEXT,
    address TEXT,
    phoneNumber TEXT,
    workSchedule TEXT,
    securityContacts TEXT,
    safetyRecommendations TEXT,
    CONSTRAINT pk_cat_shelter PRIMARY KEY (id)
);

--changeset mingaripov:7
CREATE TABLE cat
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name TEXT,
    age INT,
    health_status BOOLEAN,
    vaccination BOOLEAN,
    status_animal INT,
    CONSTRAINT pk_cat PRIMARY KEY (id)
);

--changeset mingaripov:8
CREATE TABLE cat_owner
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    phoneNumber TEXT,
    CONSTRAINT pk_cat_owner PRIMARY KEY (id)
);

--changeset mingaripov:9
create table cat_owner_cat
(
    cat_owner_id bigint not null
        constraint fkipftsx386s1e9e1lk5onryuh5
            references cat_owner,
    cat_id  bigint not null
        constraint uk_k06alvu5bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs01ttp0ucho9yp8addx4m
            references cat,
    constraint catOwner_cat_pkey
        primary key (cat_owner_id, cat_id)
);

--changeset mingaripov:10
create table cat_shelter_cat
(
    cat_shelter_id bigint not null
        constraint fkipftsx386s1e9e1lk5onryuh7
            references cat_shelter,
    cat_id  bigint not null
        constraint uk_k06alvu7bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs02ttp0ucho7yp8addx4m
            references cat,
    constraint cat_shelter_cat_pkey
        primary key (cat_shelter_id, cat_id)
);

--changeset mingaripov:11
CREATE TABLE message_to_volunteer
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    sender    VARCHAR(255),
    text      VARCHAR(255),
    local_date_time TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_message_to_volunteer PRIMARY KEY (id)
);

--changeset mingaripov:12
CREATE TABLE report
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    photo           bytea,
    food_ration      VARCHAR(255),
    general_health   VARCHAR(255),
    behavior_changes VARCHAR(255),
    receive_date     date,
    CONSTRAINT pk_report PRIMARY KEY (id)
);

--changeset mingaripov:13
CREATE TABLE trial_period
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    start_date date,
    end_date   date,
    result    INTEGER,
    CONSTRAINT pk_trial_period PRIMARY KEY (id)
);

--changeset mingaripov:14
create table trial_period_report
(
    trial_period_id bigint not null
        constraint fkipftsx186s1e9e1lk5onryuh7
            references trial_period,
    report_id  bigint not null
        constraint uk_k01alvu7bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs01ttp0ucho7yp8addx4m
            references report,
    constraint trial_period_report_pkey
        primary key (trial_period_id, report_id)
);

--changeset mingaripov:15
CREATE TABLE users
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    DTYPE       VARCHAR(31),
    phone_number VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

--changeset mingaripov:16
create table users_trial_period
(
    users_id bigint not null
        constraint fkipftsx146s1e9e1lk5onryuh7
            references users,
    trial_period_id  bigint not null
        constraint uk_k01alvu8bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs08ttp0ucho7yp8addx4m
            references trial_period,
    constraint users_trial_period_pkey
        primary key (users_id, trial_period_id)
);

--changeset mingaripov:17
create table users_completed_trial_period
(
    users_id bigint not null
        constraint fkipftsx136s1e9e1lk5onryuh7
            references users,
    trial_period_id  bigint not null
        constraint uk_k03alvu8bnbjdjysbclq6qjnb
            unique
        constraint fkrvxxs02ttp0ucho7yp8addx4m
            references trial_period,
    constraint users_completed_trial_period_pkey
        primary key (users_id, trial_period_id)
);