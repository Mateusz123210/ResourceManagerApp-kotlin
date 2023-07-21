--liquibase formatted sql
--changeset mateusz:1

CREATE TABLE users (
                              id integer NOT NULL,
                              type smallint,
                              creation_time timestamp(6) without time zone NOT NULL,
                              modification_time timestamp(6) without time zone NOT NULL,
                              name character varying(255) NOT NULL,
                              nick character varying(255) NOT NULL,
                              surname character varying(255) NOT NULL,
                              CONSTRAINT users_type_check CHECK (((type >= 0) AND (type <= 1)))
);