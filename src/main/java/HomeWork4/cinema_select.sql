USE cinema_db;
# 1 -	ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
# 		Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»
SELECT 
m1.movie_name_fld AS `film 1`, s1.show_starting_time_fld AS `starting time 1`, m1.movie_length_fld AS `movie length 1`,
m2.movie_name_fld AS `film 2`, s2.show_starting_time_fld AS `starting time 2`, m2.movie_length_fld AS `movie length 2`
FROM 
	show_running_tbl AS s1
    JOIN
	movies_tbl AS m1 ON (m1.movie_id = s1.show_movie_id)
	JOIN
    show_running_tbl AS s2 ON (s1.show_id <> s2.show_id) AND (s1.show_date_fld = s2.show_date_fld)
    AND (s2.show_starting_time_fld > s1.show_starting_time_fld)
	AND (s2.show_starting_time_fld < (s1.show_starting_time_fld + m1.movie_length_fld))
    JOIN
    movies_tbl AS m2 ON (m2.movie_id = s2.show_movie_id)
ORDER BY s1.show_starting_time_fld;

# 2 - перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1»,
#	 «время начала», «длительность», «время начала второго фильма», «длительность перерыва» 
SELECT 
m1.movie_name_fld, s1.show_starting_time_fld, m1.movie_length_fld, s2.show_starting_time_fld,
sec_to_time(time_to_sec(s2.show_starting_time_fld) - time_to_sec(m1.movie_length_fld) - time_to_sec(s1.show_starting_time_fld)) AS `Brake`
FROM
	show_running_tbl AS s1
    JOIN
	movies_tbl AS m1 ON (m1.movie_id = s1.show_movie_id)
	JOIN
    show_running_tbl AS s2 ON (s1.show_date_fld = s2.show_date_fld) 
    AND s2.show_starting_time_fld > sec_to_time(time_to_sec(s1.show_starting_time_fld) + time_to_sec(m1.movie_length_fld) + 1800)
    AND time_to_sec(s2.show_starting_time_fld) - time_to_sec(m1.movie_length_fld) - time_to_sec(s1.show_starting_time_fld) < 7800
ORDER BY `Brake` DESC
# Послесловие: условий задания не достаточно чтобы корректно ограничить выдачу от наложения фильмов идуших через сеанс и более,
#				поэтому ограничение перерыва "сверху" было введено вручную в 2:10:00 (7800с.)
;

# 3 - список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей
# 	за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого»,
# 	содержащая данные по всем фильмам сразу
SELECT
ifnull(m.movie_name_fld,'total') as 'name', count(m.movie_name_fld) as `views`,
count(m.movie_name_fld) / count(distinct s.show_id)  as `average`,
sum(t.cost_fld) as `sum`
FROM
	tickets_tbl AS t
	JOIN
	show_running_tbl AS s ON t.show_id = s.show_id
	JOIN
	movies_tbl AS m ON m.movie_id = s.show_movie_id
GROUP BY m.movie_name_fld with rollup
;

# 4 - число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18,
#	 с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.)
SELECT
CASE s.show_starting_time_fld
	WHEN "10:30:00" THEN "from 9 till 15"
END AS `time`,
count(t.ticket_id) AS `ticket count`, sum(t.cost_fld) AS `sum`
FROM
	tickets_tbl AS t
	JOIN
	show_running_tbl AS s ON t.show_id = s.show_id
WHERE 
s.show_starting_time_fld between time("09:00:00") and time("15:00:00")
UNION
SELECT
CASE s.show_starting_time_fld
	WHEN "17:30:00" THEN "from 15 till 18"
END AS `time`,
count(t.ticket_id) AS `ticket count`, sum(t.cost_fld) AS `sum`
FROM
	tickets_tbl AS t
	JOIN
	show_running_tbl AS s ON t.show_id = s.show_id
WHERE 
s.show_starting_time_fld between time("15:00:00") and time("18:00:00")
UNION
SELECT
CASE s.show_starting_time_fld
	WHEN "18:45:00" THEN "from 18 till 21"
END AS `time`,
count(t.ticket_id) AS `ticket count`, sum(t.cost_fld) AS `sum`
FROM
	tickets_tbl AS t
	JOIN
	show_running_tbl AS s ON t.show_id = s.show_id
WHERE 
s.show_starting_time_fld between time("18:00:00") and time("21:00:00")
UNION
SELECT
CASE s.show_starting_time_fld
	WHEN "23:15:00" THEN "from 21 till 0"
END AS `time`,
count(t.ticket_id) AS `ticket count`, sum(t.cost_fld) AS `sum`
FROM
	tickets_tbl AS t
	JOIN
	show_running_tbl AS s ON t.show_id = s.show_id
WHERE 
s.show_starting_time_fld between time("21:00:00") and time("23:59:59")
# Послесловие: так как по условию задания расписание кино-проката - произвольные, то временные рамки,
#				 указанные выше носят сомнительную информативность в выводе запроса
;
