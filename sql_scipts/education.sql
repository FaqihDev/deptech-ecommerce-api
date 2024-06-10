CREATE TABLE public.education (
                                  id serial4 NOT NULL,
                                  school_name varchar(255) NULL,
                                  school_majors varchar(255) NULL,
                                  university_name varchar(255) NULL,
                                  university_majors varchar(255) NULL,
                                  last_education varchar(255) NOT NULL,
                                  on_going_education varchar(255) NULL,
                                  user_profile_extended_id bigserial NOT NULL,
                                  create_by int8 NULL,
                                  modified_by int8 NULL,
                                  modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  is_deleted bool NULL,
                                  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  created_by int8 NULL,
                                  CONSTRAINT education_pkey PRIMARY KEY (id),
                                  CONSTRAINT foreign_key_user_profile_extended_id FOREIGN KEY (user_profile_extended_id) REFERENCES public.user_profile_extended(id)
);