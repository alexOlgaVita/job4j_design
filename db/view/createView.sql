-- запрос отбирает билеты в разрезе человека, маршрута и вида траспорта с условием, 
-- что по маршруту имеется более одного билета
create view show_persons_routs_transport_with_2_or_more_routs
as
select p.name as person, r.id rout_is, t.name as transport, count(r.id), c1.name as city_from, c2.name as city_to
from persons as p
         join tickets tt on p.id = tt.person_id
         join routs r on tt.routs_id = r.id
		 join transport t on r.transport_id = t.id
         join cities c1 on r.city_id1 = c1.id
         join cities c2 on r.city_id2 = c2.id
group by (p.name, c1.name, c2.name, t.name, r.id)
having count(r.id) >= 2;