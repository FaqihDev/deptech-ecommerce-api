CREATE TABLE public.Status (
                               id serial4 NOT NULL,
                               dapuan varchar(255) NULL,
                               dapuan_level varchar(255) NULL,
                               status varchar(255),
                               user_profile_extended_id BIGSERIAL,
                               create_by int8 NULL,
                               modified_by int8 NULL,
                               modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               is_deleted bool NULL,
                               created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               created_by int8 NULL,
                               CONSTRAINT status_pkey PRIMARY KEY (id),
                               constraint foreign_key_user_profile_extended_id foreign key (user_profile_extended_id) references public.user_profile_extended(id)
);