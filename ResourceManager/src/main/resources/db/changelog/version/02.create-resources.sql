--liquibase formatted sql
--changeset mateusz:1
CREATE TABLE resources (
                                  id integer NOT NULL,
                                  type smallint,
                                  user_id_id integer NOT NULL,
                                  creation_time timestamp(6) without time zone NOT NULL,
                                  modification_time timestamp(6) without time zone NOT NULL,
                                  metadata character varying(255) NOT NULL,
                                  name character varying(255) NOT NULL,
                                  CONSTRAINT resources_type_check CHECK (((type >= 0) AND (type <= 2)))
);