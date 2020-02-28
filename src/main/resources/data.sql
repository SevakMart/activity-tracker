insert into roles
values (1, 'ADMIN');
insert into roles
values (2, 'USER');

INSERT INTO "USERS"("USER_ID", "ACTIVE", "EMAIL", "LAST_NAME", "NAME", "PASSWORD")
VALUES (111111, 1, 'admin@admin.com', 'lastname', 'admin', '$2y$12$zCgxhS.wToR12wfuUj1ydu8toOCuUJkBFi9cSC2as.JdLDQ2S2ilS');

INSERT INTO "USERS"("USER_ID", "ACTIVE", "EMAIL", "LAST_NAME", "NAME", "PASSWORD")
VALUES (222222, 1, 'user@user.com', 'lastname', 'user', '$2y$12$zCgxhS.wToR12wfuUj1ydu8toOCuUJkBFi9cSC2as.JdLDQ2S2ilS');

insert into USER_ROLE
values (111111, 1);
insert into USER_ROLE
values (222222, 2);