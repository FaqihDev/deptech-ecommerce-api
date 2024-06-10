CREATE TABLE public.domicile (
                                 id serial4 NOT NULL,
                                 kelompok_sambung varchar(255) NULL,
                                 desa_sambung varchar(255) NULL,
                                 kelompok_address varchar(255) NOT NULL,
                                 desa_address varchar(255) NOT NULL,
                                 user_profile_extended_id bigserial NOT NULL,
                                 create_by int8 NULL,
                                 modified_by int8 NULL,
                                 modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 is_deleted bool NULL,
                                 created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 created_by int8 NULL,
                                 CONSTRAINT domicilie_pkey PRIMARY KEY (id),
                                 CONSTRAINT foreign_key_user_profile_extended_id FOREIGN KEY (user_profile_extended_id) REFERENCES public.user_profile_extended(id)
);