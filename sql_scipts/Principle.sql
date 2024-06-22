CREATE TABLE public.Principle (
                                  id serial4 NOT NULL,
                                  dapuan_level serial4 ,
                                  romantic_id serial4 ,
                                  user_profile_extended_id BIGSERIAL,
                                  create_by int8 NULL,
                                  modified_by int8 NULL,
                                  modified_on timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  is_deleted bool NULL,
                                  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  created_by int8 NULL,
                                  CONSTRAINT participant_pkey PRIMARY KEY (id),
                                  constraint foreign_key_user_profile_extended_id foreign key (user_profile_extended_id) references public.user_profile_extended(id)
                                      constraint foreign_key_romantic_id foreign key (romantic_id) references public.romantic_room
);