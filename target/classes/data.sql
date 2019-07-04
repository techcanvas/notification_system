insert into pack_info 
values(1,'$49','Send Email - Daily Limit 1 M');
insert into pack_info 
values(2,'$99','Send Email and SMS  - Total Daily Limit 10 M');
insert into pack_info 
values(3,'$500','SSend Email and SMS and PUSH  - Unlimited');

insert into PACKS_DEFAULT_LIMIT 
values(1,3,0,0);
insert into PACKS_DEFAULT_LIMIT 
values(2,3,0,3);
insert into PACKS_DEFAULT_LIMIT 
values(3,-1,-1,-1);
