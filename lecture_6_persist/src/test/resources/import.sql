insert into roster(id, name) values(1, 'roster1');
insert into roster(id, name) values(2, 'roster2');
insert into roster(id, name) values(3, 'roster3');

insert into unit(id, type) values(1, 'PIKEMAN');
insert into unit(id, type) values(2, 'PIKEMAN');
insert into unit(id, type) values(3, 'ARCHER');

insert into roster_units (roster_id, units_id) values (2, 1)
insert into roster_units (roster_id, units_id) values (2, 2)
insert into roster_units (roster_id, units_id) values (3, 1)
insert into roster_units (roster_id, units_id) values (3, 3)
